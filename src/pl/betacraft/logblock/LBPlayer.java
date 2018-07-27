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

import com.nijikokun.bukkit.Permissions.Permissions;

public class LBPlayer extends PlayerListener {
	public static Map<String, Block> clicked = new HashMap<String, Block>();

	@Override
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null) {
			return;
		}
		String p = e.getPlayer().getName();
		//String mat = e.getClickedBlock().getType().name();
		//String hand = e.getPlayer().getInventory().getItemInHand().getType().name();
		
		//if (e.getAction() == Action.LEFT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.LEVER || e.getClickedBlock().getType() == Material.STONE_BUTTON)) {
			//DBLogger.write(e.getClickedBlock().getLocation(), p + " uzyl " + mat);
		//}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.LEATHER && Permissions.Security.has(e.getPlayer(), "logblock.check")) {
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
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.LEVER || e.getClickedBlock().getType() == Material.STONE_BUTTON)) {
			//DBLogger.write(e.getClickedBlock().getLocation(), p + " uzyl " + mat);
		}
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getPlayer().getInventory().getItemInHand().getType() == Material.BREAD || e.getPlayer().getInventory().getItemInHand().getType() == Material.APPLE || e.getPlayer().getInventory().getItemInHand().getType() == Material.GOLDEN_APPLE || e.getPlayer().getInventory().getItemInHand().getType() == Material.COOKED_FISH || e.getPlayer().getInventory().getItemInHand().getType() == Material.RAW_FISH || e.getPlayer().getInventory().getItemInHand().getType() == Material.PORK || e.getPlayer().getInventory().getItemInHand().getType() == Material.GRILLED_PORK || e.getPlayer().getInventory().getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
			//DBLogger.write(e.getClickedBlock().getLocation(), p + " zjadl " + hand);
		}
		if ((e.getAction() == Action.RIGHT_CLICK_AIR) && (e.getPlayer().getInventory().getItemInHand().getType() == Material.BREAD || e.getPlayer().getInventory().getItemInHand().getType() == Material.APPLE || e.getPlayer().getInventory().getItemInHand().getType() == Material.GOLDEN_APPLE || e.getPlayer().getInventory().getItemInHand().getType() == Material.COOKED_FISH || e.getPlayer().getInventory().getItemInHand().getType() == Material.RAW_FISH || e.getPlayer().getInventory().getItemInHand().getType() == Material.PORK || e.getPlayer().getInventory().getItemInHand().getType() == Material.GRILLED_PORK || e.getPlayer().getInventory().getItemInHand().getType() == Material.MUSHROOM_SOUP)) {
			//DBLogger.write(e.getClickedBlock().getLocation(), p + " zjadl " + hand);
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
	
	/*@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		String p = e.getPlayer().getName();
		String entity = e.getRightClicked().toString();
		String x = Integer.toString(e.getRightClicked().getLocation().getBlockX());
		String y = Integer.toString(e.getRightClicked().getLocation().getBlockY());
		String z = Integer.toString(e.getRightClicked().getLocation().getBlockZ());
		String id = Integer.toString(e.getRightClicked().getEntityId());
		String w = e.getRightClicked().getLocation().getWorld().getName();
		Logging.log("Gracz " + p + " kliknal na entity " + entity + ", IDEntity: "+id+", koordy entity: X"+x+", Y"+y+", Z"+z+" Swiat - "+w+".");
	}*/
	
	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		String p = e.getPlayer().getName();
		String msg = e.getMessage();
		Logging.log("Gracz " + p + " probowal wpisac komende: " + msg + ".");
	}
}
