package fr.xen0xys.discordjava.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

@SuppressWarnings("unused")
public class SlashCommand {

    private final SlashCommandData commandData;

    public SlashCommand(String name, String description, SlashCommandOption... options){
        this.commandData = Commands.slash(name, description);
        for(SlashCommandOption option : options){
            this.commandData.addOption(option.optionType(), option.name(), option.description(), option.required());
        }
    }

    public void registerCommand(JDA jda){
        jda.upsertCommand(this.commandData).queue();
    }

    public void registerCommand(Guild guild){
        guild.upsertCommand(this.commandData).queue();
    }

}
