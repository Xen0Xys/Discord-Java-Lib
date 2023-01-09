package fr.xen0xys.discordjava;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SuppressWarnings("unused")
public class DJBot {
    private final JDA jda;

    public DJBot(String token) throws InterruptedException {
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
    }

    public JDA getJDA() {
        return jda;
    }
}
