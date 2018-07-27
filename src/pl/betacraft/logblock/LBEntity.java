package pl.betacraft.logblock;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class LBEntity extends EntityListener {
	
	@Override
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getCause() == DamageCause.ENTITY_ATTACK) {
			if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
				Player p = (Player)((EntityDamageByEntityEvent) e).getDamager();
				String x = Integer.toString(e.getEntity().getLocation().getBlockX());
				String y = Integer.toString(e.getEntity().getLocation().getBlockY());
				String z = Integer.toString(e.getEntity().getLocation().getBlockZ());
				String w = e.getEntity().getLocation().getWorld().getName();
				if (!e.getEntity().isDead()) {
					return;
				}
				if (e.getEntity() instanceof Pig) {
					Logging.log("Gracz " + p.getName() + " zabil swinie, koordy swini X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Squid) {
					Logging.log("Gracz " + p.getName() + " zabil kalamarnice, koordy kalamarnicy X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Cow) {
					Logging.log("Gracz " + p.getName() + " zabil krowe, koordy krowy X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Chicken) {
					Logging.log("Gracz " + p.getName() + " zabil kurczaka, koordy kurczaka X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Sheep) {
					Logging.log("Gracz " + p.getName() + " zabil owce, koordy owce X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Slime) {
					Logging.log("Gracz " + p.getName() + " zabil slime, koordy slime X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Giant) {
					Logging.log("Gracz " + p.getName() + " zabil giganta, koordy giganta X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Zombie) {
					Logging.log("Gracz " + p.getName() + " zabil zombie, koordy zombie X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Spider) {
					Logging.log("Gracz " + p.getName() + " zabil pajaka, koordy pajaka X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Skeleton) {
					Logging.log("Gracz " + p.getName() + " zabil szkieleta, koordy szkieleta X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Creeper) {
					Logging.log("Gracz " + p.getName() + " zabil Creepera, koordy Creepera X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Ghast) {
					Logging.log("Gracz " + p.getName() + " zabil Ghasta, koordy Ghasta X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof PigZombie) {
					Logging.log("Gracz " + p.getName() + " zabil zombie pigmen, koordy zombie pigmen X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Boat) {
					Logging.log("Gracz " + p.getName() + " uderzyl lodke, koordy lodki X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Minecart) {
					Logging.log("Gracz " + p.getName() + " uderzyl minecart, koordy minecart X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof StorageMinecart) {
					Logging.log("Gracz " + p.getName() + " uderzyl minecart, koordy minecart X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof PoweredMinecart) {
					Logging.log("Gracz " + p.getName() + " uderzyl minecart, koordy minecart X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Fireball) {
					Logging.log("Gracz " + p.getName() + " odbil fireball, koordy fireball X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Painting) {
					Logging.log("Gracz " + p.getName() + " zniszczyl obraz, koordy obrazu X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
				if (e.getEntity() instanceof Player) {
					Logging.log("Gracz " + p.getName() + " zabil gracza " +((Player) e.getEntity()).getName() + " uzywajac "+p.getInventory().getItemInHand().getType().toString()+", na koordach X"+x+", Y"+y+", Z"+z+" Swiat - " + w + ".");
				}
			}
		}
	}
}
