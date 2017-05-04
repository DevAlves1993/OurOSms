# OurOSms English
OurOSms is an open source software that allows you to send SMS through the [API REST SmsApi of orange Ivory Coast (zone AMEA)](https://www.orangepartner.com/SMS-CI-API). It allows you to monitor your activities using the OrangeSms service. It offers a user-friendly interface from which you can write your messages to be sent by SMS to your customers and partners.

# Feature

* Sending SMS via a user-friendly interface
* Saving Customer Information
* Saving Partner Information
* Monitoring of SMS (Statistics, ...)


### Install Frontend
```bash
# Navigate to PROJECT_FOLDER/webui (should contain pubspec.yaml )
pub get
# build the project (this will put the files under dist folder)
pub build
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
# Start the server (9090)
# port and other configurations for API servere is in [./src/main/resources/application.properties](/src/main/resources/application.properties) file

# If you build with gradle jar location will be 
java -jar ./build/libs/app-1.0.0.jar

# If you build with maven jar location will be 
java -jar ./target/app-1.0.0.jar
```


## Authors and Contributors
* In 2017, Amani Christian Cyrille Alves (@DevAlves1993) founded OurOSms.

## Contacts

* Gmail : [alvesamani@gmail.com] (mailto:alvesamani@gmail.com)
* Twitter [@cyrilleamani] (https://twitter.com/cyrilleamani)

## License

[MIT License](http://www.opensource.org/licenses/mit-license.php) 
[Introduction to the MIT License](https://opensource.org/osd-annotated)

## Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.

The list of the contributions which would be welcome.

* Documentation : Participated in the writing of the documentation.
* Bugs : Report bugs with of issues.
* Feature : New Feature.
