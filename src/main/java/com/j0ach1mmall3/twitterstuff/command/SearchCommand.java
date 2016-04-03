package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.search.SearchType;
import com.j0ach1mmall3.twitterscrapeapi.search.SearchUtils;
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
            commandRequest.reply("Results for " + strings[0] + ": " + SearchUtils.search(strings[0], SearchType.DEFAULT_TWEETS).getIds());
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
