package com.j0ach1mmall3.twitterstuff;

import com.j0ach1mmall3.twitterstuff.command.SearchCommand;
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
        super("TwitterStuff", "1.0-SNAPSHOT");
    }

    @Override
    public void onEnable() {
        CommandManager cm = this.getCommandManager();
        cm.registerCommand(this, new SearchCommand());
        cm.registerCommand(this, new TweetCommand());
        cm.registerCommand(this, new UserCommand());
    }

    @Override
    public void onDisable() {

    }
}
