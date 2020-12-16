package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.cmd.cmds.Short;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new Short());
    }

    private void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Command getCommandByName(String name) {
        for(Command command : commands)
            if(command.getName().equalsIgnoreCase(name))
                return command;
        return null;
    }

}