package com.enderio.base.common.init;

import com.enderio.EnderIO;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = EnderIO.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EIOCreativeTabs {
    private static final Registrate REGISTRATE = EnderIO.registrate();

    public static final ResourceKey<CreativeModeTab> MAIN = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(EnderIO.MODID, "main"));
    public static final ResourceKey<CreativeModeTab> GEAR = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(EnderIO.MODID, "gear"));
    public static final ResourceKey<CreativeModeTab> CONDUITS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(EnderIO.MODID, "conduits"));


    public static void register() {
    }
}
