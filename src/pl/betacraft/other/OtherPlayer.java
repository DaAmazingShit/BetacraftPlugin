package pl.betacraft.other;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class OtherPlayer extends PlayerListener {

	@Override
	public void onPlayerChat(PlayerChatEvent e) {

		// SoundEffect
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
					p.playEffect(p.getEyeLocation(), Effect.SMOKE, 10);
					p.playEffect(p.getEyeLocation(), Effect.EXTINGUISH, 10);
					p.playEffect(p.getEyeLocation(), Effect.CLICK2, 10);
				}
			}
		}
		e.setMessage(split.toString());
	}
}
