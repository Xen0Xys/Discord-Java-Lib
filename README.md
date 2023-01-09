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
To use this library, you need to create a new instance of the DiscordBot class and use its run() method to start the bot.

Here is an example of how to use this library to create a simple "echo" bot that repeats everything that is said in the chat:

```java
import com.example.discord.DiscordBot;

public class MyBot {
  public static void main(String[] args) {
      DJBot bot = new DJBot("token");
  }
}
```

To customize the behavior of your bot, you can use the onMessageReceived() method to register a callback that will be called every time a message is received.

Here is an example of how to modify the above example to implement the "echo" behavior:

For more information on how to customize your bot's behavior, please refer to the documentation for the JDA library.

## License
This library is licensed under the GPL-3.0 License. See the LICENSE file for more details.