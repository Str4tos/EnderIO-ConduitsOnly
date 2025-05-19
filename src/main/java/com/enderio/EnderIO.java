package com.enderio;

import com.enderio.api.integration.IntegrationManager;
import com.enderio.base.common.config.BaseConfig;
import com.enderio.base.common.init.EIOCreativeTabs;
import com.enderio.base.common.init.EIOItems;
import com.enderio.base.common.init.EIOMenus;
import com.enderio.base.common.init.EIOPackets;
import com.enderio.base.common.init.EIOParticles;
import com.enderio.base.common.init.EIORecipes;
import com.enderio.base.common.lang.EIOLang;
import com.enderio.base.common.network.EIONetwork;
import com.enderio.base.data.EIODataProvider;
import com.enderio.base.data.recipe.FilterRecipeProvider;
import com.enderio.core.EnderCore;
import com.enderio.core.common.menu.FluidFilterSlot;
import com.enderio.core.common.menu.ItemFilterSlot;
import com.enderio.core.common.network.CoreNetwork;
import com.tterrag.registrate.Registrate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;

@Mod.EventBusSubscriber(modid = EnderIO.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(EnderIO.MODID)
public class EnderIO {
    // The Mod ID. This is stored in EnderCore as it's the furthest source away but it ensures that it is constant across all source sets.
    public static final String MODID = EnderCore.MODID;

    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static Registrate registrate() {
        return REGISTRATE.get();
    }

    public EnderIO() {
        // Ensure the enderio config subdirectory is present.
        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(MODID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register config files
        var ctx = ModLoadingContext.get();
        ctx.registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON_SPEC, "enderio/base-common.toml");
        ctx.registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_SPEC, "enderio/base-client.toml");

        // Setup core networking now
        CoreNetwork.networkInit();

        // Perform initialization and registration for everything so things are registered.
        EIOCreativeTabs.register();
        EIOItems.register();
        EIOMenus.register();
        EIOPackets.register();
        EIOLang.register();
        EIORecipes.register();
        EIOParticles.register();

        // Run datagen after registrate is finished.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EventPriority.LOWEST, this::onGatherData);

        // Decor
        EIONetwork.register();
    }

    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        EIODataProvider provider = new EIODataProvider("base");

        provider.addSubProvider(event.includeServer(), new FilterRecipeProvider(packOutput));
        generator.addProvider(true, provider);
    }

    @SubscribeEvent
    public static void sendIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo("inventorysorter", "slotblacklist", ItemFilterSlot.class::getName);
        InterModComms.sendTo("inventorysorter", "slotblacklist", FluidFilterSlot.class::getName);
    }
}
