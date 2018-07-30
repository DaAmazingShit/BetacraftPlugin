package pl.betacraft.moresteck;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.util.config.Configuration;

public class eventList extends PlayerListener {
	private static Configuration config = new Configuration(new File("plugins/BetaCraft", "sound.yml"));

	@Override
	public void onPlayerChat(PlayerChatEvent e) {
		String[] split = e.getMessage().split(" ");
		for (int x = 0; x < split.length; x++) {
			if (split[x].startsWith("@")) {
				String name = split[x].substring(1);
				Player p = Bukkit.getServer().getPlayer(name);
				if (p == null) {
					split[x] = ChatColor.RED + "@" + name;
				}
				else {
					split[x] = ChatColor.BLUE + "@" + name;
					config.load();
					// nw jak chcesz to dokoncz
					p.playEffect(p.getEyeLocation(), Effect.EXTINGUISH, 10);
				}
			}
		}
		e.setMessage(split.toString());
	}
}
