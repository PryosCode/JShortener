package net.pryoscode.jshortener.cmd;

public abstract class Command {

    private String name;

    public Command() {
        CommandInfo info = getClass().getAnnotation(CommandInfo.class);
        this.name = info.name();
    }

    public abstract void onExecute(String[] args);

    public String getName() {
        return name;
    }

}