
# Salesforce Documenter

The documenter is designed to help you understand your Salesforce org. It uses the SOAP, REST and Tooling APIs to get the most complete picture of your configuration possible. Given the gaps in the Metadata API it can't (and probably wont ever) be considered complete. 

### This project has only just begun development, it should not be considered ready for use by any stretch of the imagination :) 

Current version running on Heroku is https://protected-beyond-5758.herokuapp.com/ 

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

## Running local dev (or I want to work on this on the plane)

There is some hacks in some of the code that will probably removed that will enable you to run this in an offline state.
In the src/test directory there is some files that are a saved result of some REST API calls that are in use. 
Set a system environment variable isDev=true to run this project offline.. 
