package commands;

import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        if (commandName.equals("fart")) {
            event.reply("By the Allfather, that really puts the deadly in 'silent but deadly'").setEphemeral(true).queue();
        } else if (commandName.equals("hello")) {
            event.reply("hey " + event.getUser().getName()).queue();
        } else if (commandName.equals("guild") && event.getChannelId() != null) {
//            if (event.getChannel()) {
//            System.out.println(event.getChannel());
            try {
                event.reply("We are currently in the server: " + Objects.requireNonNull(event.getGuild()).getName()).setEphemeral(true).queue(); // sets the response so only the person who requested sees it
            } catch (NullPointerException e) {
                event.reply("We are currently in a Direct Message channel").queue();
            }
        } else if (commandName.equals("alert")) {
            event.reply("Successfully alerted").setEphemeral(true).queue();
            event.getChannel().sendMessage("I see all").queue();
        }
    }
}
