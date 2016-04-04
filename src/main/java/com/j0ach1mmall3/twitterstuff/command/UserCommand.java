package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.AccountSuspendedException;
import com.j0ach1mmall3.twitterscrapeapi.exceptions.PageNotFoundException;
import com.j0ach1mmall3.twitterscrapeapi.pages.user.UserPage;
import com.j0ach1mmall3.twitterscrapeapi.pages.user.intent.id.IdIntentPage;
import com.j0ach1mmall3.twitterscrapeapi.pages.user.intent.screenname.ScreenNameIntentPage;
import com.j0ach1mmall3.twitterscrapeapi.tweet.Tweet;
import com.j0ach1mmall3.twitterscrapeapi.user.TimelineUser;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class UserCommand extends Command {
    public UserCommand() {
        super("Look up information about a Twitter user", "tuser", "tuserstats", "tuserinfo");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <username|userid>");
            return;
        }
        commandRequest.reply("Fetching data of " + strings[0] + "...");
        try {
            UserPage userPage;
            try {
                userPage = new IdIntentPage(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                userPage = new ScreenNameIntentPage(strings[0]);
            }
            userPage.fetchData();
            TimelineUser user = (TimelineUser) userPage.getUser();
            String s = user.getDisplayName() + " - @" + user.getScreenName() + " (" + user.getId() + ")\n" +
                    user.getLocation() + " | " + user.getUrl() + " | " + user.getFollowers() + " Followers | " + user.getFollowing() + " Following\n" +
                    '\"' + user.getStatus() + "\"\n";
            for(Tweet tweet : user.getTimeline()) {
                s += '\"' + tweet.getMessage() + "\" - " + tweet.getTimestamp() + " (" + tweet.getId() + ")\n";
            }
            commandRequest.reply(s);
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        } catch (PageNotFoundException e) {
            commandRequest.reply("Unknown user!");
        } catch (AccountSuspendedException e) {
            commandRequest.reply("This user is suspended!");
        }
    }
}
