package fr.xen0xys.discordjava;

import fr.xen0xys.discordjava.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class DJBot extends ListenerAdapter {

    private final JDA jda;
    private Logger logger;
    private final List<AbstractSlashCommand> commands = new ArrayList<>();
    private final Map<Long, AbstractModal> modals = new HashMap<>();

    public DJBot(@NotNull String token, @NotNull String loggerName) throws InterruptedException {
        this.setupLogger(loggerName);
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        jda.addEventListener(this);
    }

    public JDA getJDA() {
        return jda;
    }

    public void registerCommand(@NotNull AbstractSlashCommand command){
        this.logger.info("Registering command '%s'".formatted(command.getName()));
        this.jda.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void registerLocalCommand(@NotNull Guild guild, @NotNull AbstractSlashCommand command){
        this.logger.info("Registering command '%s' for guild '%s'".formatted(command.getName(), guild.getName()));
        guild.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void handleModal(long senderId, @NotNull AbstractModal modal){
        this.modals.put(senderId, modal);
    }

    public Guild getGuildById(long id){
        return this.jda.getGuildById(id);
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

    public Logger getLogger(){
        return this.logger;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        this.commands.stream().filter(command -> command.getName().equals(e.getName())).forEach(command -> command.callback(this, e));
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent e) {
        if(this.modals.containsKey(e.getUser().getIdLong())){
            AbstractModal modal = this.modals.get(e.getUser().getIdLong());
            if(modal.getId().equals(e.getModalId())){
                this.modals.remove(e.getUser().getIdLong());
                modal.callback(this, e);
            }
        }
    }
}
