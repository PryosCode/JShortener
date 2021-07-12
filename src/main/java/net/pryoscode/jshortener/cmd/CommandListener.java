package net.pryoscode.jshortener.cmd;

import net.pryoscode.jshortener.cmd.Command.Argument;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.Database;
import java.util.Arrays;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class CommandListener {

    private final CommandManager manager;
    private final Thread thread;

    public CommandListener(Database database) throws Exception {
        manager = new CommandManager();
        for (Command command : manager.getCommands()) {
            command.setDatabase(database);
            command.setCommandManager(manager);
        }

        Terminal terminal = TerminalBuilder.builder().name("JShortener").build();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        thread = new Thread(() -> {
            try {
                while (true)
                    onSubmit(reader.readLine("> ").trim());
            } catch (UserInterruptException | EndOfFileException ignored) {}
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