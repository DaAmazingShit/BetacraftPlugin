package pl.moresteck.bcskyblock;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.util.config.Configuration;

import pl.moresteck.bcskyblock.LangManager.Lang;

public class Party {
	private Configuration config = new Configuration(new File("plugins/BetaCraft/Skyblock", "parties"));
	private int id;

	protected Party(int id) {
		this.id = id;
	}

	public static Party getParty(int id) {
		return new Party(id);
	}

	protected List<String> getRawPlayers() {
		this.config.load();
		return this.config.getStringList("'" + this.id + "'", new LinkedList<String>());
	}

	public List<SBPlayer> getPlayers() {
		List<String> raw = this.getRawPlayers();
		List<SBPlayer> git = new LinkedList<SBPlayer>();
		for (String p: raw) {
			git.add(new SBPlayer(p));
		}
		return git;
	}

	public String removePlayer(SBPlayer player) {
		List<String> players = this.getRawPlayers();
		if (!players.contains(player.getName())) return LangManager.getResponse(Lang.user_not_in_party);
		players.remove(player.getName());
		config.load();
		config.setProperty("'" + id + "'", players);
		return LangManager.getResponse(Lang.user_removed_from_party_succ);
	}

	public String addPlayer(SBPlayer player) {
		List<String> players = this.getRawPlayers();
		if (players.contains(player.getName())) return LangManager.getResponse(Lang.user_already_in_party);
		players.add(player.getName());
		config.load();
		config.setProperty("'" + id + "'", players);
		return LangManager.getResponse(Lang.user_added_to_party_succ);
	}
}
