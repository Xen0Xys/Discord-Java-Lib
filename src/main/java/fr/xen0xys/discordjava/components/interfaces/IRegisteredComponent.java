package fr.xen0xys.discordjava.components.interfaces;

import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface IRegisteredComponent extends IComponent {
    void register(@NotNull final ComponentsManager componentsManager);
}
