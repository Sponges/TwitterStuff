package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.SearchPage;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.tweets.TweetsSearchPage;
import com.j0ach1mmall3.twitterscrapeapi.search.SearchType;
import com.j0ach1mmall3.twitterscrapeapi.tweet.ProfileTweet;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;
import java.util.List;

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
            SearchPage searchPage = new TweetsSearchPage(strings[0], SearchType.DEFAULT);
            searchPage.fetchData();
            commandRequest.reply("Results for '" + strings[0] + "':");
            for(ProfileTweet tweet : (List<ProfileTweet>) searchPage.getSearchResult()) {
                String s = '@' + tweet.getOriginalTweeter().getScreenName() + ": \"" + tweet.getMessage() + "\" (" + tweet.getId() + ")\n" +
                        "- " + tweet.getTimestamp() + " | " + tweet.getRetweets() + " Retweets | " + tweet.getLikes() + " Likes\n";
                commandRequest.reply(s);
            }
        } catch (IOException | PageNotFoundException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
