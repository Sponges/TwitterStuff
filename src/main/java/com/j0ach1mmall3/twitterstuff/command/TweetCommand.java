package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.tweet.mobile.MobileTweetPage;
import com.j0ach1mmall3.twitterscrapeapi.tweet.MobileTweet;
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
        try {
            MobileTweetPage tweetPage;
            try {
                tweetPage = new MobileTweetPage(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                commandRequest.reply("Invalid Tweet ID!");
                return;
            }
            tweetPage.fetchData();
            MobileTweet tweet = tweetPage.getTweet();
            String s = '@' + tweet.getOriginalTweeter().getScreenName() + " on " + tweet.getTimestamp() + ": \"" + tweet.getMessage() + "\" (" + tweet.getId() + ')';
            commandRequest.reply(s);
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        } catch (PageNotFoundException e) {
            commandRequest.reply("Unknown Tweet!");
        }
    }
}
