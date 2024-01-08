package net.bytebond.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class pingResponder extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            Message message = event.getMessage();
            User botUser = event.getJDA().getSelfUser();

            if(message.getMentions().isMentioned(botUser)) {
                event.getMessage().reply("Hey there \uD83D\uDC4B \nI'm QuotaBot, quoting famous people every 24 hours in https://discord.com/channels/1131709369160040530/1169705079041966261 . \nYou can take a look at my code on [github](https://github.com/notswedish/QuotaBot)" ).queue();
                System.out.println("Mentioned by " + event.getAuthor().getAsTag() + " in " + event.getGuild().getName() + " (" + event.getGuild().getId() + ").");
            }
        }


    }
}
