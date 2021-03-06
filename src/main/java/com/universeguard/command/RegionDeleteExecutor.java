/* 
 * Copyright (C) JimiIT92 - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Jimi, December 2017
 * 
 */
package com.universeguard.command;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import com.universeguard.region.Region;
import com.universeguard.region.enums.RegionText;
import com.universeguard.utils.MessageUtils;
import com.universeguard.utils.RegionUtils;

/**
 * 
 * Command Handler for /rg delete
 * @author Jimi
 *
 */
public class RegionDeleteExecutor implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (args.hasAny("name")) {
			Region region = RegionUtils.load(args.<String>getOne("name").get());
			if (region != null) {
				if (RegionUtils.remove(region)) {
					MessageUtils.sendSuccessMessage(src, RegionText.REGION_REMOVED.getValue());
				} else
					MessageUtils.sendErrorMessage(src, RegionText.REGION_NOT_REMOVED.getValue());
			} else
				MessageUtils.sendErrorMessage(src, RegionText.REGION_NOT_FOUND.getValue());
		} else {
			MessageUtils.sendErrorMessage(src, getCommandUsage());
		}

		return CommandResult.empty();
	}

	private String getCommandUsage() {
		return "/rg delete <name>";
	}

}