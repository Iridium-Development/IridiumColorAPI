# IridiumColorAPI

```xml
<repository>
    <id>savagelabs</id>
    <url>https://nexus.savagelabs.net/repository/maven-releases/</url>
</repository>
```
```xml
<dependency>
    <groupId>com.iridium</groupId>
    <artifactId>IridiumColorAPI</artifactId>
    <version>LATEST</version>
</dependency>
```
## Usage
**Gradient**
```java
String message = IridiumColorAPI.newInstance()
        .setPatternType(PatternType.GRADIENT)
        .setText("Cool string with a gradient")
        .setValue("2C08BA", "028A97")
        .process();
```
![Gradient Text](https://i.imgur.com/M1l5OM9.png)

**Rainbow**

```java
String message = IridiumColorAPI.newInstance()
        .setPatternType(PatternType.RAINBOW)
        .setText("THIS IS A REALLY COOL Rainbow")
        //saturation
        .setValue("1")
        .process();
```

![Rainbow](https://i.imgur.com/5GhSFo1.png)

```java
String message = IridiumColorAPI.newInstance()
        .setPatternType(PatternType.RAINBOW)
        .setText("THIS IS A REALLY COOL Rainbow")
        //saturation
        .setValue("100")
        .process();
```
![Rainbow](https://i.imgur.com/Rieftuz.png)

**Solid**
```java
String message = IridiumColorAPI.newInstance()
        .setPatternType(PatternType.SOLID)
        .setText("Cool RGB SUPPORT")
        .setValue("FF0080")
        .process();
```
![RGB Text](https://i.imgur.com/IudqIpb.png)

Now, you can store the colour into a file configuration.

**save method**
```java
IridiumColor colour = IridiumColorAPI.newInstance()
        .setPatternType(PatternType.SOLID)
        .setText("Cool RGB SUPPORT")
        .setValue("FF0080");
colour.save(fileConfiguration, path);
saveConfig();
//this is an example.
```
**retrieve method**
```java
IridiumColor colour = IridiumColorAPI.retrieve(fileConfiguration, path);
```
## Note

If your server's version is pre 1.16, the plugin will draw the given string to the nearest supported colour value (as the image below)

![Legacy RGB](https://i.imgur.com/8RMmCAX.png)
