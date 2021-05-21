package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.cmd.cmds.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new help());
        commands.add(new exit());
        commands.add(new list());
        commands.add(new remove());
        commands.add(new shorten());
        commands.add(new version());
        commands.add(new update());

        Collections.sort(commands);
    }

    public List<Command> getCommands() {
        return commands;
    }

}