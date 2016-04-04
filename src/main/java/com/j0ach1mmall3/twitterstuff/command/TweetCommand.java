package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.tweet.TweetPage;
import com.j0ach1mmall3.twitterscrapeapi.tweet.FullTweet;
import com.j0ach1mmall3.twitterscrapeapi.user.UserPreview;
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
        try {
            TweetPage tweetPage;
            try {
                tweetPage = new TweetPage(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                commandRequest.reply("Invalid Tweet ID!");
                return;
            }
            tweetPage.fetchData();
            FullTweet tweet = (FullTweet) tweetPage.getTweet();
            String s = '@' + tweet.getOriginalTweeter().getScreenName() + ": \"" + tweet.getMessage() + "\" (" + tweet.getId() + ")\n" +
                    "- " + tweet.getTimestamp() + " | " + tweet.getRetweets() + " Retweets | " + tweet.getLikes() + " Likes\n" +
                    "Likers: ";
            for(UserPreview userPreview : tweet.getLikersPreview()) {
                s += userPreview.getScreenName() + ", ";
            }
            s = s.substring(0, s.length() - 2) + "...";
            commandRequest.reply(s);
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        } catch (PageNotFoundException e) {
            commandRequest.reply("Unknown Tweet!");
        }
    }
}
