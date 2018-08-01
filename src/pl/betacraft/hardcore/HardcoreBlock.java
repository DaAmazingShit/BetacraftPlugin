package pl.betacraft.hardcore;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class HardcoreBlock extends BlockListener {

	public void onBlockPlace(BlockPlaceEvent e) {

		// SpongeBlock
		if (e.getBlock().getType() == Material.SPONGE) {
			Hdb.addSponge(e.getBlock());
			return;
		}

		// Moat Digger
		if (e.getBlockPlaced().getType() == Material.JUKEBOX) {
			int x = e.getBlockPlaced().getX();
			int y = e.getBlockPlaced().getY();
			int z = e.getBlockPlaced().getZ();
			World w = e.getBlockPlaced().getWorld();
			boolean isHardcore = false;
			for (String world: Hdb.getHardcoreWorlds()) {
				if (world.equalsIgnoreCase(w.getName())) {
					isHardcore = true;
				}
			}
			if (!isHardcore) {
				return;
			}
			Location loc = new Location(w, x, y, z);
			loc.getBlock().setType(Material.AIR);

			for (int y1 = e.getBlockPlaced().getY(); y1 > 0; y1--) {
				Block b = e.getBlockPlaced().getWorld().getBlockAt(e.getBlockPlaced().getX(), y1, e.getBlockPlaced().getZ());
				if (b.getType() != Material.BEDROCK) {
					b.setType(Material.AIR);
				}
			}
			return;
		}
	}

	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.SPONGE) {
			Hdb.removeSponge(e.getBlock());
			return;
		}

		// StoneDrop
		if (e.getBlock().getType() == Material.STONE) {
			boolean isHardcore = false;
			for (String w : Hdb.getHardcoreWorlds()) {
				if (w.equalsIgnoreCase(e.getBlock().getWorld().getName())) {
					isHardcore = true;
				}
			}
			if (!isHardcore) {
				return;
			}
			if (e.getBlock().getY() <= 20) {
				Material random = Hdb.getRandomDropStoneDeep();
				Random r = new Random();
				ItemStack item = new ItemStack(random, (r.nextInt(2) + 1));
				e.getPlayer().getInventory().addItem(item);
				e.getPlayer().sendMessage(ChatColor.GOLD + "Dostales " + random.name() + "!");
				return;
			}
			if (e.getBlock().getY() >= 20) {
				Material random = Hdb.getRandomDropStone();
				Random r = new Random();
				ItemStack item = new ItemStack(random, (r.nextInt(2) + 1));
				e.getPlayer().getInventory().addItem(item);
				e.getPlayer().sendMessage(ChatColor.GOLD + "Dostales " + random.name() + "!");
			}
		}

		// CobbleX
		if (e.getBlock().getType() == Material.MOSSY_COBBLESTONE) {
			if (!Hdb.canPlayerCobbleX(e.getPlayer().getName())) {
				return;
			}
			Hdb.setPlayerCobbleX(e.getPlayer().getName(), false);
			boolean isHardcore = false;
			for (String w : Hdb.getHardcoreWorlds()) {
				if (w.equalsIgnoreCase(e.getBlock().getWorld().getName())) {
					isHardcore = true;
				}
			}
			if (!isHardcore) {
				return;
			}
			Material random = Hdb.getRandomDropCobbleX();
			ItemStack item = new ItemStack(random, 1);
			e.getPlayer().getInventory().addItem(item);
			e.getPlayer().sendMessage(ChatColor.AQUA + "Dostales " + random.name() + "!");
			e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.EXTINGUISH, 10);
			return;
		}
	}
}