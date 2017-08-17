package com.mirsv.catnote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.mirsv.MirPlugin;

public class PvPTeleport extends MirPlugin implements Listener, CommandExecutor {
	final String prefix = ChatColor.GOLD + "[" + ChatColor.GREEN + "미르서버" + ChatColor.GOLD + "] " + ChatColor.RESET;
	public PvPTeleport() {
		getCommand("콜로세움", this);
		getCommand("캠핑장", this);
		getCommand("천공섬", this);
		getListener(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if(args.length == 0) {
			if(label.equalsIgnoreCase("콜로세움")) {
				final int CenterX = 535, Y = 73, CenterZ = 321, Range = 20;
				int X, Z;
				do {
					X = CenterX - Range + (int) (Math.random() * Range * 2);
					Z = CenterZ - Range + (int) (Math.random() * Range * 2);
				} while(!new Location(Bukkit.getWorld("spawn"), X, Y, Z).getBlock().getType().equals(Material.AIR));
				player.teleport(new Location(Bukkit.getWorld("spawn"), X, Y, Z));
				player.sendMessage(prefix + ChatColor.GOLD + "콜로세움에 도착합니다.");
			}
			if(label.equalsIgnoreCase("천공섬")) {
				final int CenterX = 797, Y = 69, CenterZ = 531, Range = 30;
				int X, Z;
				do {
					X = CenterX - Range + (int) (Math.random() * Range * 2);
					Z = CenterZ - Range + (int) (Math.random() * Range * 2);
				} while(new Location(Bukkit.getWorld("spawn"), X, Y - 2, Z).getBlock().getType().equals(Material.AIR) || !(new Location(Bukkit.getWorld("spawn"), X, Y, Z).getBlock().getType().equals(Material.AIR)));
				player.teleport(new Location(Bukkit.getWorld("spawn"), X, Y, Z));
				player.sendMessage(prefix + ChatColor.GOLD + "천공섬으로 떠납니다.");
			}
			if(label.equalsIgnoreCase("캠핑장")) {
				final int CenterX = 518, Y = 60, CenterZ = 535, Range = 20;
				int X, Z;
				do {
					X = CenterX - Range + (int) (Math.random() * Range * 2);
					Z = CenterZ - (int) (Range * 1.5) + (int) (Math.random() * Range * 3);
				} while(!(new Location(Bukkit.getWorld("spawn"), X, Y, Z).getBlock().getType().equals(Material.AIR)) || new Location(Bukkit.getWorld("spawn"), X, 56, Z).getBlock().getType().equals(Material.WATER) || new Location(Bukkit.getWorld("spawn"), X, 56, Z).getBlock().getType().equals(Material.STATIONARY_WATER));
				player.teleport(new Location(Bukkit.getWorld("spawn"), X, Y, Z));
				player.sendMessage(prefix + ChatColor.GOLD + "캠핑장에 진입합니다.");
			}
		}
		return false;
	}
}