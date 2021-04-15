package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "short", description = "Short the specified URL", args = { @CommandArgument("url"),
        @CommandArgument(value = "slug", optional = true) })
public class shorten extends Command {

    @Override
    public void onExecute(String[] args) {
        Log.info("TODO");
    }

}