package fr.xen0xys.discordjava.commands;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
public abstract class AbstractSlashCommand {

    private final String name;
    private final SlashCommandData commandData;

    public AbstractSlashCommand(@NotNull String name, @NotNull String description, @Nullable SlashCommandOption... options){
        this.name = name;
        this.commandData = Commands.slash(name, description);
        if(Objects.nonNull(options))
            for(SlashCommandOption option : options)
                this.commandData.addOption(option.optionType(), option.name(), option.description(), option.required());
    }

    public String getName() {
        return name;
    }

    public SlashCommandData getCommandData() {
        return commandData;
    }

    public abstract void callback(@NotNull SlashCommandInteraction e);
}
