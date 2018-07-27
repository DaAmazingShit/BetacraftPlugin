package pl.betacraft.wayback;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class WBlock extends BlockListener {

	@Override
	public void onBlockDamage(BlockDamageEvent e) {
		if (e.getBlock().getType() != Material.TNT) {
			return;
		}
		Block b = e.getBlock();
		Material hold = e.getPlayer().getInventory().getItemInHand().getType();
		if (hold == Material.SHEARS) {
			b.setTypeId(0);
			b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.TNT, 1));
			return;
		}
		((CraftWorld)b.getWorld()).getHandle().setRawData(b.getX(), b.getY(), b.getZ(), 1);
	}
}