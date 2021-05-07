package de.wi2020h.escaperoom.listeners;

import de.wi2020h.escaperoom.Escaperoom;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;


public class ChatInput implements Listener {

    public static final String YOU = "§l§2[Du]: §7§o";
    public static final String JUSAUR = "§l§4[Justus Aurelius]: §7§o";
    public static final String ANSWERS = JUSAUR + System.lineSeparator() + "Folgende Antworten sind möglich: "
            + System.lineSeparator() + "§b" + "A: a -> b " + System.lineSeparator() + "B: a <-> b "
            + System.lineSeparator() + "C: !a ^ b" + System.lineSeparator() + "D: (!a ^ b) v (a ^ !b)";
    static int layer = 1;
    String[] order = new String[3];
    final String[] CORRECT_ORDER = {"D", "A", "C"};


    @EventHandler
    public void onChatting(PlayerChatEvent event) {

        Player p = (Player) event.getPlayer();
        Location start = new Location(p.getWorld(), 17, 56, -3, 90, 0);

        String message = event.getMessage();
        p.sendMessage(YOU + message); // p.getServer().broadcastMessage(du + message);
        event.setCancelled(true);

        if (event.getMessage().toString().equalsIgnoreCase("A") ||
                event.getMessage().toString().equalsIgnoreCase("B") ||
                event.getMessage().toString().equalsIgnoreCase("C") ||
                event.getMessage().toString().equalsIgnoreCase("D")) {

            if (layer < 3) handleSubmitAction(p, message);
            else handleFinalSubmitAction(p, message, start);
        }
    }

    /**
     * Compares the order of answers by a player with the expected order
     *
     * @return true, if answers are correct
     */
    private boolean isCorrect() {
        boolean isCorrect = true;

        for (int j = 0; j < 3; j++) {
            if (!order[j].equalsIgnoreCase(CORRECT_ORDER[j])) {
                isCorrect = false;
            }
        }
        return isCorrect;
    }

    /**
     * Saves answer of the player and teleports him to next level
     *
     * @param p:   Player that submitted the action
     * @param mes: Answer of the Player
     */
    private void handleSubmitAction(Player p, String mes) {
        order[layer - 1] = mes;

        int locX = 17;
        int locY = p.getLocation().getBlockY() + 4;
        int locZ = -3;
        World playerWorld = p.getWorld();

        Escaperoom.delay(1);

        Location newLocation = new Location(playerWorld, locX, locY, locZ, 90, 0);
        float yaw = 90;
        float pitch = 0;
        p.teleport(newLocation);

        layer += 1;

        p.sendMessage("§4§l---EBENE " + layer + "---");
        p.sendMessage(JUSAUR + "Dein Ergebnis war: " + order[layer - 2]);
        p.sendMessage(ANSWERS);
    }


    /**
     * Chooses between handling the correct or incorrect answers
     *
     * @param p:   Player that submitted the action
     * @param mes: Answer of the Player
     * @param loc: Initial location of the Player
     */
    private void handleFinalSubmitAction(Player p, String mes, Location loc) {
        order[2] = mes;

        p.sendMessage(JUSAUR + "Deine Antworten waren: " + order[0].toUpperCase() + " " +
                order[1].toUpperCase() + " " + order[2].toUpperCase());

        Escaperoom.delay(2);

        if (!isCorrect()) handleIncorrectOrder(p, loc);
        else handleCorrectOrder(p);
    }

    /**
     * Resets player and order array to initial state
     *
     * @param p:   Player that submitted the action
     * @param loc: Initial location of the Player
     */

    private void handleIncorrectOrder(Player p, Location loc) {
        p.sendMessage(JUSAUR + "Das war leider falsch!");
        Escaperoom.delay(3);
        p.sendMessage(JUSAUR + "Probiere es nochmal!");
        Escaperoom.delay(2);
        layer = 1;
        order[0] = null;
        order[1] = null;
        order[2] = null;
        p.teleport(loc);
    }

    /**
     * Gives the player a code
     *
     * @param p: Player that submitted the action
     */
    private void handleCorrectOrder(Player p) {
        p.sendMessage(JUSAUR + "Glückwunsch! Du hast es geschafft.");
        Escaperoom.delay(3);
        p.sendMessage(JUSAUR + "Damit bist du dem Ausbruch schon ein Stück näher.");
        Escaperoom.delay(3);
        p.sendMessage(JUSAUR + "Hier ist dein Code: §l§6187");
        Escaperoom.delay(3);
        p.sendMessage(JUSAUR + "Kehre zum Hauptspiel zurück und folge weiteren Anweisungen!");
        Escaperoom.delay(2);
    }

}
