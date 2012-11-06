package com.github.theway2cool1.Skytrade;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener{
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		if(Trades.isTrading((Player) e.getWhoClicked())){
			Trade trade = Trades.getPlayerTrade((Player) e.getWhoClicked());
			Player player1 = trade.getMainTrader();
			Player player2 = trade.getSecondaryTrader();
			Inventory inv1 = trade.getPlayer1TradeGUI();
			Inventory inv2 = trade.getPlayer2TradeGUI();
			/**
			 * Alright Pure, here's your job.
			 * Take into account that only player1 sees inv1, and likewise, only player2 sees inv2.
			 * If player 1 clicks slot 4, change it to green wool.
			 * If player 1 clicks slot 49, don't change it and send player 1 this message: ChatColor.RED + "You can't confirm for another player!"; Cancel the event and set the item on their cursor to air.
			 * If player 2 clicks slot 49, change it to green wool.
			 * If player 2 clicks slot 4, send the message that player 1 gets when clicking slot 49. Cancel the event and set the item on their cursor to air.
			 * Make slots 13, 31, and 40 unclickable.
			 * I'll handle slot 22.
			 **/
		}
	}
}
