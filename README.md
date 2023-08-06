## 1. Introduction
The purpose of this project is to customize the loading of system environment variables into the springboot project.  
This way you can externalize important configuration information, such as database connection information, to the system environment variables.  
It is very easy to switch between different environments, such as development, test, and production.  

## 2. How to use
### 2.1. Add dependency
```xml

<dependency>
    <groupId>>cc.jooylife</groupId>
    <artifactId>environment-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
### 2.2. Add configuration
The default configuration file is ```[your home]/.env/config.properties```.

You can customize the file location, and then add the parameter ```-Denv.conf=[path]``` when the project starts, where ```[path]``` is the absolute path of your custom configuration file.  
Let's say your custom configuration file is in /etc/config/ilex.properties and starts up like this:  
```shell
java -jar -Denv.conf=/etc/config/filex.properties your-project.jar
```

Hope this helps, enjoy your coding!
