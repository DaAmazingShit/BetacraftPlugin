package pl.betacraft.moressentials;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import pl.betacraft.logblock.Data;

public class PL extends PlayerListener {

	@Override
	public void onPlayerChat(PlayerChatEvent e) {
		final Iterator<Player> it = e.getRecipients().iterator();
		while (it.hasNext()) {
			final Player p = it.next();
			if (PDB.ignored(p.getName()).contains(e.getPlayer().getName())) {
				it.remove();
			}
		}
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.getPlayer().setDisplayName(PDB.nick(e.getPlayer().getName()));
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent e) {
		PDB.seen(e.getPlayer().getName(), Data.current());
/*		if (Moressentials.inventories.get(e.getPlayer().getName()) != null) {
			PlayerInventory pi = Moressentials.inventories.get(e.getPlayer().getName());
			e.getPlayer().getInventory().clear();
			e.getPlayer().getInventory().setContents(pi.getContents());
			e.getPlayer().getInventory().setArmorContents(pi.getArmorContents());
			Moressentials.inventories.remove(e.getPlayer().getName());
		}*/
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		for (Player p: Bukkit.getServer().getOnlinePlayers()) {
			if (PDB.commandspy(p.getName())) {
				p.sendMessage(e.getPlayer().getName() + ": " + e.getMessage());
			}
		}
	}

	@Override
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		PDB.back(e.getPlayer(), e.getFrom());
	}
}
