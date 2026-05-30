package net.tricube.hdi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.tricube.hdi.config.Config;

public  class FullbrightState {
	public static boolean requireUpdate;

	public static void setRequireUpdate(boolean reqUpdate) {
		requireUpdate = reqUpdate;
	}

	public static boolean requireUpdate() {
		return requireUpdate;
	}
}
