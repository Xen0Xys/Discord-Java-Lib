package fr.xen0xys.discordjava.components.commands;

public record SlashSubCommand(String name, String description, SlashCommandOption... options) {
}
