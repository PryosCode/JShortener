package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "help", description = "List all Commands")
public class help extends Command {

    @Override
    public void onExecute(String[] args) {
        for (Command command : getCommandManager().getCommands()) {
            Log.info(command + " - " + command.getDescription());
        }
    }

}