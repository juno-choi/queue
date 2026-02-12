#!/bin/bash
set -e

REGION=ap-northeast-2
ACCOUNT_ID=000000000000

# --- SNS Topic ---
echo "Creating SNS topic..."
awslocal sns create-topic --name main-topic --region $REGION

TOPIC_ARN="arn:aws:sns:${REGION}:${ACCOUNT_ID}:main-topic"

# --- SQS Queues ---
echo "Creating SQS queues..."
awslocal sqs create-queue --queue-name user-service --region $REGION
awslocal sqs create-queue --queue-name admin-service --region $REGION
awslocal sqs create-queue --queue-name core-service --region $REGION

# --- Subscribe & Filter ---
# 구독 + 필터 설정 함수
subscribe_with_filter() {
  local QUEUE_NAME=$1
  shift
  local EVENT_TYPES=("$@")

  local QUEUE_ARN="arn:aws:sqs:${REGION}:${ACCOUNT_ID}:${QUEUE_NAME}"

  # 1) Subscribe
  local SUB_ARN=$(awslocal sns subscribe \
    --topic-arn "$TOPIC_ARN" \
    --protocol sqs \
    --notification-endpoint "$QUEUE_ARN" \
    --region $REGION \
    --output text --query 'SubscriptionArn')

  # 2) RawMessageDelivery
  awslocal sns set-subscription-attributes \
    --subscription-arn "$SUB_ARN" \
    --attribute-name RawMessageDelivery \
    --attribute-value true \
    --region $REGION

  # 3) FilterPolicyScope = MessageBody
  awslocal sns set-subscription-attributes \
    --subscription-arn "$SUB_ARN" \
    --attribute-name FilterPolicyScope \
    --attribute-value MessageBody \
    --region $REGION

  # 4) FilterPolicy: eventType 배열 조립
  local FILTER_VALUES=""
  for ET in "${EVENT_TYPES[@]}"; do
    if [ -n "$FILTER_VALUES" ]; then
      FILTER_VALUES="${FILTER_VALUES},"
    fi
    FILTER_VALUES="${FILTER_VALUES}\"${ET}\""
  done

  awslocal sns set-subscription-attributes \
    --subscription-arn "$SUB_ARN" \
    --attribute-name FilterPolicy \
    --attribute-value "{\"eventType\":[${FILTER_VALUES}]}" \
    --region $REGION

  echo "  ${QUEUE_NAME} -> filter: eventType=[${FILTER_VALUES}]"
}

echo "Subscribing queues to SNS topic with eventType filter..."

# user-service:  deposit, join
subscribe_with_filter "user-service" "deposit" "join"

# admin-service: deposit, join
subscribe_with_filter "admin-service" "deposit" "join"

# core-service:  deposit, mail
subscribe_with_filter "core-service" "deposit" "mail"

echo ""
echo "LocalStack initialization complete!"
echo "Topic: $TOPIC_ARN"
echo "Filter policy summary:"
echo "  user-service  -> [deposit, join]"
echo "  admin-service -> [deposit, join]"
echo "  core-service  -> [deposit, mail]"
