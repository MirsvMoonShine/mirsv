package com.mirsv.moonshine.BroadCast;

import java.util.ArrayList;

import com.mirsv.MirPlugin;

public class BroadCast extends MirPlugin {
	public static ArrayList < String > BCadmins = new ArrayList < String > ();

	public BroadCast() {
		getConfig().addDefault("BroadCast.Prefix", "&6[&4����&6]");
		getConfig().addDefault("BroadCast.ChatColor", "&a");
		getConfig().options().copyDefaults(true);
		saveConfig();

		getCommand("bc", new BroadCastListener(getConfig()));
		getListener(new BroadCastListener(getConfig()));
	}
}