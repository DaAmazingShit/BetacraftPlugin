package pl.betacraft.moresteck;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;

import pl.betacraft.betacommandblocks.BetaCommandBlocks;
import pl.betacraft.chestprotection.ChestProtection;
import pl.betacraft.dzialecznik.Dzialecznik;
import pl.betacraft.hardcore.Hdb;
import pl.betacraft.hardcore.SpongeBlock;
import pl.betacraft.logblock.LogBlock;
import pl.betacraft.moblimit.MobLimit;
import pl.betacraft.moressentials.Moressentials;
import pl.betacraft.other.Other;

public class Betacraft extends JavaPlugin {
	public static Permissions permissions;
	/*
	 * Permissions:
	 * 
	 * ChestProtection:
	 * - cp.admin.info
	 * - cp.admin.access
	 * - cp.admin.reload
	 * - cp.admin.remove.others
	 * - cp.use.create
	 * - cp.use.info
	 * - cp.use.player.add
	 * - cp.use.player.remove
	 * - cp.use.remove.self
	 * 
	 * Mssentials:
	 * - betacraft.mssentials.nick.self
	 * - betacraft.mssentials.nick.others
	 * - betacraft.mssentials.commandspy
	 * - betacraft.mssentials.invsee
	 * - betacraft.mssentials.ci
	 * 
	 * LogBlock:
	 * - betacraft.logblock.rollback
	 * 
	 * BetaCommandBlocks:
	 * - betacraft.commandblocks.use
	 * 
	 * ZoO!:
	 * - betacraft.zoo
	 * 
	 * Dzialecznik:
	 * - betacraft.dzialecznik.admin
	 * 
	 * AdminChat:
	 * - betacraft.admin
	 * 
	 * Other:
	 * - betacraft.admin
	 */

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new ServerListener() {
			@Override
			public void onPluginEnable(PluginEnableEvent e) {
				if (!e.getPlugin().getDescription().getFullName().equals("Permissions") && !e.getPlugin().getDescription().getVersion().equals("2.7.7")) {
					return;
				}
				Betacraft.permissions = (Permissions) e.getPlugin();
				Bukkit.getServer().getLogger().info(" [BetaCraft] Znaleziono SuperpermsBridge v1.2");
			}
		}, Priority.Normal, this);
		LogBlock.onEnable(this);
		Dzialecznik.onEnable();
		MobLimit.onEnable(this);
		BetaCommandBlocks.onEnable(this);
		ChestProtection.onEnable(this);
		Other.onEnable(this);
		Moressentials.onEnable(this);
		Bukkit.getServer().getLogger().info(" [BetaCraft] Wlaczono, wersja: " + this.getDescription().getVersion());
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
				if (!Hdb.getAllSponges().isEmpty()) {
					for (SpongeBlock sb: Hdb.getAllSponges()) {
						boolean matches = false;
						for (String world: Hdb.getHardcoreWorlds()) {
							if (!matches) {
								if (sb.getSpongeBlock().getWorld().getName().equals(world)) {
									matches = true;
								}
							}
						}
						if (matches) {
							sb.generateStone();
						}
					}
				}
				BetaCommandBlocks.config.load();
				if (BetaCommandBlocks.config.getKeys("commandblocks") != null) {
					if (BetaCommandBlocks.config.getKeys("commandblocks") == null || BetaCommandBlocks.config.getKeys("commandblocks").isEmpty()) {
						return;
					}
					for (String locations : BetaCommandBlocks.config.getKeys("commandblocks")) {
						String[] split = locations.split(",");
						String world = split[0];
						int x = Integer.parseInt(split[1]);
						int y = Integer.parseInt(split[2]);
						int z = Integer.parseInt(split[3]);
						Location loc = new Location(Bukkit.getServer().getWorld(world), x, y, z);
						if (loc.getBlock().isBlockPowered() || loc.getBlock().isBlockIndirectlyPowered()) {
							if (!BetaCommandBlocks.powered.contains(loc.getBlock()) ) {
								String command = BetaCommandBlocks.config.getString("commandblocks." + locations, null);
								if (command.contains("@nearest")) {
									int radius = 0;
									String[] splitted = command.split(" ");
									for (int n = 0; splitted.length > n; n++) {
										if (splitted[n].startsWith("@nearest")) {
											String[] qpa = splitted[n].split("nearest");
											radius = Integer.parseInt(qpa[1]);
										}
									}
									Entity ent = ((CraftWorld)Bukkit.getServer().getWorld(world)).spawn(loc, Pig.class);
									List<Entity> nearest = ((CraftEntity)ent).getNearbyEntities(radius, radius, radius);
									List<Player> pnear = new LinkedList<Player>();
									for (Entity s : nearest) {
										if (s instanceof Player) {
											pnear.add((Player)s);
										}
									}
									if (pnear.isEmpty()) {
										return;
									}
									List<String> names = new LinkedList<String>();
									for (Player p : pnear) {
										names.add(p.getName());
									}
									for (int dd = 0; names.size() > dd; dd++) {
										String cmd = command;
										cmd = cmd.replaceAll("@nearest"+radius, names.get(dd));
										Bukkit.getServer().dispatchCommand(new ConsoleCommandSender(Bukkit.getServer()), cmd);
										if (!BetaCommandBlocks.powered.contains(loc.getBlock())) {
											BetaCommandBlocks.powered.add(loc.getBlock());
										}
									}
									ent.remove();
									return;
								}
								Bukkit.getServer().dispatchCommand(new ConsoleCommandSender(Bukkit.getServer()), command);
								BetaCommandBlocks.powered.add(loc.getBlock());
							}
						}
						if (!loc.getBlock().isBlockPowered() || !loc.getBlock().isBlockIndirectlyPowered()) {
							if (BetaCommandBlocks.powered.contains(loc.getBlock())) {
								BetaCommandBlocks.powered.remove(loc.getBlock());
							}
						}
					}
				}
			}
		}, 0L, 20L);
	}

	public void onDisable() {
		Moressentials.onDisable();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Other.onCommand(sender, cmd, alias, args);
		LogBlock.onCommand(sender, cmd, alias, args);
		Dzialecznik.onCommand(sender, cmd, alias, args);
		ChestProtection.onCommand(sender, cmd, alias, args);
		BetaCommandBlocks.onCommand(sender, cmd, alias, args);
		Moressentials.onCommand(sender, cmd, alias, args);
		return true;
	}
}