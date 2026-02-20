#!/bin/bash
set -e

REGION=ap-northeast-2
ACCOUNT_ID=000000000000
TOPIC_ARN="arn:aws:sns:${REGION}:${ACCOUNT_ID}:user"

# ========== SNS Topic ==========
echo "Creating SNS topic..."
awslocal sns create-topic --name user --region $REGION

# ========== SQS Queues ==========
echo "Creating SQS queues..."
awslocal sqs create-queue --queue-name aml-service --region $REGION
awslocal sqs create-queue --queue-name core-service --region $REGION
awslocal sqs create-queue --queue-name notification-service --region $REGION

# ========== aml-service 구독 ==========
echo "Subscribing aml-service..."
AML_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:${REGION}:${ACCOUNT_ID}:aml-service" \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $AML_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION

# ========== core-service 구독 ==========
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

# ========== notification-service 구독 ==========
echo "Subscribing notification-service..."
NOTI_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint "arn:aws:sqs:${REGION}:${ACCOUNT_ID}:notification-service" \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $NOTI_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION

echo ""
echo "LocalStack initialization complete!"
