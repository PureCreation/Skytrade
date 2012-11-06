package com.github.theway2cool1.Skytrade;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Trade{
	private Player player1;
	private Player player2;
	private Inventory p1inv;
	private Inventory p2inv;
	public Trade(Player player1, Player player2, Inventory p1inv, Inventory p2inv){
		Trades.getQueue().remove(player1);
		Trades.getQueue().remove(player2);
		this.player1 = player1;
		this.player2 = player2;
		this.p1inv = p1inv;
		this.p2inv = p2inv;
		p1inv = Bukkit.createInventory(null, 54, "                    Trade");
		p2inv = Bukkit.createInventory(null, 54, "                    Trade");
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
			
		}
		else if(reason == EndReason.CANCEL){
			
		}
		else if(reason == EndReason.DISCONNECT){
			
		}
	}
	public void completeTransaction(){
		
	}
}
