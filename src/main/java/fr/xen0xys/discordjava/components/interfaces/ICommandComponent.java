package fr.xen0xys.discordjava.components.interfaces;

import fr.xen0xys.discordjava.components.managers.CommandsManager;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface ICommandComponent {
    void register(@NotNull final CommandsManager commandsManager, final boolean sync);
    void registerLocal(@NotNull final CommandsManager commandsManager, @NotNull final Guild guild, final boolean sync);
}
