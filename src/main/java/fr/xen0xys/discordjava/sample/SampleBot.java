package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.config.Configuration;
import fr.xen0xys.discordjava.sample.components.SampleComplexSlashCommand;
import fr.xen0xys.discordjava.sample.components.SampleSlashCommand;
import fr.xen0xys.discordjava.utils.IdUtils;
import net.dv8tion.jda.api.entities.Guild;

import java.io.File;
import java.util.Scanner;

public class SampleBot {

    public static void main(String[] args){
        Configuration config = new Configuration(new File("data"), "config.yml");
        try {
            DJApp bot = new DJApp(config.getToken(),"SampleBot");
            IdUtils idUtils = new IdUtils(bot);
            Guild guild = idUtils.getGuildFromId(1016358670600245369L);
            if(guild != null){
                new SampleSlashCommand().registerLocal(bot.getCommandsManager(), guild, true);
                new SampleComplexSlashCommand().registerLocal(bot.getCommandsManager(), guild, true);
            }
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
