package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.mobile.tweets.MobileTweetsSearchPage;
import com.j0ach1mmall3.twitterscrapeapi.tweet.Tweet;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class SearchCommand extends Command {
    public SearchCommand() {
        super("Search all of Twitter for a specific keyword", "tsearch", "twittersearch", "searchtwitter");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <keyword>");
            return;
        }
        try {
            MobileTweetsSearchPage searchPage = new MobileTweetsSearchPage(strings[0]);
            searchPage.fetchData();
            String s = "";
            if(searchPage.getSearchResult().size() < 3) {
                for(Tweet tweet : searchPage.getSearchResult()) {
                    s += '@' + tweet.getOriginalTweeter().getScreenName() + ": \"" + tweet.getMessage() + "\" | " + tweet.getTimestamp() + " (" + tweet.getId() + ")\n";
                }
            } else {
                for(int i = 0;i < 3;i++) {
                    Tweet tweet = searchPage.getSearchResult().get(i);
                    s += '@' + tweet.getOriginalTweeter().getScreenName() + ": \"" + tweet.getMessage() + "\" | " + tweet.getTimestamp() + " (" + tweet.getId() + ")\n";
                }
            }
            commandRequest.reply(s);
        } catch (IOException | PageNotFoundException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
