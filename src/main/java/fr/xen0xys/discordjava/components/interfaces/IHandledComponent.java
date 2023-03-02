package fr.xen0xys.discordjava.components.interfaces;

import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IHandledComponent extends IComponent{
    void handle(@NotNull final ComponentsManager componentsManager, @NotNull final List<Long> userIds);
}
