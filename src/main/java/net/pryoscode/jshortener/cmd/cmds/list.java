package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.entities.Link;

@CommandInfo(name = "list", description = "List all shortened Links")
public class list extends Command {

    @Override
    public void onExecute(String[] args) {
        for (Link link : getDatabase().getLinks()) {
            Log.info(link.getSlug() + " --> " + link.getUrl());
        }
    }

}