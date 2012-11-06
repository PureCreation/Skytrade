package com.github.theway2cool1.Skytrade;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener{
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		if(Trades.isTrading((Player) e.getWhoClicked())){
			Trade trade = Trades.getPlayerTrade((Player) e.getWhoClicked());
			Player clicker = (Player) e.getWhoClicked();
			Player player1 = trade.getMainTrader();
			Player player2 = trade.getSecondaryTrader();
			Inventory inv1 = trade.getPlayer1TradeGUI();
			Inventory inv2 = trade.getPlayer2TradeGUI();
			if(e.isShiftClick()){
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem() == new ItemStack(Material.DIRT) || e.getCurrentItem() == new ItemStack(Material.SAND)
			|| e.getCurrentItem() == new ItemStack(Material.BUCKET)|| e.getCurrentItem() == new ItemStack(Material.LAVA_BUCKET)
			|| e.getCurrentItem() == new ItemStack(Material.WATER_BUCKET)){
				clicker.sendMessage(ChatColor.RED + "Illegal trade item: " + e.getCurrentItem().toString());
				e.setCancelled(true);
				return;
			}
			if(e.getSlot() == 13 || e.getSlot() == 31 || e.getSlot() == 40){
				e.setCancelled(true);
				e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
				return;
			}
			else if(e.getSlot() == 4){
				if((Player) e.getWhoClicked() == player2){
					player2.sendMessage(ChatColor.RED + "You can't confirm for another player!");
				}
				else{
					inv1.setItem(4, new ItemStack(35, 1, (byte) 13));
					inv2.setItem(4, new ItemStack(35, 1, (byte) 13));
					if(trade.getGreenWool() == 1){
						trade.end(EndReason.ACCEPT);
						Trades.getAllTrades().remove(trade);
					}
					else{
						player2.sendMessage(ChatColor.GREEN + player1.getName() + " is ready to trade!");
					}
				}
				return;
			}
			else if(e.getSlot() == 49){
				if((Player) e.getWhoClicked() == player1){
					player1.sendMessage(ChatColor.RED + "You can't confirm for another player!");
				}
				else{
					inv1.setItem(49, new ItemStack(35, 1, (byte) 13));
					inv2.setItem(49, new ItemStack(35, 1, (byte) 13));
					if(trade.getGreenWool() == 1){
						trade.end(EndReason.ACCEPT);
						Trades.getAllTrades().remove(trade);
					}
					else{
						player1.sendMessage(ChatColor.GREEN + player2.getName() + " is ready to trade!");
					}
				}
				return;
			}
			else if(e.getSlot() == 22){
				trade.end(EndReason.CANCEL);
			}
			else if((API.between(e.getSlot(),0,3) || API.between(e.getSlot(),9,12) || API.between(e.getSlot(),18,21) 
					|| API.between(e.getSlot(),27,30) || API.between(e.getSlot(),36,39) || API.between(e.getSlot(),45,48)) && e.getInventory() == trade.getPlayer1TradeGUI()){
				if(clicker != player1){
					player2.sendMessage(ChatColor.RED + "Please stay on your side.");
					e.setCancelled(true);
				}
				else{
					inv1.setItem(e.getSlot(), clicker.getItemOnCursor());
					inv2.setItem(e.getSlot(), clicker.getItemOnCursor());
					player1.setItemInHand(new ItemStack(Material.AIR));
				}
			}
			else if((API.between(e.getSlot(),0,3) || API.between(e.getSlot(),9,12) || API.between(e.getSlot(),18,21) 
					|| API.between(e.getSlot(),27,30) || API.between(e.getSlot(),36,39) || API.between(e.getSlot(),45,48)) && e.getInventory() == trade.getPlayer2TradeGUI()){
				if(clicker != player1){
					player2.sendMessage(ChatColor.RED + "Please stay on your side.");
					e.setCancelled(true);
				}
				else{
					inv1.setItem(e.getSlot(), clicker.getItemOnCursor());
					inv2.setItem(e.getSlot(), clicker.getItemOnCursor());
					player1.setItemInHand(new ItemStack(Material.AIR));
				}
			}
			else if(API.between(e.getSlot(),5,8) || API.between(e.getSlot(),14,17) || API.between(e.getSlot(),23,26) || API.between(e.getSlot(),32,35) 
					|| API.between(e.getSlot(),41,44) || API.between(e.getSlot(),50,53)){
				if(clicker != player2){
					player1.sendMessage(ChatColor.RED + "Please stay on your side.");
					e.setCancelled(true);
				}
				else{
					inv1.setItem(e.getSlot(), clicker.getItemOnCursor());
					inv2.setItem(e.getSlot(), clicker.getItemOnCursor());
					player1.setItemInHand(new ItemStack(Material.AIR));
				}
			}
		}
	}
}
