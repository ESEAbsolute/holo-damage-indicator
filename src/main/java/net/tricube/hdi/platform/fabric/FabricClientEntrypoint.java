package net.tricube.hdi.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.tricube.hdi.ModInit;
import net.fabricmc.loader.api.FabricLoader;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModInit.onInitializeClient();
	}
}
//?}
