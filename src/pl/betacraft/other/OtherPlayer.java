package pl.betacraft.other;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.config.Configuration;

public class OtherPlayer extends PlayerListener {
	private static Configuration config = new Configuration(new File("plugins/BetaCraft", "wbili"));

	@Override
	public void onPlayerChat(PlayerChatEvent e) {
		// AdminChat
		if (Other.adminchat.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			String msg = String.format(e.getFormat(), e.getPlayer().getDisplayName(),
					e.getMessage());
			Other.sendAdminmsg(msg);
		}

		// SoundEffect
		String[] split = e.getMessage().split(" ");
		for (int x = 0; x < split.length; x++) {
			if (split[x].startsWith("@")) {
				String name = split[x].substring(1);
				Player p = Bukkit.getServer().getPlayer(name);
				if (p != null) {
					p.playEffect(p.getEyeLocation(), Effect.SMOKE, 10);
					p.playEffect(p.getEyeLocation(), Effect.EXTINGUISH, 10);
					p.playEffect(p.getEyeLocation(), Effect.CLICK2, 10);
				}
			}
		}
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent e) {
		// first spawn fix
		config.load();
		if (config.getStringList("no", new LinkedList<String>()).contains(e.getPlayer().getName())) {
			return;
		}
		List<String> no = new LinkedList<String>();
		no.addAll(config.getStringList("no", new LinkedList<String>()));
		no.add(e.getPlayer().getName());
		config.setProperty("no", no);
		config.save();
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
	}
}
