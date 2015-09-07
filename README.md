
# Salesforce Documenter

The documenter is designed to help you understand your Salesforce org. It uses the SOAP, REST and Tooling APIs to get the most complete picture of your configuration possible. Given the gaps in the Metadata API it can't (and probably wont ever) be considered complete. 

### This project has only just begun development, it should not be considered ready for use by any stretch of the imagination :) 

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## So What's In The Box?

- Java 8 with Embedded Tomcat
- Memcache for session persistence on Heroku 
- AngularJS
- Salesforce Lightning Design System (https://developer.salesforce.com/lightning/design-system)



## Getting Started

Setup to run exploded in Eclipse.
- set build path to copy class files to web/src/main/webapp/WEB-INF/classes directory
- use Debug -> Debug Configurations and add new Java application
	webapp.runner.launch.Main

