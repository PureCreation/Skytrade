package com.github.theway2cool1.Skytrade;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Trade{
	private List<ItemStack> p1items;
	private List<ItemStack> p2items;
	private Player player1;
	private Player player2;
	private Inventory p1inv;
	private Inventory p2inv;
	private int greenWool;
	public Trade(Player player1, Player player2, Inventory p1inv, Inventory p2inv, int greenWool){
		Trades.getQueue().remove(player1);
		Trades.getQueue().remove(player2);
		this.player1 = player1;
		this.player2 = player2;
		this.p1inv = p1inv;
		this.p2inv = p2inv;
		this.greenWool = greenWool;
		player1.openInventory(p1inv);
		player2.openInventory(p2inv);
		p1inv.setItem(4, new ItemStack(35, 1, (byte) 14));
		p1inv.setItem(13, new ItemStack(Material.STICK));
		p1inv.setItem(22, new ItemStack(Material.FIRE));
		p1inv.setItem(31, new ItemStack(Material.STICK));
		p1inv.setItem(40, new ItemStack(Material.STICK));
		p1inv.setItem(49, new ItemStack(35, 1, (byte) 14));
		/**|~|**/
		p2inv.setItem(4, new ItemStack(35, 1, (byte) 14));
		p2inv.setItem(13, new ItemStack(Material.STICK));
		p2inv.setItem(22, new ItemStack(Material.FIRE));
		p2inv.setItem(31, new ItemStack(Material.STICK));
		p2inv.setItem(40, new ItemStack(Material.STICK));
		p2inv.setItem(49, new ItemStack(35, 1, (byte) 14));
	}
	public Player getMainTrader(){
		return player1;
	}
	public Player getSecondaryTrader(){
		return player2;
	}
	public Inventory getPlayer1TradeGUI(){
		return p1inv;
	}
	public Inventory getPlayer2TradeGUI(){
		return p2inv;
	}
	public void end(EndReason reason){
		if(reason == EndReason.ACCEPT){
			this.completeTransaction();
		}
		else if(reason == EndReason.CANCEL){
			this.cancelTransaction();
			this.getMainTrader().sendMessage(ChatColor.RED + "Trade cancelled.");
			this.getSecondaryTrader().sendMessage(ChatColor.RED + "Trade cancelled.");
		}
		else if(reason == EndReason.DISCONNECT){
			this.cancelTransaction();
			if(this.getMainTrader().isOnline()){
				this.getMainTrader().sendMessage(ChatColor.RED + "Trade cancelled: " + ChatColor.DARK_AQUA + this.getSecondaryTrader().getDisplayName() + ChatColor.RED + " disconnected.");
			}
			if(this.getSecondaryTrader().isOnline()){
				this.getSecondaryTrader().sendMessage(ChatColor.RED + "Trade cancelled: " + ChatColor.DARK_AQUA + this.getMainTrader().getDisplayName() + ChatColor.RED + " disconnected.");
			}
		}
		this.getMainTrader().closeInventory();
		this.getSecondaryTrader().closeInventory();
	}
	public void completeTransaction(){
		int p1itemcount = 0;
		int p2itemcount = 0;
		for(int i = 0; i<=53; i++){
			if(API.between(i,0,3) || API.between(i,9,12) || API.between(i,18,21) || API.between(i,27,30) || API.between(i,36,39) || API.between(i,45,48)){
				if(this.getPlayer1TradeGUI().getItem(i) != new ItemStack(Material.AIR) && this.getPlayer1TradeGUI().getItem(i) != null){
					p1itemcount++;
					p1items.add(this.getPlayer1TradeGUI().getItem(i));
				}
			}
			if(API.between(i,5,8) || API.between(i,14,17) || API.between(i,23,26) || API.between(i,32,35) || API.between(i,41,44) || API.between(i,50,53)){
				if(this.getPlayer2TradeGUI().getItem(i) != new ItemStack(Material.AIR) && this.getPlayer2TradeGUI().getItem(i) != null){
					p2itemcount++;
					p2items.add(this.getPlayer2TradeGUI().getItem(i));
				}
			}
		}
		if(API.getEmptySlots(this.getMainTrader().getInventory()) >= p2itemcount && API.getEmptySlots(this.getSecondaryTrader().getInventory()) >= p1itemcount){
			for(final ItemStack i : p2items){
				this.getMainTrader().getInventory().addItem(i);
			}
			for(final ItemStack i : p1items){
				this.getSecondaryTrader().getInventory().addItem(i);
			}
			this.getMainTrader().sendMessage(ChatColor.GREEN + "Trade complete! Enjoy your new items.");
			this.getSecondaryTrader().sendMessage(ChatColor.GREEN + "Trade complete! Enjoy your new items.");
			return;
		}
		else{
			this.getMainTrader().sendMessage(ChatColor.RED + "Trade cancelled: One or more players didn't have enough inventory space.");
			this.getSecondaryTrader().sendMessage(ChatColor.RED + "Trade cancelled: One or more players didn't have enough inventory space.");
			return;
		}
	}
	private void cancelTransaction(){
		for(int i = 0; i <=53; i++){
			if(API.between(i,0,3) || API.between(i,9,12) || API.between(i,18,21) || API.between(i,27,30) || API.between(i,36,39) || API.between(i,45,48)){
				if(this.getPlayer1TradeGUI().getItem(i) != new ItemStack(Material.AIR) && this.getPlayer1TradeGUI().getItem(i) != null){
					this.getMainTrader().getInventory().addItem(this.getPlayer1TradeGUI().getItem(i));
				}
			}
		}
		for(int i = 0; i <=53; i++){
			if(API.between(i,5,8) || API.between(i,14,17) || API.between(i,23,26) || API.between(i,32,35) || API.between(i,41,44) || API.between(i,50,53)){
				if(this.getPlayer2TradeGUI().getItem(i) != new ItemStack(Material.AIR) && this.getPlayer2TradeGUI().getItem(i) != null){
					this.getSecondaryTrader().getInventory().addItem(this.getPlayer2TradeGUI().getItem(i));
				}
			}
		}
	}
	public int getGreenWool(){
		return greenWool;
	}
}
