package pl.betacraft.moresteck;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pl.betacraft.dzialecznik.Dzialecznik;
import pl.betacraft.hardcore.Hardcore;
import pl.betacraft.logblock.LogBlock;
import pl.betacraft.sell.Sprzedaj;
import pl.betacraft.wayback.Wayback;

public class Betacraft extends JavaPlugin {

	public void onEnable() {
		LogBlock.onEnable(this);
		Dzialecznik.onEnable();
		Sprzedaj.onEnable();
		Hardcore.onEnable(this);
		Wayback.onEnable(this);
		Bukkit.getLogger().info(" [BetaCraft] Wlaczono, wersja: " + this.getDescription().getVersion());
	}

	public void onDisable() {
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		LogBlock.onCommand(sender, cmd, alias, args);
		Dzialecznik.onCommand(sender, cmd, alias, args);
		Hardcore.onCommand(sender, cmd, alias, args);
		try {
			Sprzedaj.onCommand(sender, cmd, alias, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}