package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.DJBot;
import fr.xen0xys.discordjava.config.Configuration;
import net.dv8tion.jda.api.entities.Guild;

import java.io.File;

public class SampleBot {

    public static void main(String[] args){
        Configuration config = new Configuration(new File("data"), "config.yml");
        try {
            DJBot bot = new DJBot(config.getToken(),"SampleBot");
            Guild guild = bot.getGuildById(1016358670600245369L);
            bot.registerLocalCommand(guild, new SampleSlashCommand());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
