package pl.betacraft.hardcore;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class HardcoreBlock extends BlockListener {

	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType() != Material.SPONGE) {
			return;
		}
		Hdb.addSponge(e.getBlock());
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
			}
			
			else if(n == 2) {
				e.getPlayer().getInventory().addItem(gold);
				e.getPlayer().getInventory().addItem(redstone);
			}
			
			else if(n == 3) {
				e.getPlayer().getInventory().addItem(iron);
				e.getPlayer().getInventory().addItem(tnt);
				e.getPlayer().getInventory().addItem(obsidian);
			}
			
			else if(n == 4) {
				e.getPlayer().getInventory().addItem(coal);
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
				e.getPlayer().getInventory().addItem(redstone);
			}
			
			else if(n == 2) {
				e.getPlayer().getInventory().addItem(iron);
				e.getPlayer().getInventory().addItem(tnt);
				e.getPlayer().getInventory().addItem(obsidian);
			}
			
			else if(n == 3) {
				e.getPlayer().getInventory().addItem(coal);
			}
			
			else {
				return;
			}
		}
	}
	
}