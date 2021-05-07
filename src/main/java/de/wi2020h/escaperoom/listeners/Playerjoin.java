package de.wi2020h.escaperoom.listeners;

import de.wi2020h.escaperoom.Escaperoom;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Playerjoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {


        Player p = e.getPlayer();
        e.setJoinMessage(null);

        Location spawn = new Location(p.getWorld(), 45, 89, -3);
        p.teleport(spawn);


        Thread writeMessages = new Thread(new Message(p));
        writeMessages.start();

    }
}

class Message implements Runnable {
    Player p;

    public Message(Player p) {
        this.p = p;
    }

    /**
     * Displays starting messages to the player
     *
     * @param p: Player, whom the messages should be shown
     */
    private void displayMessages(Player p) {

        TextComponent message = new TextComponent("Willst du starten?");
        message.setColor(ChatColor.GOLD);
        message.setBold(true);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/start"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Hier klicken um zu starten!").color(ChatColor.RED).italic(true).create()));

        Escaperoom.delay(4);

        p.sendMessage(ChatInput.JUSAUR + "Wilkommen im Gefängnis der Hardwarekomponenten.");
        Escaperoom.delay(3);
        p.sendMessage(ChatInput.JUSAUR + "Du bist gefangen in einem Super-Computer!");
        Escaperoom.delay(3);
        p.sendMessage(ChatInput.JUSAUR + "Löse folgende Aufgaben, um dem Albtraum zu entkommen!");
        Escaperoom.delay(3);
        p.sendMessage(ChatInput.JUSAUR + "Viel Glück! :)");
        Escaperoom.delay(3);

        p.spigot().sendMessage(message);
    }

    @Override
    public void run() {
        displayMessages(this.p);
    }
}


