package de.wi2020h.escaperoom.commands;

import de.wi2020h.escaperoom.Escaperoom;
import de.wi2020h.escaperoom.listeners.ChatInput;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            World pWorld = p.getWorld();

            Location start = new Location(pWorld, 17, 56, -3, 90, 0);
            p.teleport(start);

            Escaperoom.delay(1);
            p.sendMessage(ChatInput.JUSAUR + "Vor dir ist eine Schaltung.");
            Escaperoom.delay(4);
            p.sendMessage(ChatInput.JUSAUR + "Erkenne, um was für eine es sich hierbei handelt.");
            Escaperoom.delay(4);
            p.sendMessage(ChatInput.JUSAUR + "Schreibe die richtige Antwort anschließend in den Chat!");
            Escaperoom.delay(4);

            p.sendMessage(ChatInput.ANSWERS);
        }

        return true;
    }

}
