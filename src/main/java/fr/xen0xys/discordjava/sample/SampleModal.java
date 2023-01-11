package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.DJBot;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class SampleModal extends AbstractModal {

    private static final ModalField modalField = new ModalField("name", "Name", TextInputStyle.SHORT, "Enter your name");

    private final long targetChannelId;

    public SampleModal(long targetChannelId) {
        super("sample_modal", "Sample modal", modalField);
        this.targetChannelId = targetChannelId;
    }

    @Override
    public void callback(@NotNull DJBot bot, @NotNull ModalInteractionEvent e) {
        e.reply(e.getValues().get(0).getAsString() + "\n" + this.targetChannelId).queue();
    }
}
