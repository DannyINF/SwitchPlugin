package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import util.STATIC;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (STATIC.isStarted())
            sender.sendMessage(STATIC.PREFIX + ChatColor.RED + STATIC.getLang().getString("start.error" + STATIC.getLanguage()));
        else {
            STATIC.setStarted(true);
            Bukkit.getServer().broadcastMessage(STATIC.PREFIX + ChatColor.GREEN + STATIC.getLang().getString("start.success" + STATIC.getLanguage()));

            STATIC.setExec(new BukkitRunnable() {

                @Override
                public void run() {
                    switchPlayers();
                }

            }.runTaskTimer(
                    STATIC.getPlugin(),
                    (STATIC.getPlugin().getConfig().getInt("time.delay") - (STATIC.getPlugin().getConfig().getInt("time.countdown") + 1)) * 20,
                    STATIC.getPlugin().getConfig().getInt("time.delay") * 20));
        }
        return true;
    }

    private void switchPlayers() {
        final AtomicInteger i = new AtomicInteger(STATIC.getPlugin().getConfig().getInt("time.countdown"));

        STATIC.setTimer(new BukkitRunnable() {

            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(STATIC.PREFIX + ChatColor.RED + STATIC.getLang().getString("countdown" + STATIC.getLanguage()).replace("[TIME_REMAINING]", "" + ChatColor.BOLD + i.get() + ChatColor.RESET + ChatColor.RED + ""));
                System.out.println(i.get());
                if (i.addAndGet(-1) < 1) {
                    STATIC.setTeleport(new BukkitRunnable() {

                        @Override
                        public void run() {
                            Collection<? extends Player> players;
                            players = Bukkit.getOnlinePlayers();

                            List<Location> player_locations = new ArrayList<>();
                            List<Vector> player_velocity = new ArrayList<>();

                            players.forEach(player -> player_locations.add(player.getLocation()));
                            players.forEach(player -> player_velocity.add(player.getVelocity()));

                            Collections.reverse(player_locations);
                            Collections.reverse(player_velocity);

                            for (Player player : players) {
                                player.teleport(player_locations.get(0));
                                player.setVelocity(player_velocity.get(0));

                                player_locations.remove(0);
                                player_velocity.remove(0);

                                Collections.reverse(player_locations);
                                Collections.reverse(player_velocity);
                            }

                            Bukkit.getServer().broadcastMessage(STATIC.PREFIX + ChatColor.RED + STATIC.getLang().getString("switch" + STATIC.getLanguage()));
                            STATIC.getTeleport().cancel();
                        }

                    }.runTaskTimer(STATIC.getPlugin(), 20, 20));
                    STATIC.getTimer().cancel();
                }
            }

        }.runTaskTimer(STATIC.getPlugin(), 20, 20));


    }
}