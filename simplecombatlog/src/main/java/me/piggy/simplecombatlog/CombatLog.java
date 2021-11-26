package me.piggy.simplecombatlog;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class CombatLog {

    private Player p;

    private int time;
    private int taskID;

    public CombatLog(Player p) {

        this.p = p;

    }

    public void startCombatLog() {
        Simplecombatlog.getInstance().combatloggedplayers.put(p.getUniqueId(), this);
        p.sendMessage(ChatColor.BLUE + "[SimpleCombatLog] " + ChatColor.RED + "You Have Been Damaged DO NOT LOG OUT!");
        stopTimer();
        startCountdown();
    }

    private void startCountdown() {

        time = Simplecombatlog.getInstance().totaltime;

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Simplecombatlog.getInstance(), () -> {
            if (time == 0) {
                Simplecombatlog.getInstance().combatloggedplayers.remove(p.getUniqueId());
                p.sendMessage(ChatColor.BLUE + "[SimpleCombatLog] " + ChatColor.GREEN + "You Are Now Safe You May Log Out.");
                stopTimer();
            }
            time = time - 1;
        }, 0L, 20L);
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
