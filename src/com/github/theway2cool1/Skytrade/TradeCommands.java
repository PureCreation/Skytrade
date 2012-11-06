package com.github.theway2cool1.Skytrade;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TradeCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("trade.use")){
			if(args.length == 2){
				if(cmd.getName().equalsIgnoreCase("trade")){
					if(args[0].equalsIgnoreCase("invite")){
						if(Trades.hasTradePending((Player) sender)){
							sender.sendMessage(ChatColor.RED + "You already have a trade pending.");
							return true;
						}
						else{
							if(Trades.hasTradePending(Bukkit.getPlayer(args[1]))){
								sender.sendMessage(ChatColor.RED + "That player already has a trade pending.");
							}
							else{
								API.requestTrade(sender, Bukkit.getPlayer(args[1]));
							}
						}
					}
					else{
						sender.sendMessage(ChatColor.RED + "Unknown subcommand.");
						return false;
					}
				}
			}
			else if(args.length == 1){
				if(cmd.getName().equalsIgnoreCase("trade")){
					if(args[0].equalsIgnoreCase("cancel")){
						if(Trades.hasTradePending((Player) sender)){
							Trades.removeFromQueue((Player) sender);
							return true;
						}
						else{
							sender.sendMessage(ChatColor.RED + "You don't have a trade request pending!");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("deny")){
						if(Trades.hasTradePending((Player) sender)){
							Trades.removeFromQueue((Player) sender);
							return true;
						}
						else{
							sender.sendMessage(ChatColor.RED + "You don't have a trade pending!");
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("accept")){
						if(Trades.getQueue().containsValue(sender.getName())){
							Trade trade = new Trade(Bukkit.getPlayerExact(API.getKeyByValue(sender.getName(), Trades.getQueue())), (Player) sender,
                                Bukkit.createInventory(null, 54, "                    Trader 1") , Bukkit.createInventory(null, 54, "                    Trader 2"), 0);
							Trades.getAllTrades().add(trade);
						}
						else{
							sender.sendMessage(ChatColor.RED + "You don't have a trade pending!");
							return true;
						}
					}
				}
			}
			else{
				sender.sendMessage(ChatColor.RED + "Invalid number of arguments.");
				return false;
			}
		}
		else{
			sender.sendMessage(ChatColor.RED + "No permissions.");
			return true;
		}
		return false;
	}

}
