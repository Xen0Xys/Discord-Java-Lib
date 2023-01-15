package fr.xen0xys.discordjava.components;

import fr.xen0xys.discordjava.DJBot;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ComponentsManager extends ListenerAdapter {

    private final DJBot bot;

    private final Map<Long, AbstractModal> modals = new HashMap<>();

    public ComponentsManager(DJBot bot){
        this.bot = bot;
        bot.getJDA().addEventListener(this);
    }

    public void handleModal(long senderId, @NotNull AbstractModal modal){
        this.modals.put(senderId, modal);
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent e) {
        if(this.modals.containsKey(e.getUser().getIdLong())){
            AbstractModal modal = this.modals.get(e.getUser().getIdLong());
            if(modal.getId().equals(e.getModalId())){
                this.modals.remove(e.getUser().getIdLong());
                modal.callback(this.bot, e);
            }
        }
    }

}
