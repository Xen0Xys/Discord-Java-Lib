package fr.xen0xys.discordjava.commands;

import fr.xen0xys.discordjava.DJBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager extends ListenerAdapter {

    private final DJBot bot;

    private final List<AbstractSlashCommand> commands = new ArrayList<>();

    public CommandsManager(@NotNull DJBot bot){
        this.bot = bot;
        this.bot.getJDA().addEventListener(this);
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
