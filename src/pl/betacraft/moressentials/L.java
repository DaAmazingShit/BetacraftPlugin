package pl.betacraft.moressentials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.PlayerInventory;

import pl.betacraft.logblock.Data;

public class L implements Listener {

	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().setDisplayName(PDB.nick(e.getPlayer().getName()));
	}

	public void onPlayerQuit(PlayerQuitEvent e) {
		PDB.seen(e.getPlayer().getName(), Data.current());
		if (Moressentials.inventories.get(e.getPlayer().getName()) != null) {
			PlayerInventory pi = Moressentials.inventories.get(e.getPlayer().getName());
			e.getPlayer().getInventory().clear();
			e.getPlayer().getInventory().setContents(pi.getContents());
			e.getPlayer().getInventory().setArmorContents(pi.getArmorContents());
			Moressentials.inventories.remove(e.getPlayer().getName());
		}
	}

	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		for (Player p: Bukkit.getServer().getOnlinePlayers()) {
			if (PDB.commandspy(p.getName())) {
				p.sendMessage(e.getPlayer().getName() + ": " + e.getMessage());
			}
		}
	}

	public void onPlayerTeleport(PlayerTeleportEvent e) {
		PDB.back(e.getPlayer(), e.getFrom());
	}

	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		if (PDB.god(p.getName())) {
			e.setDamage(0);
			p.setFireTicks(0);
		}
	}
}