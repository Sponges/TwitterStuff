package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.exceptions.AccountSuspendedException;
import com.j0ach1mmall3.twitterscrapeapi.exceptions.ProfileProtectedException;
import com.j0ach1mmall3.twitterscrapeapi.user.User;
import com.j0ach1mmall3.twitterscrapeapi.user.UserUtils;
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
            commandRequest.reply("Arguments: <username>");
            return;
        }
        commandRequest.reply("Fetching data of " + strings[0] + "...");
        User user;
        try {
            try {
                user = new User(Long.valueOf(strings[0]));
            } catch (NumberFormatException e) {
                user = new User(UserUtils.getIdBySreenName(strings[0]));
            }
            user.fetchData();
            commandRequest.reply(user.toPrettyString());
        } catch (IOException e) {
            commandRequest.reply("Uh oh, an error occured!\n" + e.getMessage());
        } catch (AccountSuspendedException e) {
            commandRequest.reply("This user is suspended!");
        } catch (ProfileProtectedException e) {
            commandRequest.reply("This profile is protected!");
        }
    }
}
