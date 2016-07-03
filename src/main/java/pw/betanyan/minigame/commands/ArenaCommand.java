package pw.betanyan.minigame.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.betanyan.minigame.Minigame;
import pw.betanyan.minigame.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaCommand implements CommandExecutor {

    private final Minigame plugin = Minigame.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("arena")) {

            if (sender instanceof Player) {

                Player p = (Player) sender;

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
                            plugin.getConfig().set("arenas." + arenaName + ".lobbyspawn", "NOEXIST");
                            plugin.getConfig().set("arenas." + arenaName + ".spawns", new ArrayList<>());
                            plugin.getConfig().set("arenas." + arenaName + ".maxplayers", 4);
                            plugin.getConfig().set("arenas." + arenaName + ".sign", "NOEXIST");

                            plugin.saveConfig();

                            new Arena(arenaName, null, new ArrayList<>(), 4, null);

                            sender.sendMessage(plugin.chatColor("&aSuccessfully created arena &b" + arenaName));

                        }

                    } else if (args[0].equalsIgnoreCase("remove")) {

                        String arenaName = args[1];

                        if (plugin.getArenaManager().getArenaByName(arenaName) != null) {

                            plugin.getConfig().set("arenas." + arenaName, null);
                            plugin.saveConfig();

                            plugin.getArenaManager().removeArena(
                                    plugin.getArenaManager().getArenaByName(arenaName)
                            );

                            sender.sendMessage(plugin.chatColor("&aSuccessfully removed arena &b" + arenaName));

                        } else {

                            sender.sendMessage(plugin.chatColor("&cThis arena does not exist!"));

                        }

                    } else if (args[0].equalsIgnoreCase("addspawn")) {

                        String arenaName = args[1];

                        Arena arenaObj = plugin.getArenaManager().getArenaByName(arenaName);

                        if (arenaObj != null) {

                            if (arenaObj.getSpawns().size() < arenaObj.getMaxPlayers()) {
                                arenaObj.addSpawn(p.getLocation());
                                p.sendMessage(plugin.chatColor("&aSuccessfully added spawn #" + arenaObj.getSpawns().size() + " to " + arenaName));

                                List<String> currentSpawns = plugin.getConfig().getStringList("arenas." + arenaName + ".spawns");
                                currentSpawns.add(plugin.serializeLocation(p.getLocation()));

                                plugin.getConfig().set("arenas." + arenaName + ".spawns", currentSpawns);
                                plugin.saveConfig();
                            } else {
                                p.sendMessage(plugin.chatColor("&cYou already have enough spawns!"));
                            }

                        } else {

                            sender.sendMessage(plugin.chatColor("&cThis arena does not exist!"));

                        }

                    } else if (args[0].equalsIgnoreCase("setlobbyspawn")) {

                        String arenaName = args[1];

                        Arena arenaObj = plugin.getArenaManager().getArenaByName(arenaName);

                        if (arenaObj != null) {

                            arenaObj.setLobbySpawn(p.getLocation());
                            p.sendMessage(plugin.chatColor("&aSuccessfully set lobby spawn for " + arenaName));

                            plugin.getConfig().set("arenas." + arenaName + ".lobbyspawn",
                                    plugin.serializeLocation(p.getLocation()));
                            plugin.saveConfig();

                        } else {

                            sender.sendMessage(plugin.chatColor("&cThis arena does not exist!"));

                        }

                    } else {

                        sender.sendMessage(getHelpMessage());

                    }

                }

            }

        }

        return false;
    }


    public String getHelpMessage() {

        StringBuilder builder = new StringBuilder("&b&m----------------&r &aArena Commands&r &b&m----------------\n");

        builder.append("&b/arena create <name> - &aCreate an arena.\n");
        builder.append("&b/arena remove <name> - &aRemove an arena.\n");
        builder.append("&b/arena addspawn <name> - &aAdd a spawn to an arena.\n");
        builder.append("&b/arena setlobbyspawn <name> - &aSet the lobby spawn for an arena.");

        return plugin.chatColor(builder.toString());

    }

}
