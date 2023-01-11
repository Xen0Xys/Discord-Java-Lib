package fr.xen0xys.discordjava.components.modal;

import fr.xen0xys.discordjava.DJBot;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class AbstractModal {

    private final String id;
    private final Modal modal;

    public AbstractModal(@NotNull String id, @NotNull String title, @NotNull ModalField... fields){
        this.id = id;
        ActionRow[] builtFields = Arrays.stream(fields).map(ModalField::getField).map(ActionRow::of).toList().toArray(new ActionRow[0]);
        this.modal = Modal.create(id, title).addActionRows(builtFields).build();

    }

    public String getId() {
        return id;
    }

    public Modal getModal(){
        return this.modal;
    }

    public abstract void callback(@NotNull DJBot bot, @NotNull ModalInteractionEvent e);

}
