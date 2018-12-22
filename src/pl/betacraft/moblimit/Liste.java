package pl.betacraft.moblimit;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityListener;

public class Liste extends EntityListener {

	@Override
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.NATURAL && e.getSpawnReason() != SpawnReason.BED) {
			return;
		}
		World w = e.getEntity().getWorld();
		// Handle SkyBlock.
		if (w.getName().equalsIgnoreCase("skyblock")) {
			List<Entity> animals = new LinkedList<Entity>();
			for (Entity en : w.getLivingEntities()) {
				if (en instanceof Animals) {
					animals.add(en);
				}
			}
			if (animals.size() >= 30 && e.getEntity() instanceof Animals) {
				e.setCancelled(true);
			}
			if (w.getLivingEntities().size() - animals.size() >= 180) {
				e.setCancelled(true);
				return;
			}
			return;
		}
		// If the amount of entities in world is about 180, don't allow them to spawn!
		if (w.getLivingEntities().size() >= 180) {
			e.setCancelled(true);
		}
	}
}