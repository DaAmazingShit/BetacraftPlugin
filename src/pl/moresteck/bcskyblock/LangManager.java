package pl.moresteck.bcskyblock;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.util.config.Configuration;

public class LangManager {
	protected static Configuration lang = new Configuration(new File("plugins/BetaCraft/Skyblock/", "lang.yml"));

	public enum Lang {

		user_removed_from_party_succ,
		user_already_in_party,
		user_added_to_party_succ,
		user_not_in_party;

		protected static Map<String, Lang> names = new HashMap<String, Lang>();

		public static Lang getLang(String l) {
			return names.get(l.toLowerCase());
		}

		static {
			for (Lang l: Lang.values()) {
				String name = l.name().toLowerCase();
				names.put(name, l);
			}
		}
	}

	public static String getResponse(Lang l) {
		lang.load();
		return lang.getString(l.name());
	}

	public static void generateResponseFile() {
		Lang[] lang = Lang.values();
		// TODO
	}
}
