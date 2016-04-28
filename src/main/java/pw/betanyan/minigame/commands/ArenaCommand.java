package pw.betanyan.minigame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

import java.util.ArrayList;

public class ArenaCommand implements CommandExecutor {

    private final Minigame plugin = Minigame.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("arena")) {

            if (args.length == 0) {

                sender.sendMessage(getHelpMessage());

            } else if (args.length == 1) {

                sender.sendMessage(getHelpMessage());

            } else if (args.length == 2) {

                if (args[0].equalsIgnoreCase("create")) {

                    String arenaName = args[1];

                    if (plugin.getArenaManager().getArenaByName(arenaName) != null) {

                        sender.sendMessage(plugin.chatColor("&cThis arena already exists!"));

                    } else {

                        plugin.getConfig().set("arenas." + arenaName, "");
                        plugin.getConfig().set("arenas." + arenaName + ".spawns", new ArrayList<>());
                        plugin.getConfig().set("arenas." + arenaName + ".maxplayers", 4);
                        plugin.getConfig().set("arenas." + arenaName + ".sign", "NOEXIST");

                        plugin.saveConfig();

                        new Arena(arenaName, 4, null);

                        sender.sendMessage(plugin.chatColor("&aSuccessfully created arena &b" + arenaName));

                    }

                } else {

                    sender.sendMessage(getHelpMessage());

                }

            }

        }

        return false;
    }


    public String getHelpMessage() {

        StringBuilder builder = new StringBuilder("&b&m---------------- &r&aArena Commands&r &b&m----------------\n");

        builder.append("&b/arena create <name> - &aCreate an arena.\n");

        return plugin.chatColor(builder.toString());

    }

}
