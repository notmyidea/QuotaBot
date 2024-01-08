package net.bytebond;

import net.bytebond.util.pingResponder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.ScheduledEvent;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.security.auth.login.LoginException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    static Dotenv dotenv = Dotenv.load();

    static String token = dotenv.get("TOKEN");
    static String QOTDChannelID = dotenv.get("QOTD_CHANNEL_ID");

    public static void main(String[] args) throws LoginException, InterruptedException {



        // < init JDA >
        JDA builder = JDABuilder.createDefault(token).enableIntents(
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS).addEventListeners(new pingResponder()).build();
        builder.awaitReady();

        builder.getPresence().setActivity(Activity.listening("daily quotes"));
        System.out.println("Logged in as '" + builder.getSelfUser().getAsTag() + "' in " + builder.getGuilds().size() + " guild(s).");

        // </ init JDA >




        // < init QOTD >    Check if QOTD channel exists, if so, schedule QOTD to be sent every 24 hours.
        // I know its horrible, but I don't know how to do it any other way.
        TextChannel channel = builder.getTextChannelById(QOTDChannelID);
        if (channel != null) {
            System.out.println("Found QOTD channel: '" + channel.getName() + "' (" + channel.getId() + ").");

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

            executor.scheduleAtFixedRate(() -> QOTD(builder, executor),
                    0,
                    24, TimeUnit.HOURS);


        } else {
            System.out.println("Couldn't find QOTD channel as specified by ID: '" + QOTDChannelID + "'.");
        }
        // </ init QOTD >


    }

    // < QOTD >   Send QOTD to channel.
    public static void QOTD(JDA builder, ScheduledExecutorService executor) {
        TextChannel channel = builder.getTextChannelById(QOTDChannelID);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);



        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.quotable.io/quotes/random?tags=famous-quotes")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(response -> {
                    String quote = response.substring(response.indexOf("content") + 10, response.indexOf("author") - 3);
                    String author = response.substring(response.indexOf("author") + 9, response.indexOf("author") + 9 + response.substring(response.indexOf("author") + 9).indexOf("\""));
                    String authorUrl = "https://en.wikipedia.org/wiki/" + author.replace(" ", "_");

                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(author);
                    embed.setUrl(authorUrl);
                    embed.setDescription(quote);

                    embed.setFooter("Quote of the Day", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Wikipedia-logo-v2.svg/1200px-Wikipedia-logo-v2.svg.png");
                    embed.setColor(Color.decode("#2596be"));
                    embed.setTimestamp(date.atStartOfDay());

                    channel.sendMessageEmbeds(embed.build()).queue(); System.out.println("Sent todays Quote to channel: '" + channel.getName() + "' (" + channel.getId() + ").");


        });

    }

}