package pl.betacraft.logblock;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class LBBlock extends BlockListener {
	
	@Override
	public void onBlockPlace(BlockPlaceEvent e) {
		String p = e.getPlayer().getName();
		String type = e.getBlock().getType().name();
		String x = Integer.toString(e.getBlock().getLocation().getBlockX());
		String y = Integer.toString(e.getBlock().getLocation().getBlockY());
		String z = Integer.toString(e.getBlock().getLocation().getBlockZ());
		String w = e.getBlock().getWorld().getName();
		DBLogger.write(e.getBlock().getLocation(), p, e.getBlockReplacedState().getType().name(), e.getItemInHand().getType().name());
		Logging.log("Gracz " + p + " postawil blok " + type + " na koordach: X"+x+", Y"+y+", Z"+z+", Swiat - "+w+".");
	}

	@Override
	public void onBlockBreak(BlockBreakEvent e) {
		String p = e.getPlayer().getName();
		String type = e.getBlock().getType().name();
		String x = Integer.toString(e.getBlock().getLocation().getBlockX());
		String y = Integer.toString(e.getBlock().getLocation().getBlockY());
		String z = Integer.toString(e.getBlock().getLocation().getBlockZ());
		String w = e.getBlock().getWorld().getName();
		DBLogger.write(e.getBlock().getLocation(), p, type, "AIR");
		Logging.log("Gracz " + p + " zniszczyl blok " + type + " na koordach: X"+x+", Y"+y+", Z"+z+", Swiat - "+w+".");
	}

	@Override
	public void onBlockIgnite(BlockIgniteEvent e) {
		if (e.getCause() == IgniteCause.FLINT_AND_STEEL) {
			String p = e.getPlayer().getName();
			String type = e.getBlock().getType().name();
			String x = Integer.toString(e.getBlock().getLocation().getBlockX());
			String y = Integer.toString(e.getBlock().getLocation().getBlockY());
			String z = Integer.toString(e.getBlock().getLocation().getBlockZ());
			String w = e.getBlock().getWorld().getName();
			Logging.log("Gracz " + p + " PODPALIL blok " + type + " na koordach: X"+x+", Y"+y+", Z"+z+", Swiat - "+w+".");
		}
	}

	@Override
	public void onBlockBurn(BlockBurnEvent e) {
		String type = e.getBlock().getType().name();
		String x = Integer.toString(e.getBlock().getLocation().getBlockX());
		String y = Integer.toString(e.getBlock().getLocation().getBlockY());
		String z = Integer.toString(e.getBlock().getLocation().getBlockZ());
		String w = e.getBlock().getWorld().getName();
		DBLogger.write(e.getBlock().getLocation(), "Ogien", type, "AIR");
		Logging.log("Splonal blok " + type + " na koordach: X"+x+", Y"+y+", Z"+z+", Swiat - "+w+".");
	}

	@Override
	public void onSignChange(SignChangeEvent e) {
		String line1 = e.getLine(0);
		String line2 = e.getLine(1);
		String line3 = e.getLine(2);
		String line4 = e.getLine(3);
		String p = e.getPlayer().getName();
		String x = Integer.toString(e.getBlock().getLocation().getBlockX());
		String y = Integer.toString(e.getBlock().getLocation().getBlockY());
		String z = Integer.toString(e.getBlock().getLocation().getBlockZ());
		String w = e.getBlock().getLocation().getWorld().getName();
		
		Logging.log("Gracz " + p + " postawil tabliczke, X"+x+", Y"+y+", Z"+z+", Swiat - "+w+".");
		Logging.log("Linijka 1: '"+line1+"'.");
		Logging.log("Linijka 2: '"+line2+"'.");
		Logging.log("Linijka 3: '"+line3+"'.");
		Logging.log("Linijka 4: '"+line4+"'.");
	}
}
