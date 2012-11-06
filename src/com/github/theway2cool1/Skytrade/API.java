package com.github.theway2cool1.Skytrade;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class API{
	public static boolean between(int i, int f, int s){
		if(i >= f && i <= s){
			return true;
		}
		return false;
	}
	public static int getEmptySlots(Inventory inv){
		int amount = 0;
		for(final ItemStack item : inv.getContents()){
			if(item.getType() == Material.AIR || item == null){
				amount++;
			}
		}
		return amount;
	}
	public static void requestTrade(CommandSender sender, Player invited){
		if(invited != null){
			Trades.getQueue().put(sender.getName(), invited.getName());
			sender.sendMessage(ChatColor.GREEN+ invited.getName() + " has been invited to trade!");
			invited.sendMessage(ChatColor.DARK_AQUA + sender.getName() + ChatColor.GOLD + " wants to trade.");
			invited.sendMessage(ChatColor.GOLD + "Type " + ChatColor.GREEN + "/trade accept" + ChatColor.GOLD + " to accept.");
			invited.sendMessage(ChatColor.GOLD + "Or type " + ChatColor.BLUE + "/trade deny" + ChatColor.GOLD + " to deny.");
		}
		else{
			sender.sendMessage(ChatColor.RED+ "Player can't be found!");
		}
	}
	public static String getKeyByValue(String value, HashMap<String, String> hashmap){
		for(final String s : hashmap.keySet()){
			if(hashmap.get(s).equals(value)){
				return s;
			}
		}
		return null;
	}
}
