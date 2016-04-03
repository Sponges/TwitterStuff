package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.tweet.Tweet;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class TweetCommand extends Command {
    public TweetCommand() {
        super("Look up information about a Tweet", "tweet", "tweetstats", "tweetinfo");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <tweet id>");
            return;
        }
        commandRequest.reply("Fetching data of " + strings[0] + "...");
        Tweet tweet;
        try {
            try {
                tweet = new Tweet(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                commandRequest.reply("Invalid Tweet ID!");
                return;
            }
            tweet.fetchData();
            commandRequest.reply(tweet.toPrettyString());
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
