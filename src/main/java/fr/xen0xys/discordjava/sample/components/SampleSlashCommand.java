package fr.xen0xys.discordjava.sample.components;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.commands.AbstractSlashCommand;
import fr.xen0xys.discordjava.components.commands.SlashCommandOption;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class SampleSlashCommand extends AbstractSlashCommand {

    public SampleSlashCommand() {
        super("sample", "Sample slash command for tests",
                new SlashCommandOption(OptionType.CHANNEL, "channel", "Channel to send the message", true));
    }

    @Override
    public void callback(@NotNull DJApp bot, @NotNull SlashCommandInteraction e) {
        long targetChannelId = Objects.requireNonNull(e.getOption("channel")).getAsLong();
        AbstractModal modal = new SampleModal(bot, targetChannelId);
        modal.handle(bot.getComponentsManager(), List.of(e.getUser().getIdLong()));
        e.replyModal(modal.getModal()).queue();
    }
}
