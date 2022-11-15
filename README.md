# The FeedMe tech test 

The Tech test is to consume and transform the proprietary mock data. 
The proprietary data format will need to be parsed and enriched with the relevant field names and data types. 
For more information about the feed please read the provider README: https://hub.docker.com/r/sbgfeedme/provider/

Tasks
1. Create an app that connects the provider service on the exposed TCP port
2. Transform the proprietary data format into JSON using the field names and data types defined in the provider /types endpoint
3. Write unit tests
4. Save the JSON into a NoSQL store
5. Create a Dockerfile for your app(s)

Technology
1. Java
2. Spring boot
3. Mongo
4. Docker

Run the App
1. clone the repo
2. install Spring tool suite/intellij
3. open project the maven project in IDE
4. run the application on tomcat server (host=localhost, port=8081)
