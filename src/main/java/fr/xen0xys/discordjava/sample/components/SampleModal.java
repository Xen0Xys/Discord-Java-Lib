package fr.xen0xys.discordjava.sample.components;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import fr.xen0xys.discordjava.utils.IdUtils;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SampleModal extends AbstractModal {

    private static final ModalField modalField = new ModalField("name", "Name", TextInputStyle.SHORT, "Enter your name");

    private final long targetChannelId;

    private final IdUtils idUtils;

    public SampleModal(DJApp bot, long targetChannelId) {
        super("sample_modal", "Sample modal", modalField);
        this.targetChannelId = targetChannelId;
        this.idUtils = new IdUtils(bot);
    }

    @Override
    public void callback(@NotNull DJApp bot, @NotNull ModalInteractionEvent e) {
        TextChannel targetChannel = this.idUtils.getTextChannelFromId(this.targetChannelId);
        if(targetChannel != null){
            targetChannel.sendMessage("Hello " + e.getValues().get(0).getAsString()).addActionRow(this.getButton(bot, e.getUser().getIdLong())).queue();
            e.deferReply(true).addContent("Message sent").queue();
        }else
            e.deferReply(true).addContent("The target channel is not found").queue();
    }

    private Button getButton(DJApp bot, long userId){
        SampleButton button = new SampleButton();
        button.handle(bot.getComponentsManager(), List.of(userId));
        return button.getButton();
    }
}
