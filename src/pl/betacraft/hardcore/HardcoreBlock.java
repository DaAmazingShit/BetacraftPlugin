package pl.betacraft.hardcore;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

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
	}
}