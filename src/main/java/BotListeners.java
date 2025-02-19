import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BotListeners extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("Message detected");
        if(!event.getAuthor().isBot()) {
            String content = event.getMessage().getContentRaw();
            event.getChannel().sendMessage("Sent: " + content).queue();
            System.out.println("Message sent");
            sendReport(event.getAuthor());
        }


    }

    @Override
    public void onUserTyping(@NotNull UserTypingEvent event) {
        System.out.println(event.getUser() + "is typing");
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        Guild guild = event.getGuild();
        guild.upsertCommand("fart", "do fart").queue();
        guild.upsertCommand("alert", "Alert those present of my presence").queue();
    }

    public void sendReport(User author){
        System.out.println(author.getId());

    }
}
