package pl.betacraft.hardcore;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Jukebox;

public class HardcoreBlock extends BlockListener {

	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType() != Material.SPONGE) {
			return;
		}
		Hdb.addSponge(e.getBlock());
		
		//Moat Digger
		
		if(e.getBlockPlaced().getType() == Material.JUKEBOX) {
            int x = e.getBlockPlaced().getLocation().getBlockX();
            int y = e.getBlockPlaced().getLocation().getBlockY();
            int z = e.getBlockPlaced().getLocation().getBlockZ();
            World w = Bukkit.getWorld("hardcore");
            Location loc = new Location(w, x, y, z);
            loc.getBlock().setType(Material.AIR);
            
            for (int y1 = e.getBlockPlaced().getY(); y1 > 0; y1--) {
                Block b = e.getBlockPlaced().getWorld().getBlockAt(e.getBlockPlaced().getX(), y1, e.getBlockPlaced().getZ());
                if (b.getType() != Material.BEDROCK) {
                    b.setType(Material.AIR);
                }
            }
 
		}
		
	}

	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.SPONGE) {
			Hdb.removeSponge(e.getBlock());
			return;
		}
		
		//CobbleX
		
		if (e.getBlock().getType() == Material.STONE && e.getPlayer().getLocation().getBlockY() <= 20) {
			
			ItemStack diamond = new ItemStack(Material.DIAMOND);
			ItemStack gold = new ItemStack(Material.GOLD_ORE);
			ItemStack iron = new ItemStack(Material.IRON_ORE);
			ItemStack coal = new ItemStack(Material.COAL);
			ItemStack tnt = new ItemStack(Material.TNT);
			ItemStack redstone = new ItemStack(Material.REDSTONE);
			ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
			
			Random rand = new Random();
			int n = rand.nextInt(10) + 1;
		
			if(n == 1) {
				e.getPlayer().getInventory().addItem(diamond);
				e.getPlayer().sendMessage(ChatColor.AQUA + "Dostales diament!");
			}
			
			else if(n == 2) {
				e.getPlayer().getInventory().addItem(gold);
				e.getPlayer().sendMessage(ChatColor.GOLD + "Dostales zloto!");
				e.getPlayer().getInventory().addItem(redstone);
				e.getPlayer().sendMessage(ChatColor.RED + "Dostales redstone!");
			}
			
			else if(n == 3) {
				e.getPlayer().getInventory().addItem(iron);
				e.getPlayer().sendMessage(ChatColor.GRAY + "Dostales zelazo!");
				e.getPlayer().getInventory().addItem(tnt);
				e.getPlayer().sendMessage(ChatColor.DARK_RED + "Dostales TNT!");
				e.getPlayer().getInventory().addItem(obsidian);
				e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Dostales obsidian!");
				
			}
			
			else if(n == 4) {
				e.getPlayer().getInventory().addItem(coal);
				e.getPlayer().sendMessage(ChatColor.BLACK + "Dostales wegiel!");
			}
			
			else {
				return;
			}
			
			
		}
		
		if (e.getBlock().getType() == Material.STONE && e.getPlayer().getLocation().getBlockY() >= 20) {
			
			ItemStack gold = new ItemStack(Material.GOLD_ORE);
			ItemStack iron = new ItemStack(Material.IRON_ORE);
			ItemStack coal = new ItemStack(Material.COAL);
			ItemStack tnt = new ItemStack(Material.TNT);
			ItemStack redstone = new ItemStack(Material.REDSTONE);
			ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
			
			Random rand = new Random();
			int n = rand.nextInt(10) + 1;
			
			if(n == 1) {
				e.getPlayer().getInventory().addItem(gold);
				e.getPlayer().sendMessage(ChatColor.GOLD + "Dostales zloto!");
				e.getPlayer().getInventory().addItem(redstone);
				e.getPlayer().sendMessage(ChatColor.RED + "Dostales redstone!");
			}
			
			else if(n == 2) {
				e.getPlayer().getInventory().addItem(iron);
				e.getPlayer().sendMessage(ChatColor.GRAY + "Dostales zelazo!");
				e.getPlayer().getInventory().addItem(tnt);
				e.getPlayer().sendMessage(ChatColor.DARK_RED + "Dostales TNT!");
				e.getPlayer().getInventory().addItem(obsidian);
				e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Dostales obsidian!");
			}
			
			else if(n == 3) {
				e.getPlayer().getInventory().addItem(coal);
				e.getPlayer().sendMessage(ChatColor.BLACK + "Dostales wegiel!");
			}
			
			else {
				return;
			}
		}
	}
	
}