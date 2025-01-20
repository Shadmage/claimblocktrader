package dev.shadmage.cbtrader.commands;

import dev.shadmage.cbtrader.settings.Settings;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public final class buycbCommand extends SimpleCommand {
	public buycbCommand() {
		super("buycb");
		setPermission("cbtrader.command.buycb");
		setMinArguments(1);
		setUsage("<quantity_items_to_Sell>");
		setDescription("Trade items for claimblocks");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = getPlayer();

		if (args.length == 0) {
			tellInfo("You can trade " + Settings.CBTrader.CURRENCY_ITEM.name().replace("_", " ").toLowerCase() + "s for " + Settings.CBTrader.QUANTITY_PER_ITEM + " claimblocks per item");
		} else {
			try{
				Integer tradeAmount = Integer.valueOf(args[0]);
				Integer claimBlocks = tradeAmount * Settings.CBTrader.QUANTITY_PER_ITEM;

				if(claimBlocks > 0 && tradeAmount > 0){
					if(player.getInventory().containsAtLeast(Settings.CBTrader.CURRENCY_ITEM.toItem(), tradeAmount)){
						if(PlayerUtil.take(player, Settings.CBTrader.CURRENCY_ITEM, tradeAmount)){
							GriefPrevention gp = GriefPrevention.instance;
							PlayerData playerData = gp.dataStore.getPlayerData(player.getUniqueId());
							playerData.setBonusClaimBlocks(playerData.getBonusClaimBlocks() + claimBlocks);
							tellSuccess("You have exchanged " + tradeAmount + " x " + Settings.CBTrader.CURRENCY_ITEM.name() + " for " + claimBlocks + " claimblocks.");
							tellInfo("Total claimblocks available: " + playerData.getRemainingClaimBlocks() + ".");
							Common.log(player.getName() + " has exchanged " + tradeAmount + " x " + Settings.CBTrader.CURRENCY_ITEM.name() + " for " + claimBlocks + " claimblocks.");
						} else {
							tellWarn("Unable to remove items from your inventory. Please log a ticket with our support team.");
							Common.log("WARNING: we couldn't remove items from " + player.getName() + "'s inventory.");
						}
					} else {
						tellWarn("You do not have enough " + Settings.CBTrader.CURRENCY_ITEM + " to do that.");
					}
				} else {
					tellWarn("You need to enter a valid number of " + Settings.CBTrader.CURRENCY_ITEM.name() + " that you want to trade for claimblocks.");
				}

			} catch (Exception ex){
				tellError("Please check usage by typing /buycb. Then try again.");
				Common.log("Error while handling cbTrade command for player: " + player.getName());
				ex.printStackTrace();
			}
		}



	}

	@Override
	protected List<String> tabComplete() {
		return new ArrayList<>();
	}
}
