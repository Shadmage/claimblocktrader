package dev.shadmage.cbtrader;

import dev.shadmage.cbtrader._external.Metrics;
import dev.shadmage.cbtrader._external.SpigotUpdateChecker;
import org.mineacademy.fo.plugin.SimplePlugin;

public class cbtrader extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Metrics metrics = new Metrics(this, 24524);
		SpigotUpdateChecker spigotUpdateChecker = new SpigotUpdateChecker(this, 122057);
	}
}
