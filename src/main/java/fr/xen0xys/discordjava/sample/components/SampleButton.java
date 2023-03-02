package fr.xen0xys.discordjava.sample.components;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.buttons.AbstractButton;
import fr.xen0xys.discordjava.components.buttons.enums.ButtonType;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class SampleButton extends AbstractButton {

    public SampleButton() {
        super("delete-msg", ButtonType.Danger, "Delete this message");
    }

    @Override
    public void callback(@NotNull DJApp bot, @NotNull ButtonInteractionEvent e) {
        e.getMessage().delete().queue();
    }
}
