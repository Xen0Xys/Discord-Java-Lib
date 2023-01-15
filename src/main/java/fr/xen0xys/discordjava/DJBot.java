package fr.xen0xys.discordjava;

import fr.xen0xys.discordjava.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.commands.CommandsManager;
import fr.xen0xys.discordjava.components.ComponentsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class DJBot extends ListenerAdapter {

    private final JDA jda;
    private Logger logger;

    private final CommandsManager commandsManager;
    private final ComponentsManager componentsManager;

    private final List<AbstractSlashCommand> commands = new ArrayList<>();

    public DJBot(@NotNull String token, @NotNull String loggerName) throws InterruptedException {
        this.setupLogger(loggerName);
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        this.commandsManager = new CommandsManager(this);
        this.componentsManager = new ComponentsManager(this);
        jda.addEventListener(this);
    }

    private void setupLogger(@NotNull String loggerName){
        try {
            InputStream stream = DJBot.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
            this.logger = Logger.getLogger(loggerName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JDA getJDA() {
        return jda;
    }
    public Logger getLogger(){
        return this.logger;
    }
    public CommandsManager getCommandsManager() {
        return commandsManager;
    }
    public ComponentsManager getComponentsManager() {
        return this.componentsManager;
    }
}
