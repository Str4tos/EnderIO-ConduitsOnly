package com.enderio.base.client.tooltip;

import com.enderio.base.common.lang.EIOLang;
import com.enderio.core.client.item.IAdvancedTooltipProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 1.19: Move to core. Need to work out what to do about the shift lang key. Will now need decoupled from the capacitor and grindingball logic.
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipHandler {

    private static final Component DETAIL_TOOLTIP = EIOLang.SHOW_DETAIL_TOOLTIP.copy().withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC);

    @SubscribeEvent
    public static void addAdvancedTooltips(ItemTooltipEvent evt) {
        ItemStack forItem = evt.getItemStack();
        boolean advanced = Screen.hasShiftDown();

        // Advanced tooltip system
        getAdvancedProvider(forItem.getItem()).ifPresent(provider ->
            addAdvancedTooltips(provider, forItem, evt.getEntity(), evt.getToolTip(), advanced)
        );
    }




    // endregion

    // region Advanced Tooltips

    private static Optional<IAdvancedTooltipProvider> getAdvancedProvider(Item item) {
        if (item instanceof IAdvancedTooltipProvider provider) {
            return Optional.of(provider);
        }

        if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof IAdvancedTooltipProvider provider) {
            return Optional.of(provider);
        }

        return Optional.empty();
    }

    private static void addAdvancedTooltips(IAdvancedTooltipProvider tooltipProvider, ItemStack itemstack, @Nullable Player player, List<Component> components, boolean showAdvanced) {
        tooltipProvider.addCommonTooltips(itemstack, player, components);
        if (showAdvanced) {
            tooltipProvider.addDetailedTooltips(itemstack, player, components);
        } else {
            tooltipProvider.addBasicTooltips(itemstack, player, components);
            if (hasDetailedTooltip(tooltipProvider, itemstack, player)) {
                addShowDetailsTooltip(components);
            }
        }
    }

    // endregion

    private static void addShowDetailsTooltip(List<Component> components) {
        if (!components.contains(DETAIL_TOOLTIP)) {
            components.add(DETAIL_TOOLTIP);
        }
    }

    private static boolean hasDetailedTooltip(IAdvancedTooltipProvider tooltipProvider, ItemStack stack, @Nullable Player player) {
        List<Component> tooltips = new ArrayList<>();
        tooltipProvider.addDetailedTooltips(stack, player, tooltips);
        return !tooltips.isEmpty();
    }
}
