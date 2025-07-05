package com.example.teleport_magic.init;

import com.example.teleport_magic.TeleportMagicMod;
import com.example.teleport_magic.item.TeleportMagic;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TeleportMagicMod.MODID);

	public static final DeferredItem<TeleportMagic> TELEPORT_MAGIC = ITEMS.registerItem("teleport_magic",
			TeleportMagic::new,
			new Item.Properties().fireResistant().stacksTo(1)
		);
}
