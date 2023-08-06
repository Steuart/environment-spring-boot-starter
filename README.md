## 1. Introduction
This little project is designed to load custom environment variable configurations into a springboot project.
This allows you to externalize important configuration information (e.g. database connection information) into system environment variables.
It is also easy to switch between different environments such as development, test and production.

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
The default configuration file is ```[your home path]/.env/config.properties```.

You can customize the file location, and then add the parameter ```-Denv.conf=[your config path]``` when the project starts, where ```[path]``` is the absolute path of your custom configuration file.  
Let's say your custom configuration file is in /etc/config/ilex.properties and starts up like this:  
```shell
java -jar -Denv.config=/etc/config/config.properties your-project.jar
```

### 2.3. Add environment variables
Add the environment variable to the configuration file, for example:  
```properties
meerkat.datasource.username=root
meerkat.datasource.password=123456
```
### 2.4. Use environment variables
You can use it in ```application.yml``` in the springboot project like this:
```yaml
spring:
  datasource:
    username: ${meerkat.datasource.username}
    password: ${meerkat.datasource.password}
```
If you don't want to start the springboot container and just want to do a little testing, you can use the ```EnvUtil``` tool to get the environment variable values, like below:
```
String username = EnvUtil.getValue("meerkat.datasource.username");
```

Hope this helps, enjoy your coding!
