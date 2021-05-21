package net.pryoscode.jshortener.cmd.cmds;

import java.util.Random;
import net.pryoscode.jshortener.Config;
import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.cmd.CommandInfo.CommandArgument;
import net.pryoscode.jshortener.log.Log;
import net.pryoscode.jshortener.sql.entities.Link;

@CommandInfo(name = "short", description = "Short the specified URL", args = { @CommandArgument("url"), @CommandArgument(value = "slug", optional = true) })
public class shorten extends Command {

    private static final char[] CHARS = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    @Override
    public void onExecute(String[] args) {
        String slug = "";
        if (args.length > 1)
            slug = args[1];
        else
            for (int i = 0; i <= Config.getShortLength(); i++)
                slug = slug + CHARS[new Random().nextInt(CHARS.length)];

        if (args[0].startsWith("http://") || args[0].startsWith("https://")) {
            if (getDatabase().getLinkBySlug(slug) == null) {
                getDatabase().addLink(new Link(slug, args[0]));
                Log.info(slug + " --> " + args[0]);
            } else {
                Log.info(slug + " already exists.");
            }
        } else {
            Log.info("Please specify a valid URL.");
        }
    }

}