package pl.betacraft.moressentials;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import pl.betacraft.logblock.Data;

public class PL extends PlayerListener {
	public static Map<String, Location> last = new HashMap<String, Location>();
	public static Map<String, Location> pending = new HashMap<String, Location>();

	public void onPlayerQuit(PlayerQuitEvent e) {
		PDB.seen(e.getPlayer().getName(), Data.current());
	}

	public void onPlayerChat(PlayerChatEvent e) {
		
	}

	public void onPlayerTeleport(PlayerTeleportEvent e) {
		
	}
}
