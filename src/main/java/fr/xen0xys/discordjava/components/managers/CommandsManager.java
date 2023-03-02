package fr.xen0xys.discordjava.components.managers;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.commands.AbstractSlashCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager extends ListenerAdapter {

    private final DJApp bot;

    private final List<AbstractSlashCommand> commands = new ArrayList<>();

    public CommandsManager(@NotNull DJApp bot){
        this.bot = bot;
        this.bot.getJDA().addEventListener(this);
        this.bot.getLogger().info("Commands manager initialized !");
    }

    public void registerCommand(@NotNull AbstractSlashCommand command, boolean sync){
        this.bot.getLogger().info("Registering command '%s'".formatted(command.getName()));
        if(sync)
            this.bot.getJDA().upsertCommand(command.getCommandData()).complete();
        else
            this.bot.getJDA().upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    public void registerLocalCommand(@NotNull Guild guild, @NotNull AbstractSlashCommand command, boolean sync){
        this.bot.getLogger().info("Registering command '%s' for guild '%s'".formatted(command.getName(), guild.getName()));
        if(sync)
            guild.upsertCommand(command.getCommandData()).complete();
        else
            guild.upsertCommand(command.getCommandData()).queue();
        this.commands.add(command);
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        this.commands.stream().filter(command -> command.getName().equals(e.getName())).forEach(command -> command.callback(this.bot, e));
    }
}
