package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.search.mobile.users.MobileUsersSearchPage;
import com.j0ach1mmall3.twitterscrapeapi.user.MobileSearchedUser;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class SearchUsersCommand extends Command {
    public SearchUsersCommand() {
        super("Search all of Twitter for a specific user", "searchusers", "tsearchusers", "twittersearchusers", "searchtwitterusers");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <keyword>");
            return;
        }
        try {
            MobileUsersSearchPage searchPage = new MobileUsersSearchPage(strings[0]);
            searchPage.fetchData();
            commandRequest.reply("Results for '" + strings[0] + "':");
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
