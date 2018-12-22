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

	public Block getSpongeBlock() {
		return sponge;
	}

	public boolean compareToSponge(Block block) {
		int x1 = block.getX();
		int y1 = block.getY();
		int z1 = block.getZ();

		int x2 = sponge.getX();
		int y2 = sponge.getY();
		int z2 = sponge.getZ();
		if (x1 == x2 && y1 == y2 && z1 == z2) {
			return true;
		}
		return false;
	}

	public boolean compareToStone(Block block) {
		int x1 = block.getX();
		int y1 = block.getY();
		int z1 = block.getZ();

		int x2 = up.getX();
		int y2 = up.getY();
		int z2 = up.getZ();
		if (x1 == x2 && y1 == y2 && z1 == z2) {
			return true;
		}
		return false;
	}

	// This is abstract because we don't know if the block which should be stone is not any other block type.
	public Block getAbstractStoneBlock() {
		return up;
	}
}