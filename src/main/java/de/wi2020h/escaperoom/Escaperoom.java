package de.wi2020h.escaperoom;

import de.wi2020h.escaperoom.commands.Start;
import de.wi2020h.escaperoom.listeners.ChatInput;
import de.wi2020h.escaperoom.listeners.DropNBreak;
import de.wi2020h.escaperoom.listeners.Playerjoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public final class Escaperoom extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new DropNBreak(), this);
        getServer().getPluginManager().registerEvents(new ChatInput(), this);
        getServer().getPluginManager().registerEvents(new Playerjoin(), this);

        this.getCommand("start").setExecutor(new Start());

        log("Plugin geladen");
    }

    @Override
    public void onDisable() {
        log("Plugin entladen");
    }


    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    /**
     * Waits a given time
     *
     * @param dur: Time of delay in seconds.
     */


    public static void delay(int dur) {
        long end = (new Date().getTime() + (dur * 1000));
        while (new Date().getTime() < end) {

        }
    }


}
