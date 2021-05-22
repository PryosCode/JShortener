package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "exit", description = "Exit the application")
public class exit extends Command {

    @Override
    public void onExecute(String[] args) {
        Log.info("Bye");
        System.exit(0);
    }

}