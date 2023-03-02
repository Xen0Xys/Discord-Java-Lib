package fr.xen0xys.discordjava.components.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;

public record SlashCommandOption(@NotNull OptionType optionType, @NotNull String name, @NotNull String description, boolean required) { }
