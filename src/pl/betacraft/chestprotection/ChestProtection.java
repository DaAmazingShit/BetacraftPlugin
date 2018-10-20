package pl.betacraft.chestprotection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.betacraft.chestprotection.listeners.Blocks;
import pl.betacraft.chestprotection.listeners.Explosions;
import pl.betacraft.chestprotection.listeners.Players;
import pl.betacraft.chestprotection.managers.CommandManager;
import pl.betacraft.chestprotection.managers.CommandManager.Commands;
import pl.betacraft.chestprotection.managers.ConfigManager;
import pl.betacraft.chestprotection.managers.LanguageManager;
/**
 * Main ChestProtection class
 * @author Moresteck
 */
public class ChestProtection {

	public static Plugin instance;
	public static LanguageManager lang;
	public static String prefix = "[ChestProtection] ";
	public static CraftServer server;

	public static void onEnable(JavaPlugin instanc) {
		server = (CraftServer)instanc.getServer();
		// Disable for BetaCraft
		if (server.getIp().equals("betacraft.ovh")) {
			return;
		}
		lang   = new LanguageManager();
		instance = instanc;
		if (!ConfigManager.configExists()) {
			ConfigManager.createConfig();
		}
		lang.setup();
		
		PluginManager pm = instanc.getServer().getPluginManager();
		pm.registerEvent(Type.BLOCK_IGNITE,    new Blocks(),     Priority.Normal,  instanc);
		pm.registerEvent(Type.BLOCK_PLACE,     new Blocks(),     Priority.Normal,  instanc);
		pm.registerEvent(Type.BLOCK_DAMAGE,    new Blocks(),     Priority.Normal,  instanc);
		pm.registerEvent(Type.BLOCK_BREAK,     new Blocks(),     Priority.Normal,  instanc);
		
		pm.registerEvent(Type.PLAYER_INTERACT, new Players(),    Priority.Normal,  instanc);
		
		pm.registerEvent(Type.ENTITY_EXPLODE,  new Explosions(), Priority.Normal,  instanc);

		Bukkit.getServer().getLogger().info(" [BetaCraft] ChestProtection: wlaczony.");
	}

	/**
	 * Gets the version of the ChestProtection.
	 */
	public static String getVersion() {
		return instance.getDescription().getVersion();
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String cmdalias, String[] args) {
		// Disable for BetaCraft
		if (server.getIp().equals("betacraft.ovh")) {
			return true;
		}

		for (Commands c: CommandManager.getCommands()) {
			c.execute(sender, cmd, cmdalias, args);
		}
		return true;
	}
}
