package com.j0ach1mmall3.twitterstuff.command;

import java.io.IOException;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.mobile.images.MobileImagesSearchPage;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class SearchImagesCommand extends Command {
    public SearchImagesCommand() {
        super("Search all of Twitter for specific images", "tsearchimages");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <keyword>");
            return;
        }
        try {
            MobileImagesSearchPage searchPage = new MobileImagesSearchPage(strings[0]);
            searchPage.fetchData();
            String s = "";
            if(searchPage.getSearchResult().size() < 3) {
                for(String image : searchPage.getSearchResult()) {
                    s += image + '\n';
                }
            } else {
                for(int i = 0;i < 3;i++) {
                    s += searchPage.getSearchResult().get(i) + '\n';
                }
            }
            commandRequest.reply(s);
        } catch (IOException | PageNotFoundException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
