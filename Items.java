//Kazu

package pl.betacraft.hardcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public class Items {	

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, new BlockListeners(), Priority.Normal, (Plugin) this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cobblex")) {
			Player p = (Player) sender;
			PlayerInventory pi = p.getInventory();
			ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);
			ItemStack cobblex = new ItemStack(Material.MOSSY_COBBLESTONE, 1);
			if (pi.contains(Material.COBBLESTONE, 576)) {
				for (int x = 0; x < 9; x++) {
					pi.remove(cobble);
				}
				pi.addItem(cobblex);
				p.sendMessage(ChatColor.GREEN + "Dostales cobblex!");
				return true;
			}
				p.sendMessage(ChatColor.RED + "Nie masz wystarczajacej ilosci cobble'a. Potrzeba 9 stakow.");
				return true;
		}
		return false;
	}
}
