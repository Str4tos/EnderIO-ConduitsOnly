package com.enderio.base.data.recipe;

import com.enderio.EnderIO;
import com.enderio.base.common.init.EIOItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class FilterRecipeProvider extends RecipeProvider {

    public FilterRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EIOItems.BASIC_ITEM_FILTER.get())
            .define('P', Items.PAPER) // TODO: forge:paper?
            .define('H', Items.HOPPER)
            .pattern(" P ")
            .pattern("PHP")
            .pattern(" P ")
            .unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HOPPER))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EIOItems.ADVANCED_ITEM_FILTER.get())
            .define('P', Items.PAPER) // TODO: forge:paper?
            .define('Z', EIOItems.BASIC_ITEM_FILTER)
            .define('R', Tags.Items.DUSTS_REDSTONE)
            .pattern("RPR")
            .pattern("PZP")
            .pattern("RPR")
            .unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(EIOItems.BASIC_ITEM_FILTER))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EIOItems.BASIC_FLUID_FILTER.get())
            .define('P', Items.PAPER) // TODO: forge:paper?
            .define('B', Items.BUCKET)
            .pattern(" P ")
            .pattern("PBP")
            .pattern(" P ")
            .unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
            .save(consumer);

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EIOItems.ENTITY_FILTER.get())
//            .define('P', Items.PAPER) // TODO: c:paper?
//            .define('S', Items.LAPIS_LAZULI)
//            .pattern(" P ")
//            .pattern("PSP")
//            .pattern(" P ")
//            .unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(EIOItems.EMPTY_SOUL_VIAL))
//            .save(consumer);

        addCraftingComponents(consumer);
    }

    private void addCraftingComponents(Consumer<FinishedRecipe> recipeConsumer) {
        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, EIOItems.CONDUIT_BINDER_COMPOSITE.get(), 8)
            .pattern("GCG")
            .pattern("SGS")
            .pattern("GCG")
            .define('G', Tags.Items.GRAVEL)
            .define('S', Tags.Items.SAND)
            .define('C', Items.CLAY_BALL)
            .unlockedBy("has_ingredient_gravel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAVEL))
            .unlockedBy("has_ingredient_sand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SAND))
            .unlockedBy("has_ingredient_clay", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLAY_BALL))
            .save(recipeConsumer);

        MultipleCookingRecipeBuilder
            .smelting(Ingredient.of(EIOItems.CONDUIT_BINDER_COMPOSITE.get()), RecipeCategory.MISC, new ItemStack(EIOItems.CONDUIT_BINDER.get(), 2), 0, 100)
            .unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(EIOItems.CONDUIT_BINDER_COMPOSITE.get()))
            .save(recipeConsumer, EnderIO.loc(EIOItems.CONDUIT_BINDER.getId().getPath() + "_from_smelting"));
    }
}
