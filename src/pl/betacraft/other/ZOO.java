package pl.betacraft.other;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ZOO extends PlayerListener {
	private static List<String> seeing = new LinkedList<String>();
	private static List<String> inZoo = new LinkedList<String>();

	public void onPlayerChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (inZoo.contains(p.getName())) {
			System.out.println(e.getFormat());
			for (String s: seeing) {
				Player see = Bukkit.getServer().getPlayer(s);
				if (see != null) {
					see.sendMessage(e.getFormat());
				}
			}
			e.setCancelled(true);
		}
	}

	public static void addToZoo(String player) {
		if (!inZoo.contains(player)) {
			inZoo.add(player);
		}
		allowSee(player);
	}

	public static void removeFromZoo(String player) {
		if (inZoo.contains(player)) {
			inZoo.remove(player);
		}
		denySee(player);
	}

	public static void allowSee(String player) {
		if (!seeing.contains(player)) {
			seeing.add(player);
		}
	}

	public static void denySee(String player) {
		if (seeing.contains(player)) {
			seeing.remove(player);
		}
	}

	public static boolean canSee(String player) {
		if (seeing.contains(player)) {
			return true;
		}
		return false;
	}
}
