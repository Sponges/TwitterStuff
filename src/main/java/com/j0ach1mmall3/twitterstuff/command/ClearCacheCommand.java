package com.j0ach1mmall3.twitterstuff.command;

import com.j0ach1mmall3.twitterscrapeapi.jsoup.DocumentCache;
import io.sponges.bot.api.cmd.Command;
import io.sponges.bot.api.cmd.CommandRequest;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class ClearCacheCommand extends Command {
    public ClearCacheCommand() {
        super("Clears the downloaded Twitter pages Cache", "tclearcache", "tccache", "tcc");
    }

    @Override
    public void onCommand(CommandRequest commandRequest, String[] strings) {
        if(commandRequest.getUser().isOp() || (commandRequest.getClient().getId().equals("skype") && commandRequest.getUser().getId().equals("joachim.vandersmissen"))) {
            DocumentCache.clearCache();
            commandRequest.reply("Cleared the Cache!");
        }
    }
    
    @Override
    public boolean requiresOp() {
        return true;
    }
}
