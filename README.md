# Spring kotlin + react typescript

This project is skeleton for integration of spring (in kotlin) and react (in typescript).

Used technologies:

- Spring boot
- Mustache templates
- Parcel bundler
- React, Typescript, styled-components





### How React + Spring works

You need to have installed: **node** + **yarn**

#### Problem
We need to bundle react files into one (or more javascript files)
and import them in 
```
<script></script> 
``` 
tag. Name of bundled file differs each build (so browser is not caching it).

#### Solution 

Web module contains task that is run before resources are copied. This task is responsible for bundling of web-react
project and copying **js** and **map** files to **resources/static**. 

Output of build is also entry html file that imports generated js files in **script** tag. 
In our case, it is file named **react.mustache**, which is after bundling copied to templates folder. This file should imported
 to footer in templates that want to use react components.


#### Development
For live javascript files in development you need to:

1. first enable hot reload files in IDEA (check section below).
2. run server via IDEA task
3. Go to web-react folder and run 
```
yarn dev:spring
```
4. Enjoy

#### HELP I run server from IDEA and It failed

Default IDEA run/debug configuration does not run gradle tasks and will not build javascript. 
This will cause build to fail entirely. You have these options:

1. Run gradle task **web:buildJs**, after that IDEA configuration will work
2. Edit IDEA task configuration and add *before launch* gradle task like this 
(be sure it is before build task)

|         |            | 
| ------------- |:-------------:| 
| Gradle project      | **path to web module ($rootDir/web)** | 
| Tasks      | **buildJs**      |  

3. Run **initReactTemplate** gradle task - this will init empty react.mustache template, react will not work
4. Go to web-react and run **build:spring**, after that IDEA configuration will work 
(or **build:spring** for watching files and hot reload)



### How to hot reload in IDEA

These are steps that are need for hot reloading stuff in IDEA. This is most usefull when developing
react (and using **yarn dev:spring**) task.

##### 1 - Enabling automatic build
```
Open the Settings --> Build-Execution-Deployment --> Compiler
``` 
and enable: Build Project Automatically.

##### 2 - Update the value of compiler.automake.allow.when.app.running

press ctrl+shift+A and search for the registry. In the registry, enable :
```
compiler.automake.allow.when.app.running
```

