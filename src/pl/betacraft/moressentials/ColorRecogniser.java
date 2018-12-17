package pl.betacraft.moressentials;

import org.bukkit.ChatColor;

public enum ColorRecogniser {

    BLACK("&0"),
    DARK_BLUE("&1"),
    DARK_GREEN("&2"),
    DARK_AQUA("&3"),
    DARK_RED("&4"),
    DARK_PURPLE("&5"),
    GOLD("&6"),
    GRAY("&7"),
    DARK_GRAY("&8"),
    BLUE("&9"),
    GREEN("&a"),
    AQUA("&b"),
    RED("&c"),
    LIGHT_PURPLE("&d"),
    YELLOW("&e"),
    WHITE("&f");

	private final String code;

	private ColorRecogniser(final String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public static ChatColor getColor(String s) {
		ColorRecogniser cr = null;
		for (ColorRecogniser candidate: ColorRecogniser.values()) {
			if (candidate.getCode().equalsIgnoreCase(s)) {
				cr = candidate;
			}
		}
		if (cr == null) return ChatColor.WHITE;
		return ChatColor.valueOf(cr.name());
	}

	public static String format(String s) {
		String s1 = s.replaceAll("&", "\u00A7");
		return s1;
	}
}
