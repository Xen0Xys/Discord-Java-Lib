package fr.xen0xys.discordjava.components.buttons;

import fr.xen0xys.discordjava.components.buttons.enums.ButtonType;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

abstract class AbstractPrimitiveButton {
    private final String id;
    private final ButtonType type;
    private final String content;

    public AbstractPrimitiveButton(@NotNull final String id, @NotNull final ButtonType type, @NotNull final String content){
        this.id = id;
        this.type = type;
        this.content = content;
    }

    public Button getButton(){
        return switch (this.type) {
            case Primary -> Button.primary(this.id, this.content);
            case Secondary -> Button.secondary(this.id, this.content);
            case Danger -> Button.danger(this.id, this.content);
            case Link -> Button.link(this.id, this.content);
            case Success -> Button.success(this.id, this.content);
        };
    }

    public String getId() {
        return id;
    }
}
