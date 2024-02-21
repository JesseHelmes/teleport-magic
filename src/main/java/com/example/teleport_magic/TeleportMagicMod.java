package com.example.teleport_magic;

import com.example.teleport_magic.init.ItemInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TeleportMagicMod.MODID)
public class TeleportMagicMod {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "teleport_magic";

	public TeleportMagicMod() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		// Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}
}
