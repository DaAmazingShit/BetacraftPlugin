package pl.betacraft.sell;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.util.config.Configuration;

public class Ceny {
	public static Configuration ceny = new Configuration(new File("plugins/BetaCraft/Sprzedaj"));
	public Material mat;
	public MaterialData md;

	public Ceny(Material mat, MaterialData md) throws Exception {
		this.mat = mat;
		this.md = md;
		if (ceny.getProperty(mat.name() + ".sell") == null) {
			throw new Exception("Nie mozna sprzedac ani kupic tego przedmiotu.");
		}
	}

	public Double getSell() {
		return ceny.getDouble(mat.name() + "-" + md.getData() + ".sell", 0.0D);
	}

	public Double getBuy() {
		return ceny.getDouble(mat.name() + "-" + md.getData() + ".buy", 99999999911110.0D);
	}

	public Integer getMinimumAmount() {
		return ceny.getInt(mat.name() + "-" + md.getData() + ".amount", 99090000);
	}

	public Integer getBuyAmount() {
		return ceny.getInt(mat.name() + "-" + md.getData() + ".buy_amount", 0);
	}
}