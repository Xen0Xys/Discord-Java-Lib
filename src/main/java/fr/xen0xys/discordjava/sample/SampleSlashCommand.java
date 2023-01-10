package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.commands.SlashCommandOption;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

public class SampleSlashCommand extends AbstractSlashCommand {

    public SampleSlashCommand() {
        super("sample", "Sample slash command for tests",
                new SlashCommandOption(OptionType.CHANNEL, "channel", "Channel to send the message", true));
    }

    @Override
    public void callback(@NotNull SlashCommandInteraction e) {

    }
}
