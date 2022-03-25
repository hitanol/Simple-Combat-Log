package me.piggy.simplecombatlog;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CombatLogEvents implements Listener {

    @EventHandler
    public void onTakeDamageEvent(EntityDamageEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            Player p = (Player) e.getEntity();
            CombatLog combatlog = new CombatLog(p);
            combatlog.startCombatLog();
        }
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Simplecombatlog.getInstance().combatloggedplayers.containsKey(p.getUniqueId())) {
            p.setHealth(0);
            Simplecombatlog.getInstance().combatloggedplayers.get(p.getUniqueId()).stopTimer();
        }
    }

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (Simplecombatlog.getInstance().combatloggedplayers.containsKey(p.getUniqueId())) {
            if (Simplecombatlog.getInstance().commands.contains(e.getMessage())) {
                p.sendMessage(ChatColor.BLUE  + "[AntiGallinas] " + ChatColor.RED + "No puedes hacer eso ahora mismo.");
                e.setCancelled(true);
            }
        }
    }

}
