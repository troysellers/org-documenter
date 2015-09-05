
# Salesforce Documenter

The documenter is designed to help you understand your Salesforce org. It uses the SOAP, REST and Tooling APIs to get the most complete picture of your configuration possible. Given the gaps in the Metadata API it can't (and probably wont ever) be considered complete. 

### This project has only just begun development, it should not be considered ready for use by any stretch of the imagination :) 

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Getting Started

Install bower-installer
- sudo npm install -g bower-installer

Then, run bower-installer to install dependencies

ONE FIX - need to copy bootstrap css into the css directory.. 

Setup to run exploded in Eclipse.
- set build path to copy class files to web/src/main/webapp/WEB-INF/classes directory
- use Debug -> Debug Configurations and add new Java application
	webapp.runner.launch.Main

