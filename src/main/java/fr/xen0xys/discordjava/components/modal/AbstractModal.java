package fr.xen0xys.discordjava.components.modal;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.interfaces.IHandledComponent;
import fr.xen0xys.discordjava.components.managers.ComponentsManager;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractModal implements IHandledComponent {

    private final String id;
    private final Modal modal;

    public AbstractModal(@NotNull String id, @NotNull String title, @NotNull ModalField... fields){
        this.id = id;
        ActionRow[] builtFields = Arrays.stream(fields).map(ModalField::getField).map(ActionRow::of).toList().toArray(new ActionRow[0]);
        this.modal = Modal.create(id, title).addActionRows(builtFields).build();

    }

    @Override
    public void handle(@NotNull ComponentsManager componentsManager, @NotNull List<Long> userIds) {
        if(userIds.size() != 1) throw new IllegalArgumentException("Modal can only be used by one user");
        componentsManager.handleModal(userIds.get(0),this);
    }

    public String getId() {
        return id;
    }

    public Modal getModal(){
        return this.modal;
    }

    public abstract void callback(@NotNull DJApp bot, @NotNull ModalInteractionEvent e);

}
