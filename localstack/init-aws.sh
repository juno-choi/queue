#!/bin/bash
set -e

REGION=ap-northeast-2
ACCOUNT_ID=000000000000
TOPIC_ARN="arn:aws:sns:${REGION}:${ACCOUNT_ID}:main-topic"

# ========== SNS Topic ==========
echo "Creating SNS topic..."
awslocal sns create-topic --name main-topic --region $REGION

# ========== SQS Queues ==========
echo "Creating SQS queues..."
awslocal sqs create-queue --queue-name user-service --region $REGION
awslocal sqs create-queue --queue-name admin-service --region $REGION
awslocal sqs create-queue --queue-name core-service --region $REGION

# ========== user-service 구독 (deposit, join) ==========
echo "Subscribing user-service..."

USER_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:${REGION}:${ACCOUNT_ID}:user-service" \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $USER_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION


# ========== admin-service 구독 (deposit, join) ==========
echo "Subscribing admin-service..."
ADMIN_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:${REGION}:${ACCOUNT_ID}:admin-service" \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $ADMIN_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION


# ========== core-service 구독 (deposit, mail) ==========
echo "Subscribing core-service..."
CORE_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:${REGION}:${ACCOUNT_ID}:core-service" \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $CORE_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION


echo ""
echo "LocalStack initialization complete!"