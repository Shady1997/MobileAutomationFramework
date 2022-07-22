# MobileAutomationFramework
My Own Mobile Automation Testing Framework Designed to facilate mobile automation testing using Selenium-Java , TestNg and Appium
We will discuss step by step from scratch how to use framework

#Installtion
1- install node js 
2- use npm in node js to install appium using following command
npm install -g appium
3- install appium doctor
npm install -g appium-doctor
4- install UIAutomator2Driver for android
npm i appium-uiautomator2-driver

#Usage of Framework
1- open cmd as administrator and run the following command
appium
2- open cmd as administrator and run the following command
uiautomatorviewer
3- open cmd as administrator and run the following command
adb reconnect

then

4- start android emulator
5- add apk below Src folder
6- change apk name in properties file below foloowing path
src\test\resources\properties\generalProperties.properties
7- add page class below following folder path
src\main\java\pages
8- add testbase below following folder path
src\test\java\test_cases


