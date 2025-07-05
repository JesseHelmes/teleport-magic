package com.example.teleport_magic;

import com.example.teleport_magic.init.ItemInit;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(TeleportMagicMod.MODID)
public class TeleportMagicMod {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "teleport_magic";

	public TeleportMagicMod(IEventBus modEventBus, ModContainer modContainer) {
		ItemInit.ITEMS.register(modEventBus);
	}
}
