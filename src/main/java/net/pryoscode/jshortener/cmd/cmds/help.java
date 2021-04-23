package net.pryoscode.jshortener.cmd.cmds;

import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.LogTable;

@CommandInfo(name = "help", description = "List all Commands")
public class help extends Command {

    @Override
    public void onExecute(String[] args) {
        LogTable table = new LogTable("Command", "Description");
        for (Command command : getCommandManager().getCommands())
            table.addRow(command.toString(), command.getDescription());
        table.print();
    }

}