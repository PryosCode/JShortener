package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.LogTable;
import net.pryoscode.jshortener.sql.entities.Link;

@CommandInfo(name = "list", description = "List all shortened Links")
public class list extends Command {

    @Override
    public void onExecute(String[] args) {
        LogTable table = new LogTable("Slug", "URL", "Clicks");
        for (Link link : getDatabase().getLinks())
            table.addRow(link.getSlug(), link.getUrl(), String.valueOf(link.getClicks().size()));
        table.print();
    }

}