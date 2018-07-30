package pl.betacraft.hardcore;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockListeners extends BlockListener{
	
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.MOSSY_COBBLESTONE) {
			
			ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
			ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE);
			ItemStack dirt = new ItemStack(Material.DIRT);
			ItemStack jukebox = new ItemStack(Material.JUKEBOX);
			ItemStack greenrecord = new ItemStack(Material.GREEN_RECORD);
			ItemStack map = new ItemStack(Material.MAP);
			ItemStack ironhelmet = new ItemStack(Material.IRON_HELMET);
			ItemStack ironchestplate = new ItemStack(Material.IRON_CHESTPLATE);
			ItemStack ironboots = new ItemStack(Material.IRON_BOOTS);
			ItemStack ironleggins= new ItemStack(Material.IRON_LEGGINGS);
			
			Random rand = new Random();
			int n = rand.nextInt(10) + 1;
			if (n == 1) {
				e.getPlayer().getInventory().addItem(ironsword);
			}
			
			else if (n == 2) {
				e.getPlayer().getInventory().addItem(goldenapple);
			}
			
			else if (n == 3) {
				e.getPlayer().getInventory().addItem(dirt);
			}
			
			else if (n == 4) {
				e.getPlayer().getInventory().addItem(jukebox);
			}
			
			else if (n == 5) {
				e.getPlayer().getInventory().addItem(greenrecord);
			}
			
			else if (n == 6) {
				e.getPlayer().getInventory().addItem(map);
			}
			
			else if (n == 7) {
				e.getPlayer().getInventory().addItem(ironhelmet);
			}
			
			else if (n == 8) {
				e.getPlayer().getInventory().addItem(ironchestplate);
			}
			
			else if (n == 9) {
				e.getPlayer().getInventory().addItem(ironboots);
			}
			
			else if (n == 10) {
				e.getPlayer().getInventory().addItem(ironleggins);
			}
			
			
		}
	}

}
