# OurOSms English
OurOSms is an open source software that allows you to send SMS through the Orange Sms service. It allows you to monitor your activities using the OrangeSms service. It offers a user-friendly interface from which you can write your messages to be sent by SMS to your customers and partners.

# Feature

* Sending SMS via a user-friendly interface
* Saving Customer Information
* Saving Partner Information
* Monitoring of SMS (Statistics, ...)


### Install Frontend
```bash
# Navigate to PROJECT_FOLDER/webui (should cntain package.json )
npm install
# build the project (this will put the files under dist folder)
ng build -prod --aot=false
```

### Install Backend (SpringBoot Java)
```bash
# Maven Build : Navigate to the root folder where pom.xml is present 
mvn clean install
Or
# Spring Plugin : Navigate to the root folder where pom.xml is present 
mvn spring-boot:run
```

### Start the API and WebUI server
```bash
# Start the server (8080)
# port and other configurations for API servere is in [./src/main/resources/application.properties](/src/main/resources/application.properties) file

# If you build with gradle jar location will be 
java -jar ./build/libs/app-1.0.0.jar

# If you build with maven jar location will be 
java -jar ./target/app-1.0.0.jar
```
