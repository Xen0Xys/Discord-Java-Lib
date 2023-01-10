package fr.xen0xys.discordjava.components.modal;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record ModalField(@NotNull String fieldId, @NotNull String fieldName, @NotNull TextInputStyle inputType, @NotNull String placeHolder, int minLength, int maxLength) {
    public ModalField(@NotNull String fieldName, @NotNull TextInputStyle inputType){
        this(fieldName.toLowerCase(), fieldName, inputType, "", 0, 4000);
    }

    public ModalField(@NotNull String fieldId, @NotNull String fieldName, @NotNull TextInputStyle inputType, @NotNull String placeHolder){
        this(fieldId, fieldName, inputType, placeHolder, 0, 4000);
    }

    public ModalField(@NotNull String fieldId, @NotNull String fieldName, @NotNull TextInputStyle inputType, @NotNull String placeHolder, int maxLength){
        this(fieldId, fieldName, inputType, placeHolder, maxLength, maxLength);
    }

    public TextInput getField(){
        return TextInput.create(this.fieldId, this.fieldName, this.inputType)
                .setPlaceholder(this.placeHolder)
                .setMinLength(this.minLength)
                .setMaxLength(this.maxLength)
                .build();
    }
}
