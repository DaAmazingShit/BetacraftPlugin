package pl.betacraft.betacommandblocks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

import com.nijikokun.bukkit.Permissions.Permissions;

public class PLIST extends PlayerListener {

	public static Map<String, Block> selected = new HashMap<String, Block>();

	@Override
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		Block block = e.getClickedBlock();
		if (block.getType() != Material.LAPIS_BLOCK) {
			return;
		}
		if (block.getData() != (byte)2) {
			return;
		}
		if (e.getPlayer().isSneaking()) {
			if (BetaCommandBlocks.config.getProperty("commandblocks." + block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ()) != null) {
				String komenda = BetaCommandBlocks.config.getString("commandblocks." + block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ(), "Cos nie tak z konfiguracja.");
				e.getPlayer().sendMessage(ChatColor.AQUA + " Komenda tego bloku wykonawczego: " + ChatColor.YELLOW + "/" + komenda);
				e.setUseInteractedBlock(Result.DENY);
				e.setUseItemInHand(Result.DENY);
				return;
			}
		}
		if (!Permissions.Security.has(e.getPlayer(), "commandblock.use")) {
			return;
		}
		if (selected.get(e.getPlayer().getName()) != null) {
			selected.remove(e.getPlayer().getName());
		}
		selected.put(e.getPlayer().getName(), block);
		e.getPlayer().sendMessage(ChatColor.AQUA + " [BetaCommandBlocks] Zaznaczono blok wykonawczy.");
		e.setUseInteractedBlock(Result.DENY);
		e.setUseItemInHand(Result.DENY);
	}
}