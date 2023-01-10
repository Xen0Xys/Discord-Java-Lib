package fr.xen0xys.discordjava.sample;

import fr.xen0xys.discordjava.components.modal.AbstractModal;
import fr.xen0xys.discordjava.components.modal.ModalField;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class SampleModal extends AbstractModal {

    private static final ModalField modalField = new ModalField("Name", TextInputStyle.SHORT);

    public SampleModal() {
        super("sample_modal", "Sample modal", modalField);
    }

    @Override
    public void callback(@NotNull ModalInteractionEvent e) {
        e.reply(e.getValues().get(0).getAsString()).queue();
    }
}
