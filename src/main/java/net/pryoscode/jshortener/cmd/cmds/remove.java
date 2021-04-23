package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.entities.Link;

@CommandInfo(name = "remove", description = "Remove shortened Link", args = { @CommandArgument("slug") })
public class remove extends Command {

    @Override
    public void onExecute(String[] args) {
        Link link = getDatabase().getLinkBySlug(args[0]);
        if (link == null) {
            Log.info(args[0] + " doesn't exist.");
        } else {
            getDatabase().removeLink(link);
            Log.info("Removed " + link.getSlug() + ".");
        }
    }

}