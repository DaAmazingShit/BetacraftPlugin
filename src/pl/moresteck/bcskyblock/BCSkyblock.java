package pl.moresteck.bcskyblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BCSkyblock {

	public static void onEnable() {
		
	}

	public static void onDisable() {
		
	}

	public static void onCommand(CommandSender cs, Command cmd, String alias, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sb")) {
			(new SkyblockCommand(args)).parseCommand();
		}
	}
}
