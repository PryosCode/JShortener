package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.log.Log;

import java.util.Arrays;
import java.util.Scanner;

public class CommandHandler {

    private final Thread thread;

    public CommandHandler() {
        thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            CommandManager manager = new CommandManager();
            while (true) {
                String[] input = scanner.nextLine().split(" ");
                Command command = manager.getCommandByName(input[0]);
                String[] args = Arrays.copyOfRange(input, 1, input.length);

                if(command != null)
                    command.onExecute(args);
                else
                    Log.info("Command not found.");
            }
        });
    }

    public void start() {
        thread.start();
    }

}