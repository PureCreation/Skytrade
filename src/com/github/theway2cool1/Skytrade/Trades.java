package com.github.theway2cool1.Skytrade;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.entity.Player;


public class Trades{
	private static HashSet<Trade> trades = new HashSet<Trade>();
	private static HashMap<String,String> queue = new HashMap<String,String>();
	public static HashMap<String,String> getQueue(){
		return queue;
	}
	public static HashSet<Trade> getAllTrades(){
		return trades;
	}
	public static boolean isTrading(Player player){
		for(final Trade t : getAllTrades()){
			if(t.getMainTrader() == player || t.getSecondaryTrader() == player){
				return true;
			}
		}
		return false;
	}
	public static boolean hasTradePending(Player player){
		if(getQueue().containsKey(player.getName()) || getQueue().containsValue(player.getName())){
			return true;
		}
		return false;
	}
	public static Trade getPlayerTrade(Player player){
		for(final Trade t: getAllTrades()){
			if(t.getMainTrader() == player || t.getSecondaryTrader() == player){
				return t;
			}
		}
		return null;
	}
	public static void removeFromQueue(Player player){
		if(getQueue().containsKey(player.getName())){
			getQueue().remove(player.getName());
			return;
		}
		else if(getQueue().containsValue(player.getName())){
			for(final String s : getQueue().keySet()){
				if(getQueue().get(s) == player.getName()){
					getQueue().remove(s);
					return;
				}
			}
		}
	}
}
