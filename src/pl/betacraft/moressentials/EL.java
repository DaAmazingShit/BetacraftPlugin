package pl.betacraft.moressentials;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class EL extends EntityListener {

	@Override
	public void onEntityDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		if (PDB.god(p.getName())) {
			e.setDamage(0);
			p.setFireTicks(0);
		}
	}
}
