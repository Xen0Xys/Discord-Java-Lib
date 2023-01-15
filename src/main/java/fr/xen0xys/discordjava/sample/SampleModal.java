package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.DJBot;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import fr.xen0xys.discordjava.utils.IdUtils;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class SampleModal extends AbstractModal {

    private static final ModalField modalField = new ModalField("name", "Name", TextInputStyle.SHORT, "Enter your name");

    private final long targetChannelId;

    private final IdUtils idUtils;

    public SampleModal(DJBot bot, long targetChannelId) {
        super("sample_modal", "Sample modal", modalField);
        this.targetChannelId = targetChannelId;
        this.idUtils = new IdUtils(bot);
    }

    @Override
    public void callback(@NotNull DJBot bot, @NotNull ModalInteractionEvent e) {
        TextChannel targetChannel = this.idUtils.getTextChannelFromId(this.targetChannelId);
        if(targetChannel != null){
            targetChannel.sendMessage("Hello " + e.getValues().get(0).getAsString()).queue();
            e.deferReply(true).addContent("Message sent").queue();
        }else
            e.deferReply(true).addContent("The target channel is not found").queue();
    }
}
