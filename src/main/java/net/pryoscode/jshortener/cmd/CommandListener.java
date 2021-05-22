package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.cmd.Command.Argument;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import java.util.Arrays;
import java.util.Scanner;

public class CommandListener {

    private final CommandManager manager;
    private final Thread thread;

    public CommandListener(Database database) {
        manager = new CommandManager();
        for (Command command : manager.getCommands()) {
            command.setDatabase(database);
            command.setCommandManager(manager);
        }
        thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext())
                onSubmit(scanner.nextLine().trim());
            scanner.close();
        });
    }

    public void onSubmit(String text) {
        String[] array = text.split(" ");

        for (Command command : manager.getCommands()) {
            if (command.getName().equalsIgnoreCase(array[0])) {
                int required = 0;
                for (Argument arg : command.getArguments())
                    if (!arg.isOptional())
                        required++;

                if (array.length - 1 >= required)
                    command.onExecute(Arrays.copyOfRange(array, 1, array.length));
                else
                    Log.info(command.toString());
            }
        }
    }

    public void start() {
        thread.start();
    }

}