package dev.shadmage.cbtrader;

import dev.shadmage.cbtrader.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.fo.plugin.SimplePlugin;
import java.util.Objects;

public class cbtrader extends SimplePlugin {

	private static cbtrader instance;

	public static cbtrader getInstance() {
		if (instance == null) {
			try {
				instance = JavaPlugin.getPlugin(cbtrader.class);
			} catch (final IllegalStateException ex) {
				if (Bukkit.getPluginManager().getPlugin("PlugMan") != null) {
					Bukkit.getLogger().severe("Failed to get instance of the plugin, if you reloaded using PlugMan you need to do a clean restart instead.");
				}
				throw ex;
			}
			Objects.requireNonNull(instance, "Cannot get a new instance! Have you reloaded?");
		}
		return instance;
	}

	@Override
	protected void onPluginStart() {//enable bstats
		Metrics metrics = new Metrics(this, 24524);
	}
}
