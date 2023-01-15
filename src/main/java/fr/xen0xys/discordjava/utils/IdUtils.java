package fr.xen0xys.discordjava.utils;

import fr.xen0xys.discordjava.DJBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class IdUtils {

    private final DJBot bot;

    public IdUtils(@NotNull DJBot bot) {
        this.bot = bot;
    }

    public long getIdFromGuild(@NotNull Guild guild){
        return guild.getIdLong();
    }

    @Nullable
    public Guild getGuildFromId(long guildId){
        return this.bot.getJDA().getGuildById(guildId);
    }

    public long getIdFromTextChannel(@NotNull TextChannel channel){
        return channel.getIdLong();
    }

    @Nullable
    public TextChannel getTextChannelFromId(long channelId){
        return this.bot.getJDA().getTextChannelById(channelId);
    }


}
