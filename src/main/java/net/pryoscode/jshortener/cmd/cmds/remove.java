package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "remove", description = "Remove shortened Link", args = { @CommandArgument("slug") })
public class remove extends Command {

    @Override
    public void onExecute(String[] args) {
        Log.info("TODO");
    }

}