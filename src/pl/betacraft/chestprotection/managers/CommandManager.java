package pl.betacraft.chestprotection.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import pl.betacraft.chestprotection.commands.CP;
import pl.betacraft.chestprotection.commands.CPReload;

public class CommandManager {

	public static Commands[] getCommands() {
		return Commands.values();
	}

	public enum Commands {

		TYPICAL(0),

		RELOAD(1);

		private int num;

		private Commands(int num) {
			this.num = num;
		}

		public void execute(CommandSender sender, Command cmd, String cmdalias, String[] args) {
			if (num == 1) {
				new CPReload(sender, cmd, cmdalias, args).handle();
			}
			if (num == 0) {
				new CP(sender, cmd, cmdalias, args).handle();
			}
		}
	}
}