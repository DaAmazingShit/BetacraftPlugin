package pl.betacraft.moblimit;

import org.bukkit.World;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Liste extends EntityListener {

	@Override
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.NATURAL) {
			return;
		}
		World w = e.getEntity().getWorld();
		if (w.getLivingEntities().size() >= 150) {
			e.setCancelled(true);
		}
	}
}