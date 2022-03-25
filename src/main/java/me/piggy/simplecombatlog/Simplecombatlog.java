package me.piggy.simplecombatlog;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Simplecombatlog extends JavaPlugin {

    public static Simplecombatlog instance;

    public HashMap<UUID, CombatLog> combatloggedplayers = new HashMap<>();

    public int totaltime;
    public List<String> commands = new ArrayList<>();


    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new CombatLogEvents(), this);

        totaltime = getConfig().getInt("time");
        commands = getConfig().getStringList("commands");

    }

    @Override
    public void onDisable() {

    }

    public static Simplecombatlog getInstance() {
        return instance;
    }
}
