package fr.xen0xys.discordjava;

import fr.xen0xys.discordjava.components.managers.CommandsManager;
import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class DJApp extends ListenerAdapter {

    private final JDA jda;
    private Logger logger;

    private final CommandsManager commandsManager;
    private final ComponentsManager componentsManager;

    public DJApp(@NotNull final String token, @NotNull final String loggerName) throws InterruptedException {
        this.setupLogger(loggerName);
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        this.commandsManager = new CommandsManager(this);
        this.componentsManager = new ComponentsManager(this);
        jda.addEventListener(this);
    }

    public DJApp(@NotNull final String token, @NotNull final Logger logger) throws InterruptedException {
        this.logger = logger;
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        this.commandsManager = new CommandsManager(this);
        this.componentsManager = new ComponentsManager(this);
        jda.addEventListener(this);
    }

    private void setupLogger(@NotNull final String loggerName){
        try {
            InputStream stream = DJApp.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
            this.logger = Logger.getLogger(loggerName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        this.jda.shutdown();
    }

    @Override
    public void onShutdown(@NotNull final ShutdownEvent event) {
        if(this.componentsManager.getHandledComponents().size() > 0)
            this.logger.warning("There are still handled components, data will be lost!");
        this.logger.info("DJApp shutdown!");
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
