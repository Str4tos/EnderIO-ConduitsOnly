package com.enderio.base.common.init;

import com.enderio.EnderIO;
import com.enderio.base.common.item.filter.FluidFilter;
import com.enderio.base.common.item.filter.ItemFilter;
import com.enderio.base.common.item.misc.LocationPrintoutItem;
import com.enderio.base.common.item.misc.MaterialItem;
import com.enderio.base.common.item.tool.CoordinateSelectorItem;
import com.enderio.base.common.item.tool.YetaWrenchItem;
import com.enderio.base.common.tag.EIOTags;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class EIOItems {
    private static final Registrate REGISTRATE = EnderIO.registrate();

    public static final ItemEntry<MaterialItem> CONDUIT_BINDER_COMPOSITE = materialItem("conduit_binder_composite").register();

    public static final ItemEntry<MaterialItem> CONDUIT_BINDER = materialItem("conduit_binder").register();

    private static ItemBuilder<MaterialItem, Registrate> materialItem(String name) {
        return REGISTRATE.item(name, props -> new MaterialItem(props, false)).tab(EIOCreativeTabs.MAIN);
    }

    private static ItemBuilder<MaterialItem, Registrate> materialItemGlinted(String name) {
        return REGISTRATE.item(name, props -> new MaterialItem(props, true)).tab(EIOCreativeTabs.MAIN);
    }

    // region Tools
    public static final ItemEntry<YetaWrenchItem> YETA_WRENCH = REGISTRATE
        .item("yeta_wrench", YetaWrenchItem::new)
        .tab(EIOCreativeTabs.GEAR)
        .properties(props -> props.stacksTo(1))
        .tag(EIOTags.Items.WRENCH)
        .register();

    public static final ItemEntry<LocationPrintoutItem> LOCATION_PRINTOUT = REGISTRATE
        .item("location_printout", LocationPrintoutItem::new)
        .tab(EIOCreativeTabs.GEAR)
        .properties(props -> props.stacksTo(1))
        .register();

    public static final ItemEntry<CoordinateSelectorItem> COORDINATE_SELECTOR = REGISTRATE
        .item("coordinate_selector", CoordinateSelectorItem::new)
        .tab(EIOCreativeTabs.GEAR)
        .properties(props -> props.stacksTo(1))
        .register();


    public static final ItemEntry<ItemFilter> BASIC_ITEM_FILTER = REGISTRATE
        .item("basic_item_filter", props -> new ItemFilter(props, 5))
        .tab(EIOCreativeTabs.GEAR)
        .register();

    public static final ItemEntry<ItemFilter> ADVANCED_ITEM_FILTER = REGISTRATE
        .item("advanced_item_filter", props -> new ItemFilter(props, 10))
        .tab(EIOCreativeTabs.GEAR)
        .register();

    public static final ItemEntry<FluidFilter> BASIC_FLUID_FILTER = REGISTRATE
        .item("basic_fluid_filter", props -> new FluidFilter(props, 5))
        .tab(EIOCreativeTabs.GEAR)
        .register();

    // endregion

    // region description

    public static MutableComponent capacitorDescriptionBuilder(String type, String value, String description) {
        return REGISTRATE.addLang("description", EnderIO.loc("capacitor." + type + "." + value), description);
    }

    // endregion

    // region Creative Tab Icons

//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_NONE = dumbItem("enderface_none", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_ITEMS = dumbItem("enderface_items", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_MATERIALS = dumbItem("enderface_materials", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_MACHINES = dumbItem("enderface_machines", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_CONDUITS = dumbItem("enderface_conduits", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_MOBS = dumbItem("enderface_mobs", CreativeTabIconItem::new).register();
//    public static final ItemEntry<CreativeTabIconItem> CREATIVE_ICON_INVPANEL = dumbItem("enderface_invpanel", CreativeTabIconItem::new).register();

    // endregion

    // region Helpers

    public static <T extends Item> ItemBuilder<T, Registrate> dumbItem(String name, NonNullFunction<Item.Properties, T> factory) {
        return REGISTRATE.item(name, factory).removeTab(CreativeModeTabs.SEARCH);
    }

    public static ItemBuilder<Item, Registrate> dumbItem(String name) {
        return REGISTRATE.item(name, Item::new);
    }

    public static <T extends Item> ItemEntry<T> groupedItem(String name, NonNullFunction<Item.Properties, T> factory, ResourceKey<CreativeModeTab> tab) {
        return REGISTRATE.item(name, factory).tab(tab).register();
    }

    // endregion

    public static void register() {}
}
