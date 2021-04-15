package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.cmd.cmds.help;
import net.pryoscode.jshortener.cmd.cmds.list;
import net.pryoscode.jshortener.cmd.cmds.remove;
import net.pryoscode.jshortener.cmd.cmds.shorten;
import net.pryoscode.jshortener.cmd.cmds.update;
import net.pryoscode.jshortener.cmd.cmds.version;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new help());
        commands.add(new remove());
        commands.add(new list());
        commands.add(new shorten());
        commands.add(new update());
        commands.add(new version());
    }

    public List<Command> getCommands() {
        return commands;
    }

}