package fr.xen0xys.discordjava.components.buttons;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.buttons.enums.ButtonType;
import fr.xen0xys.discordjava.components.interfaces.IRegisteredComponent;
import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractStaticButton extends AbstractPrimitiveButton implements IRegisteredComponent {

    public AbstractStaticButton(@NotNull final String id, @NotNull final ButtonType type, @NotNull final String content) {
        super(id, type, content);
    }

    @Override
    public void register(@NotNull final ComponentsManager componentsManager) {
        componentsManager.registerButton(this);
    }

    public abstract void callback(@NotNull final DJApp bot, @NotNull final ButtonInteractionEvent e);
}
