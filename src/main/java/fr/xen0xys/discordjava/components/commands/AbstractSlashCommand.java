package fr.xen0xys.discordjava.components.commands;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.interfaces.ICommandComponent;
import fr.xen0xys.discordjava.components.managers.CommandsManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
public abstract class AbstractSlashCommand implements ICommandComponent {

    private final String name;
    private final SlashCommandData commandData;

    public AbstractSlashCommand(@NotNull String name, @NotNull String description, @Nullable SlashCommandOption... options){
        this.name = name;
        this.commandData = Commands.slash(name, description);
        if(Objects.nonNull(options))
            for(SlashCommandOption option : options)
                this.commandData.addOption(option.optionType(), option.name(), option.description(), option.required());
    }

    public AbstractSlashCommand(@NotNull String name, @NotNull String description, SlashSubCommand... subcommands){
        this.name = name;
        this.commandData = Commands.slash(name, description);
        for(SlashSubCommand slashSubCommand : subcommands){
            SubcommandData subcommandData = new SubcommandData(slashSubCommand.name(), slashSubCommand.description());
            for(SlashCommandOption option : slashSubCommand.options())
                subcommandData.addOption(option.optionType(), option.name(), option.description(), option.required());
            this.commandData.addSubcommands(subcommandData);
        }
    }

    @Override
    public void register(@NotNull CommandsManager commandsManager, boolean sync) {
        commandsManager.registerCommand(this, sync);
    }

    @Override
    public void registerLocal(@NotNull CommandsManager commandsManager, @NotNull Guild guild, boolean sync) {
        commandsManager.registerLocalCommand(guild, this, sync);
    }

    public String getName() {
        return name;
    }

    public SlashCommandData getCommandData() {
        return commandData;
    }

    public abstract void callback(@NotNull DJApp bot, @NotNull SlashCommandInteraction e);
}
