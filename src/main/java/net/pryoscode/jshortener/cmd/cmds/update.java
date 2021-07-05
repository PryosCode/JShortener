package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.entities.Link;

@CommandInfo(name = "update", description = "Update the URL of a Link", args = { @CommandArgument("slug"), @CommandArgument("url") })
public class update extends Command {

    @Override
    public void onExecute(String[] args) {
        String slug = args[0];
        String url = args[1];
        if (url.startsWith("http://") || url.startsWith("https://")) {
            Link link = getDatabase().getLinkBySlug(slug);
            if (link == null) {
                Log.info(slug + " doesn't exists.");
            } else {
                link.setUrl(url);
                getDatabase().persistLink(link);
                Log.info(slug + " --> " + url);
            }
        } else {
            Log.info("Please specify a valid URL.");
        }
    }

}