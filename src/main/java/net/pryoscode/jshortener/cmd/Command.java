package net.pryoscode.jshortener.cmd;

import java.util.ArrayList;
import java.util.List;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.sql.Database;

public abstract class Command implements Comparable<Command> {

    private final String name;
    private final String description;
    private final List<Argument> arguments = new ArrayList<>();
    private Database database;
    private CommandManager commandManager;

    public Command() {
        CommandInfo info = getClass().getAnnotation(CommandInfo.class);
        name = info.name();
        description = info.description();
        for (CommandArgument argument : info.args())
            arguments.add(new Argument(argument));
    }

    public abstract void onExecute(String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Database getDatabase() {
        return database;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String toString() {
        StringBuilder args = new StringBuilder();
        for (Argument argument : arguments)
            args.append(argument).append(" ");
        return (name + " " + args.toString()).trim();
    }

    @Override
    public int compareTo(Command o) {
        return name.compareTo(o.name);
    }

    public class Argument {

        private String name;
        private boolean optional;

        public Argument(CommandArgument argument) {
            name = argument.value();
            optional = argument.optional();
        }

        public String getName() {
            return name;
        }

        public boolean isOptional() {
            return optional;
        }

        @Override
        public String toString() {
            return (optional ? "[" : "<") + name + (optional ? "]" : ">");
        }

    }

}