package com.j0ach1mmall3.twitterstuff;

import com.j0ach1mmall3.twitterstuff.command.ClearCacheCommand;
import com.j0ach1mmall3.twitterstuff.command.SearchCommand;
import com.j0ach1mmall3.twitterstuff.command.SearchImagesCommand;
import com.j0ach1mmall3.twitterstuff.command.SearchUsersCommand;
import com.j0ach1mmall3.twitterstuff.command.TweetCommand;
import com.j0ach1mmall3.twitterstuff.command.UserCommand;
import io.sponges.bot.api.cmd.CommandManager;
import io.sponges.bot.api.module.Module;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/04/2016
 */
public final class TwitterStuff extends Module {
    public TwitterStuff() {
        super("TwitterStuff", "1.4.1");
    }

    @Override
    public void onEnable() {
        CommandManager cm = this.getCommandManager();
        cm.registerCommand(this, new ClearCacheCommand());
        cm.registerCommand(this, new SearchCommand());
        cm.registerCommand(this, new SearchUsersCommand());
        cm.registerCommand(this, new SearchImagesCommand());
        cm.registerCommand(this, new TweetCommand());
        cm.registerCommand(this, new UserCommand());
    }

    @Override
    public void onDisable() {

    }
}
