import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class DiscordBot{
    public static String getAPIKey(String fileName) {
        File file = new File("/home/Nyth/Documents/Discord_Bot/src/main/java/" + fileName + ".txt");
        try {
            Scanner scanner = new Scanner(file);
            String APIkey = null;
            if (scanner.hasNextLine()){
                String fileContent = scanner.nextLine();
                APIkey = fileContent.replace("APIkey: ", "");
            } else {
                System.err.print("Unable to find or extract from file: " + fileName);
            }
            return APIkey;
        } catch (FileNotFoundException e) {
          System.err.println("Unable to find file: " + fileName + ".txt");
          e.printStackTrace();
          System.exit(1);
        }
        return null;
    }



    public static void main(String[] args) throws InterruptedException {
        String APIkey = getAPIKey("APIkey");
        System.out.println(APIkey);

        JDA bot = JDABuilder.createDefault(APIkey)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("with ur mom, scrub"))
                .addEventListeners(new BotListeners(), new BotCommands())
                .build().awaitReady();

        Guild guild = bot.getGuildById("969426072217198623");

        if (guild != null) {
            guild.upsertCommand("fart", "do fart").queue();
            guild.upsertCommand("alert", "Alert those present of my presence").queue();
        }
    }


}
