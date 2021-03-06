package pl.betacraft.chestprotection.util;

import java.util.HashMap;

import org.bukkit.entity.Player;

import pl.betacraft.moresteck.Betacraft;

public class Permission {

	public static Boolean hasPlayer(Player p, Perm permission) {	
		if (permission.isDefault()) {
			return true;
		}
		return Betacraft.permissions.getHandler().has(p, permission.getName());
	}

	public enum Perm {

		// Admin
		SHOW_INFO_OTHER("cp.admin.info", false),

		ACCESS_OTHER("cp.admin.access", false),

		RELOAD("cp.admin.reload", false),

		REMOVE_OTHER("cp.admin.remove.others", false),

		// Default
		SHOW_INFO("cp.use.info", true),

		PLAYER_REMOVE("cp.use.player.remove", true),

		PLAYER_ADD("cp.use.player.add", true),

		REMOVE("cp.use.remove.self", true),

		CREATE("cp.use.create", true);

		private String perm;
		private static HashMap<String, Perm> schowek = new HashMap<String, Perm>();
		private final boolean defaultAccess;

		private Perm(String perm, boolean da) {
			this.perm = perm;
			this.defaultAccess = da;
		}

		/**
		 * Returns the permission node
		 * @return node
		 */
		public String getName() {
			return perm;
		}

		/**
		 * Returns true if action is possible for everyone.
		 */
		public boolean isDefault() {
			return this.defaultAccess;
		}

		/**
		 * Returns an instance of Perm
		 * 
		 * @param node
		 * @return Perm instance
		 */
		public static Perm getPerm(String perm) {
			return schowek.get(perm);
		}

		static {
			for (Perm everyperm: Perm.values()) {
				schowek.put(everyperm.name(), everyperm);
			}
		}
	}
}