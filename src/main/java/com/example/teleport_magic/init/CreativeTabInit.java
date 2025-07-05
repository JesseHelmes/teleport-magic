package com.example.teleport_magic.init;

import com.example.teleport_magic.TeleportMagicMod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = TeleportMagicMod.MODID, value = Dist.CLIENT)
public class CreativeTabInit {
	@SubscribeEvent
	public static void buildContents(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() != CreativeModeTabs.COMBAT) {
			return;
		}

		event.insertAfter(Items.NETHERITE_SWORD.getDefaultInstance(),
				ItemInit.TELEPORT_MAGIC.get().getDefaultInstance(),
				CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
}
