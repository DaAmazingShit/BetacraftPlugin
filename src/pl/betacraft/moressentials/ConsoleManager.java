package pl.betacraft.moressentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ConsoleManager {
	private static String loggedIn = null;

	public static void onCommand(ConsoleCommandSender cs, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("say")) {
			if (loggedIn == null) {
				cs.sendMessage("Zaloguj sie do konsoli: /conlogin <nick>");
				return;
			}
			String message = "<&c*" + loggedIn + "&f> ";
			for (int i = 0; i < args.length; i++) {
				message = message + args[i];
			}
			Bukkit.getServer().getLogger().info(message);
			for (Player p: Bukkit.getServer().getOnlinePlayers()) {
				p.sendMessage(message);
			}
			return;
		}
		if (cmd.getName().equalsIgnoreCase("conlogin")) {
			if (args.length != 1) {
				cs.sendMessage("Zaloguj sie do konsoli: /conlogin <nick>");
				return;
			}
			loggedIn = args[0];
			cs.sendMessage("Zalogowano jako " + args[0] + ". Aby wylogowac: /conlogout");
			return;
		}
		if (cmd.getName().equalsIgnoreCase("conlogout")) {
			if (loggedIn == null) {
				cs.sendMessage("Nie zalogowano.");
				return;
			}
			cs.sendMessage("Wylogowano z konta " + loggedIn);
			loggedIn = null;
			return;
		}
	}

}
