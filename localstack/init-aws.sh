#!/bin/bash
set -e

REGION=ap-northeast-2

echo "Creating SNS topic..."
awslocal sns create-topic --name order-events --region $REGION

echo "Creating SQS queues..."
awslocal sqs create-queue --queue-name order-processing-queue --region $REGION
awslocal sqs create-queue --queue-name order-notification-queue --region $REGION

TOPIC_ARN="arn:aws:sns:${REGION}:000000000000:order-events"
PROCESSING_QUEUE_ARN="arn:aws:sqs:${REGION}:000000000000:order-processing-queue"
NOTIFICATION_QUEUE_ARN="arn:aws:sqs:${REGION}:000000000000:order-notification-queue"

echo "Subscribing queues to SNS topic..."
PROCESSING_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint $PROCESSING_QUEUE_ARN \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $PROCESSING_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION

NOTIFICATION_SUB_ARN=$(awslocal sns subscribe \
  --topic-arn $TOPIC_ARN \
  --protocol sqs \
  --notification-endpoint $NOTIFICATION_QUEUE_ARN \
  --region $REGION \
  --output text --query 'SubscriptionArn')

awslocal sns set-subscription-attributes \
  --subscription-arn $NOTIFICATION_SUB_ARN \
  --attribute-name RawMessageDelivery \
  --attribute-value true \
  --region $REGION

echo "LocalStack initialization complete!"
echo "Topic: $TOPIC_ARN"
echo "Subscriptions created with RawMessageDelivery=true"
