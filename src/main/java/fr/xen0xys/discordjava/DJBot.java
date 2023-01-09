package fr.xen0xys.discordjava;

import fr.xen0xys.discordjava.commands.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class DJBot extends ListenerAdapter {

    private final JDA jda;
    private final List<SlashCommand> commands = new ArrayList<>();

    public DJBot(@NotNull String token) throws InterruptedException {
        this.jda = JDABuilder.createDefault(token).build().awaitReady();
        jda.addEventListener(this);
    }

    public JDA getJDA() {
        return jda;
    }

    public void registerCommand(@NotNull SlashCommand command){
        this.jda.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void registerLocalCommand(@NotNull Guild guild, @NotNull SlashCommand command){
        guild.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        this.commands.stream().filter(command -> command.getName().equals(e.getName())).forEach(command -> command.onCalled(e));
    }
}
