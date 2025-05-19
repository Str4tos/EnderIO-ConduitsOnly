package com.enderio.conduits.data.recipe;

import com.enderio.EnderIO;
import com.enderio.base.common.init.EIOItems;
import com.enderio.base.common.tag.EIOTags;
import com.enderio.conduits.common.init.ConduitItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RedstoneFilterRecipes extends RecipeProvider {
    public RedstoneFilterRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {


        conversionRecipes(consumer);
    }

    private void conversionRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.OR_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.NOR_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.NOR_FILTER))
            .save(consumer, EnderIO.loc("or_filter_from_nor_filter"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.NOR_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.OR_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.OR_FILTER))
            .save(consumer, EnderIO.loc("nor_filter_from_or_filter"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.AND_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.NAND_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.NAND_FILTER))
            .save(consumer, EnderIO.loc("and_filter_from_nand_filter"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.NAND_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.AND_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.AND_FILTER))
            .save(consumer, EnderIO.loc("nand_filter_from_and_filter"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.XOR_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.XNOR_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.XNOR_FILTER))
            .save(consumer, EnderIO.loc("xor_filter_from_xnor_filter"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ConduitItems.XNOR_FILTER)
            .requires(Items.REDSTONE_TORCH)
            .requires(ConduitItems.XOR_FILTER)
            .unlockedBy("has_ingredient", has(ConduitItems.XOR_FILTER))
            .save(consumer, EnderIO.loc("xnor_filter_from_xor_filter"));
    }
}
