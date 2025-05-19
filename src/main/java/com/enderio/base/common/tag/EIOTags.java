package com.enderio.base.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.versions.forge.ForgeVersion;

public class EIOTags {

    public static void register() {
        Items.init();
    }

    public static class Items {

        private static void init() {}
    
        public static final TagKey<Item> WRENCH = ItemTags.create(new ResourceLocation(ForgeVersion.MOD_ID, "tools/wrench"));

    }

}
