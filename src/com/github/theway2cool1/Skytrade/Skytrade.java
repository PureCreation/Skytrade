package com.github.theway2cool1.Skytrade;
import org.bukkit.plugin.java.JavaPlugin;


public class Skytrade extends JavaPlugin{
	public void onEnable(){
		this.getLogger().info("Skytrade v" + this.getDescription().getVersion() + " enabled!");
		this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		this.getCommand("trade").setExecutor(new TradeCommands());
	}
	public void onDisable(){
		this.getLogger().info("Skytrade v" + this.getDescription().getVersion() + " enabled!");
	}
}
