package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;

@CommandInfo(name = "exit", description = "Exit the application")
public class exit extends Command {

    @Override
    public void onExecute(String[] args) {
        System.exit(0);
    }

}