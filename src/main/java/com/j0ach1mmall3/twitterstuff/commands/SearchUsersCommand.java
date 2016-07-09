package com.j0ach1mmall3.twitterstuff.commands;

import java.io.IOException;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.mobile.users.MobileUsersSearchPage;
import com.j0ach1mmall3.twitterscrapeapi.user.MobileSearchedUser;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class SearchUsersCommand extends Command {
    public SearchUsersCommand() {
        super("Search all of Twitter for a specific user", "tsearchusers");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <keywords>");
            return;
        }
        try {
            String keywords = "";
            for(String s : strings) {
                keywords += s + ' ';
            }
            MobileUsersSearchPage searchPage = new MobileUsersSearchPage(keywords);
            searchPage.fetchData();
            String s = "";
            if(searchPage.getSearchResult().size() < 3) {
                for(MobileSearchedUser mobileSearchedUser : searchPage.getSearchResult()) {
                    s += '@' + mobileSearchedUser.getScreenName() + " - " + mobileSearchedUser.getDisplayName() + (mobileSearchedUser.isVerified() ? " <Verified> " : "") + '\n';
                }
            } else {
                for(int i = 0;i < 3;i++) {
                    MobileSearchedUser mobileSearchedUser = searchPage.getSearchResult().get(i);
                    s += '@' + mobileSearchedUser.getScreenName() + " - " + mobileSearchedUser.getDisplayName() + (mobileSearchedUser.isVerified() ? " <Verified> " : "") + '\n';
                }
            }
            commandRequest.reply(s);
        } catch (IOException | PageNotFoundException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        }
    }
}
