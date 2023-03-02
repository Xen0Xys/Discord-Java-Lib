package fr.xen0xys.discordjava.components.buttons;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.buttons.enums.ButtonType;
import fr.xen0xys.discordjava.components.interfaces.IHandledComponent;
import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractButton extends AbstractPrimitiveButton implements IHandledComponent {

    public AbstractButton(String id, ButtonType type, String content) {
        super(id, type, content);
    }

    @Override
    public void handle(@NotNull final ComponentsManager componentsManager, @NotNull final List<Long> userIds) {
        for(final Long userId : userIds)
            componentsManager.handleButton(userId, this);
    }

    public abstract void callback(@NotNull DJApp bot, @NotNull ButtonInteractionEvent e);
}
