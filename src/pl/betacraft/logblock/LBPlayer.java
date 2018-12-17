package pl.betacraft.logblock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

import pl.betacraft.moresteck.Betacraft;

public class LBPlayer extends PlayerListener {
	public static Map<String, Block> clicked = new HashMap<String, Block>();

	@Override
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) {
			return;
		}
		String p = e.getPlayer().getName();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.LEATHER && Betacraft.permissions.getHandler().has(e.getPlayer(), "logblock.check")) {
			if (clicked.get(p) != null) {
				clicked.remove(p);
			}
			clicked.put(p, e.getClickedBlock());
			e.getPlayer().sendMessage(ChatColor.AQUA + "Co tu sie stalo?:");
			List<String> changes = DBLogger.get(e.getClickedBlock().getLocation());
			if (changes.isEmpty()) {
				e.getPlayer().sendMessage(ChatColor.YELLOW + " Nie wiadomo");
				return;
			}
			for (String h: changes) {
				e.getPlayer().sendMessage(ChatColor.GRAY + h);
			}
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.SOIL && e.getPlayer().getInventory().getItemInHand().getType() == Material.SEEDS) {
			DBLogger.write(e.getClickedBlock().getLocation(), p, "AIR", "SEEDS");
		}
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.JUKEBOX && (e.getPlayer().getInventory().getItemInHand().getType() == Material.GOLD_RECORD || e.getPlayer().getInventory().getItemInHand().getType() == Material.GREEN_RECORD)) {
        	DBLogger.write(e.getClickedBlock().getLocation(), p, "JUKEBOX", "JUKEBOX");
		}
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CAKE_BLOCK) {
        	DBLogger.write(e.getClickedBlock().getLocation(), p, "CAKE_BLOCK", "CAKE_BLOCK");
        }
	}
	
	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		String p = e.getPlayer().getName();
		String msg = e.getMessage();
		Logging.log("Gracz " + p + " probowal wpisac komende: " + msg + ".");
	}
}
