package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;

@CommandInfo(name = "short")
public class Short extends Command {

    @Override
    public void onExecute(String[] args) {
        System.out.println("Hello World!");
    }

}