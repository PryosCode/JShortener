package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "list", description = "List all shortened Links")
public class list extends Command {

    @Override
    public void onExecute(String[] args) {
        Log.warning("TODO");
    }

}