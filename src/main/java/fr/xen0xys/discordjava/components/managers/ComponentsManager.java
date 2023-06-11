package fr.xen0xys.discordjava.components.managers;

import fr.xen0xys.discordjava.DJApp;
import fr.xen0xys.discordjava.components.buttons.AbstractButton;
import fr.xen0xys.discordjava.components.buttons.AbstractStaticButton;
import fr.xen0xys.discordjava.components.interfaces.IComponent;
import fr.xen0xys.discordjava.components.modal.AbstractModal;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ComponentsManager extends ListenerAdapter {

    private final DJApp bot;

    private final List<AbstractStaticButton> registeredButtons = new ArrayList<>();

    private final Map<Long, AbstractModal> handledModals = new HashMap<>();
    private final Map<Long, AbstractButton> handledButtons = new HashMap<>();

    public ComponentsManager(@NotNull final DJApp bot){
        this.bot = bot;
        bot.getJDA().addEventListener(this);
        this.bot.getLogger().info("Components Manager initialized !");
    }

    public void registerButton(@NotNull final AbstractStaticButton button){
        this.registeredButtons.add(button);
    }

    public void handleModal(final long senderId, @NotNull final AbstractModal modal){
        this.handledModals.put(senderId, modal);
    }

    public void handleButton(final long senderId, @NotNull final AbstractButton button){
        this.handledButtons.put(senderId, button);
    }

    public List<IComponent> getHandledComponents(){
        List<IComponent> components = new ArrayList<>(this.handledButtons.values().stream().toList());
        components.addAll(this.handledModals.values().stream().toList());
        return components;
    }

    @Override
    public void onModalInteraction(@NotNull final ModalInteractionEvent e) {
        if(this.handledModals.containsKey(e.getUser().getIdLong())){
            AbstractModal modal = this.handledModals.get(e.getUser().getIdLong());
            if(modal.getId().equals(e.getModalId())){
                this.handledModals.remove(e.getUser().getIdLong());
                modal.callback(this.bot, e);
            }
        }
    }

    @Override
    public void onButtonInteraction(@NotNull final ButtonInteractionEvent e) {
        AbstractStaticButton staticButton = this.registeredButtons.stream().filter(b -> b.getId().equals(e.getComponentId())).findFirst().orElse(null);
        if(Objects.nonNull(staticButton))
            staticButton.callback(this.bot, e);
        else if(this.handledButtons.containsKey(e.getUser().getIdLong())){
            AbstractButton button = this.handledButtons.get(e.getUser().getIdLong());
            if(button.getId().equals(e.getComponentId())){
                this.handledButtons.remove(e.getUser().getIdLong());
                button.callback(this.bot, e);
            }
        }
    }
}
