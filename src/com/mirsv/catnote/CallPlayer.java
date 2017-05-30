package com.mirsv.catnote;

import org.bukkit.command.CommandExecutor;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mirsv.MirPlugin;

public class CallPlayer extends MirPlugin implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String prefix = ChatColor.GOLD + "[" + ChatColor.GREEN + "�̸�����" + ChatColor.GOLD + "] " + ChatColor.RESET;
		if (getConfig().getBoolean("enable.CallPlayer")) {
			Player player = null;
			if ((sender instanceof Player)) player = (Player) sender;
			if (args.length == 0) {
				if (!(sender instanceof Player)) Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "��� ���  |  /cl [�г���1] [�г���2] ... : [�г���]�� ȣ��");
				else
					player.sendMessage(ChatColor.RED + "��� ���  |  /cl [�г���1] [�г���2] ... : [�г���]�� ȣ��");
			} else {
				ArrayList < String > CallList = new ArrayList < String > ();
				ArrayList < String > AbsentList = new ArrayList < String > ();
				boolean CallMyself = false;
				for (String s: args) {
					boolean isOnline = false;
					boolean isOverlapped;
					for (Player p: Bukkit.getOnlinePlayers()) {
						if (s.equalsIgnoreCase(p.getName())) {
							isOnline = true;
							if (((sender instanceof Player)) && (player.getName().equalsIgnoreCase(s))) {
								if (!CallMyself) player.sendMessage(prefix + ChatColor.DARK_RED + "�ڱ� �ڽ��� ȣ���� �� �����ϴ�!");
								CallMyself = true;
								break;
							}
							isOverlapped = false;
							for (String string: CallList) {
								if (string.equalsIgnoreCase(s)) {
									isOverlapped = true;
									break;
								}
							}
							if (isOverlapped) {
								continue;
							}
							CallList.add(s);
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 10.0F, 1.0F);
							if (!(sender instanceof Player)) {
								String Message = "Console�� ����� ȣ���ϼ̽��ϴ�!";
								String Command = "title " + p.getName() + " title {\"text\":\"" + Message + "\"}";
								pm.getServer().dispatchCommand((CommandSender) pm.getServer().getConsoleSender(), Command);
							} else {
								String Message = player.getName() + "���� ����� ȣ���ϼ̽��ϴ�!";
								String Command = "title " + p.getName() + " title {\"text\":\"" + Message + "\"}";
								pm.getServer().dispatchCommand((CommandSender) pm.getServer().getConsoleSender(), Command);
							}
							String Message = "�������� �� ���ɾ�� ���踦 �ϸ� �Ű����ּ���!";
							String Command = "title " + p.getName() + " subtitle {\"text\":\"" + Message + "\", \"color\":\"red\"}";
							pm.getServer().dispatchCommand((CommandSender) pm.getServer().getConsoleSender(), Command);
							break;
						}
					}
					if (!isOnline) {
						boolean isOverlapped1 = false;
						for (String string: AbsentList) {
							if (string.equalsIgnoreCase(s)) {
								isOverlapped1 = true;
								break;
							}
						}
						if (isOverlapped1) continue;
						AbsentList.add(s);
					}
				}
				if (CallList.size() > 0) {
					String List = "";
					for (int i = 0; i < CallList.size(); i++) {
						List = List + (String) CallList.get(i);
						if (i >= CallList.size() - 1) continue;
						List = List + ", ";
					}
					if (!(sender instanceof Player)) Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + List + "���� ȣ���Ͽ����ϴ�!");
					else
						player.sendMessage(prefix + ChatColor.GREEN + List + "���� ȣ���Ͽ����ϴ�!");
				}
				if (AbsentList.size() > 0) {
					String List = "";
					for (int i = 0; i < AbsentList.size(); i++) {
						List = List + (String) AbsentList.get(i);
						if (i >= AbsentList.size() - 1) continue;
						List = List + ", ";
					}
					if (!(sender instanceof Player)) Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + List + "���� ������ �������� �ʽ��ϴ�!");
					else
						player.sendMessage(prefix + ChatColor.RED + List + "���� ������ �������� �ʽ��ϴ�!");
				}
			}
		}
		return false;
	}

	public CallPlayer() {
		getCommand("cl", this);
	}

}