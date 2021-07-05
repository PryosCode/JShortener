package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "version", description = "Show Build version")
public class version extends Command {

    @Override
    public void onExecute(String[] args) {
        Log.info("JShortener v" + getClass().getPackage().getImplementationVersion());
    }

}