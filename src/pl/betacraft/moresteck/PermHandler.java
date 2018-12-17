package pl.betacraft.moresteck;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermHandler {

	public static boolean has(CommandSender cs, String node) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			return Betacraft.permissions.getHandler().has(p, node) || Betacraft.permissions.getHandler().has(p, "*") ? true : p.isOp();
		}
		return true;
	}
}
