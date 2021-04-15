package net.pryoscode.jshortener.cmd.cmds;

import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.pryoscode.jshortener.cmd.Command;
import net.pryoscode.jshortener.cmd.CommandInfo;
import net.pryoscode.jshortener.log.Log;

@CommandInfo(name = "update", description = "Check for updates")
public class update extends Command {

    @Override
    public void onExecute(String[] args) {
        try {
            Scanner scanner = new Scanner(
                    new URL("https://api.github.com/repos/PryosCode/JShortener/releases").openStream());
            StringBuffer builder = new StringBuffer();
            while (scanner.hasNext())
                builder.append(scanner.next());
            JsonArray array = new Gson().fromJson(builder.toString(), JsonArray.class);

            if (array.size() > 0) {
                JsonObject object = array.get(0).getAsJsonObject();
                String current = getClass().getPackage().getImplementationVersion();
                String github = object.get("tag_name").getAsString().replace("v", "");
                Log.info("Current Version " + current);
                Log.info("GitHub Version " + github);
                if (current.equals(github)) {
                    Log.info("You have the newest version.");
                } else {
                    Log.info("There is a new version available.");
                    Log.info(object.get("html_url").getAsString());
                }
            } else {
                Log.info("There are no releases in this repository.");
            }
        } catch (Exception e) {
            Log.severe(e);
        }
    }

}