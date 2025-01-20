package dev.shadmage.cbtrader.settings;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {
	public static String MY_PREFIX;

	private static void init() {
		pathPrefix("");
		MY_PREFIX = getString("Prefix");
		Common.setLogPrefix(MY_PREFIX);
	}

	@Override
	protected int getConfigVersion() {
		pathPrefix("");
		return getInteger("Version");
	}

	public static class CBTrader {

		public static CompMaterial CURRENCY_ITEM;
		public static Integer QUANTITY_PER_ITEM;

		private static void init() {
			pathPrefix("CBTrader");
			CURRENCY_ITEM = getMaterial("Currency");
			QUANTITY_PER_ITEM = getInteger("ClaimblocksPerItem");
		}
	}

}
