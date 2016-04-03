package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.AccountSuspendedException;
import com.j0ach1mmall3.twitterscrapeapi.exceptions.ProfileProtectedException;
import com.j0ach1mmall3.twitterscrapeapi.user.IdAndScreenName;
import com.j0ach1mmall3.twitterscrapeapi.user.UserUtils;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

import java.io.IOException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class TweetsCommand extends Command {
    public TweetsCommand() {
        super("Fetch the latest tweets of a user", "tweets", "timeline", "usertweets");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(strings.length < 1) {
            commandRequest.reply("Arguments: <username>");
            return;
        }
        commandRequest.reply("Fetching tweets of " + strings[0] + "...");
        IdAndScreenName idAndScreenName;
        try {
            try {
                idAndScreenName = IdAndScreenName.getById(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                idAndScreenName = IdAndScreenName.getByScreenName(strings[0]);
            }
            commandRequest.reply(idAndScreenName + "'s tweets: " + UserUtils.getTimeline(idAndScreenName).toString());
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        } catch (AccountSuspendedException e) {
            commandRequest.reply("This user is suspended!");
        } catch (ProfileProtectedException e) {
            commandRequest.reply("This profile is protected!");
        }
    }
}
