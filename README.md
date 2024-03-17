# IOTManager
The Project consists of two folders for
1- API to manage (register/delete/activate/deactivate/publish to any topic/subscribe to any topic)
2- Lambda Function to trigger any device update to shared topic and save the updates into Dynamo DB

Please add your credentials to application.properties 

To trigger the Lambda function we will create Topic Rule 
with SQL statement:
SELECT * FROM 'dynamoDBTopi'
And the action for the rule will be the Lambda function

