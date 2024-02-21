package com.example.teleport_magic.init;

import com.example.teleport_magic.TeleportMagicMod;
import com.example.teleport_magic.item.TeleportMagic;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			TeleportMagicMod.MODID);

	public static final RegistryObject<TeleportMagic> TELEPORT_MAGIC = ITEMS.register("teleport_magic",
	() -> new TeleportMagic((new Item.Properties()).fireResistant().stacksTo(1)));
}
