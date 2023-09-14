package fr.xen0xys.discordjava.sample.components;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.components.commands.SlashCommandOption;
import fr.xen0xys.discordjava.components.commands.SlashSubCommand;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SampleComplexSlashCommand extends AbstractSlashCommand {

    private static final SlashCommandOption nameOption = new SlashCommandOption(OptionType.STRING, "name", "Name of the user", true);
    private static final SlashCommandOption ageOption = new SlashCommandOption(OptionType.INTEGER, "age", "Age of the user", true);
    private static final SlashSubCommand FIRST_SLASH_SUB_COMMAND = new SlashSubCommand("first_subcommand", "First Subcommand", nameOption);
    private static final SlashSubCommand SECOND_SLASH_SUB_COMMAND = new SlashSubCommand("second_subcommand", "Second Subcommand", ageOption);

    public SampleComplexSlashCommand() {
        super("complex_sample", "Sample slash command for tests", FIRST_SLASH_SUB_COMMAND, SECOND_SLASH_SUB_COMMAND);
    }

    @Override
    public void callback(@NotNull DJApp bot, @NotNull SlashCommandInteraction e) {
        switch (Objects.requireNonNull(e.getSubcommandName()).toLowerCase()){
            case "first_subcommand" -> {
                String name = Objects.requireNonNull(e.getOption("name")).getAsString();
                e.reply("Your name is " + name).queue();
            }
            case "second_subcommand" -> {
                int age = (int) Objects.requireNonNull(e.getOption("age")).getAsLong();
                e.reply("You are " + age + " years old").queue();
            }
            default -> e.reply("Unknown subcommand").setEphemeral(true).queue();
        }
    }
}
