# Discord-Java-Lib

A Java library for creating Discord bots using the JDA library.

## Prerequisites

Before you can use this library, you need to have the following installed:

- Java 17 or higher
- Maven

## Installation

To use this library in your project, you can include it as a dependency in your Maven project by adding the following to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>fr.xen0xys</groupId>
        <artifactId>discord-java-lib</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Usage
To use this library, you need to create a new instance of the DJApp class, and it's ready to use!

Here is an example of how to use this library to create the bot:

```java
public class SampleBot {

    public static void main(String[] args){
        Configuration config = new Configuration(new File("data"), "config.yml");
        try {
            DJApp bot = new DJApp(config.getToken(),"SampleBot");
            IdUtils idUtils = new IdUtils(bot);
            Guild guild = idUtils.getGuildFromId(1016358670600245369L);
            if(guild != null)
                new SampleSlashCommand().registerLocal(bot.getCommandsManager(), guild, true);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.equals("stop")){
                    bot.stop();
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```
*Check sample code for more information!*

## License
This library is licensed under the GPL-3.0 License. See the LICENSE file for more details.