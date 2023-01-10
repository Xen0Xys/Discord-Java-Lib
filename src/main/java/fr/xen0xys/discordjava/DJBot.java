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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DJBot extends ListenerAdapter {

    private final JDA jda;
    private final String nameSpace;
    private final List<AbstractSlashCommand> commands = new ArrayList<>();
    private final Map<Long, AbstractModal> modals = new HashMap<>();

    public DJBot(@NotNull String token, @NotNull String nameSpace) throws InterruptedException {
        this.nameSpace = nameSpace;
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        jda.addEventListener(this);
    }

    public JDA getJDA() {
        return jda;
    }

    public void registerCommand(@NotNull AbstractSlashCommand command){
        this.jda.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void registerLocalCommand(@NotNull Guild guild, @NotNull AbstractSlashCommand command){
        guild.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void handleModal(long senderId, @NotNull AbstractModal modal){
        this.modals.put(senderId, modal);
    }

    public String getNameSpace() {
        return nameSpace;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        this.commands.stream().filter(command -> command.getName().equals(e.getName())).forEach(command -> command.callback(e));
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent e) {
        if(this.modals.containsKey(e.getUser().getIdLong()) && e.getModalId().contains(this.nameSpace)){
            this.modals.get(e.getUser().getIdLong()).callback(e);
            this.modals.remove(e.getUser().getIdLong());
        }
    }
}
