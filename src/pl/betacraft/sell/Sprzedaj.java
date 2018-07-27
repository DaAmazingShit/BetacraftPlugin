package pl.betacraft.sell;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.earth2me.essentials.api.Economy;

public class Sprzedaj {
	private static String prefix = ChatColor.DARK_GREEN + " [" + ChatColor.WHITE + "BetaCraft" + ChatColor.DARK_GREEN + "] Sprzedaj: "
        + ChatColor.WHITE;

	public static void onEnable() {
		Bukkit.getServer().getLogger().info(" [BetaCraft] Sprzedaj: wlaczony.");
	}

	public static boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) throws Exception {
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("kup")) {
			if (args.length == 1) {
				Material mat = Material.getMaterial(args[0]);
				if (mat == null || mat == null) {
					return true;
				}
				MaterialData md = mat.getData().newInstance();
				Ceny cena;
				try {
					cena = new Ceny(mat, md);
					int minim = cena.getBuyAmount();
					if (Economy.hasEnough(p.getName(), cena.getBuy())) {
						Economy.subtract(p.getName(), cena.getBuy());
						p.getInventory().addItem(new ItemStack(mat, minim, mat.getMaxDurability(), md.getData()));
						p.sendMessage(prefix + "Kupiono " + mat.name() + ":" + md.getData() + " za " + cena.getBuy());
					}
					else {
						p.sendMessage(prefix + "Masz za malo pieniedzy. Potrzeba " + cena.getBuy());
						return true;
					}
				}
				catch (Exception ex) {
					p.sendMessage(ChatColor.RED + "Error: " + ex.getMessage());
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("sprzedaj")) {
			if (args.length == 0) {
				Material mat = p.getItemInHand().getType();
				if (mat == null || mat == Material.AIR) {
					return true;
				}
				MaterialData md = mat.getData().newInstance();
				Ceny cena;
				try {
					cena = new Ceny(mat, md);
					int minim = cena.getMinimumAmount();
					if (p.getInventory().contains(mat, minim)) {
						p.getInventory().removeItem(new ItemStack(mat, minim, mat.getMaxDurability(), md.getData()));
						Economy.add(p.getName(), cena.getSell());
						p.sendMessage(prefix + "Sprzedano " + mat.name() + ":" + md.getData() + " za " + cena.getSell());
					}
					else {
						p.sendMessage(prefix + "Masz za malo " + mat.name() + ":" + md.getData() + ". Potrzeba " + cena.getMinimumAmount());
						return true;
					}
				}
				catch (Exception ex) {
					p.sendMessage(ChatColor.RED + "Error: " + ex.getMessage());
					return true;
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("*")) {
				if (args.length == 0) {
					Material mat = p.getItemInHand().getType();
					if (mat == null || mat == Material.AIR) {
						return true;
					}
					ItemStack[] stacks = p.getInventory().getContents();
					int amount = 0;
					for (ItemStack is: stacks) {
						if (is.getType() == mat) {
							amount =+ is.getAmount();
						}
					}
					MaterialData md = mat.getData().newInstance();
					Ceny cena;
					try {
						cena = new Ceny(mat, md);
						int minim = cena.getMinimumAmount();
						if (p.getInventory().contains(mat, minim)) {
							double to = amount / minim;
							p.getInventory().removeItem(new ItemStack(mat, amount, mat.getMaxDurability(), md.getData()));
							Economy.add(p.getName(), cena.getSell() * to);
							p.sendMessage(prefix + "Sprzedano " + mat.name() + ":" + md.getData() + " za " + cena.getSell());
							return true;
						}
						else {
							p.sendMessage(prefix + "Masz za malo " + mat.name() + ":" + md.getData() + ". Potrzeba " + cena.getMinimumAmount());
							return true;
						}
					}
					catch (Exception ex) {
						p.sendMessage(ChatColor.RED + "Error: " + ex.getMessage());
					}
					return true;
				}
			}
		}
		return true;
	}
}