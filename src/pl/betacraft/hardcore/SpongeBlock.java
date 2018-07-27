package pl.betacraft.hardcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SpongeBlock {
	private static Block sponge;
	private static Block up;

	public static SpongeBlock getSpongeBlock(String world, int x, int y, int z) {
		World w = Bukkit.getServer().getWorld(world);
		if (w == null) {
			return null;
		}
		Block block = w.getBlockAt(x, y, z);
		if (block.getType() != Material.SPONGE) {
			return null;
		}
		else {
			return new SpongeBlock(block);
		}
	}

	private SpongeBlock(Block block) {
		sponge = block;
		up = sponge.getWorld().getBlockAt(sponge.getX(), sponge.getY() + 1, sponge.getZ());
	}

	public boolean generateStone() {
		if (up.getType() != Material.AIR) {
			return false;
		}
		up.setTypeId(1);
		return true;
	}

	public boolean isStoneGenerated() {
		if (up.getType() != Material.STONE) {
			return false;
		}
		return true;
	}

	public Block getAbstractStoneBlock() {
		return up;
	}
}