package com.unrealdinnerbone.javd;

import com.unrealdinnerbone.javd.block.PortalBlock;
import com.unrealdinnerbone.javd.block.PortalTileEntity;
import com.unrealdinnerbone.trenzalore.api.platform.services.ICreativeTabRegister;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.BlockRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.ItemRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.MapColor;

import java.util.List;
import java.util.function.UnaryOperator;

public class JAVDRegistry implements IRegistry {


    private static final BlockRegistryObjects BLOCKS = Regeneration.createBlockRegistry(JAVD.MOD_ID);
    private static final ItemRegistryObjects ITEMS = Regeneration.createItemRegistry(JAVD.MOD_ID);

    private static final RegistryObjects<BlockEntityType<?>> TILES = Regeneration.create(JAVD.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistryEntry.BlockEntry<PortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", PortalBlock::new, properties -> properties.strength(5.0F, 6.0F).sound(SoundType.STONE).mapColor(MapColor.COLOR_BLUE));

    public static final RegistryEntry.ItemEntry<BlockItem> PORTAL_BLOCK_ITEM = ITEMS.registerBlockItem("portal_block", PORTAL_BLOCK, UnaryOperator.identity());

    public static final Holder<BlockEntityType<?>> PORTAL = TILES.register("portal", () -> Regeneration.createBEType(PortalTileEntity::new, PORTAL_BLOCK.getHolder().value()));

    public static final TagKey<Block> GENERATOR_BLOCKS = TagKey.create(Registries.BLOCK, JAVD.rl( "generator"));


    public static class Keys {
        public static final ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, JAVD.rl("void"));
        public static final ResourceKey<Biome> BIOME = ResourceKey.create(Registries.BIOME, JAVD.rl( "void"));

        public static final ResourceKey<Level> LEVEL = ResourceKey.create(Registries.DIMENSION, JAVD.rl("void"));
    }

    @Override
    public void afterRegistered(ICreativeTabRegister register) {
        register.addItemToCreativeTab(register.tabs().toolsAndUtilities(), List.of(PORTAL_BLOCK_ITEM));
    }

    @Override
    public List<AbstractRegistryObjects<?>> getRegistryObjects() {
        return List.of(BLOCKS, ITEMS, TILES);
    }

    @Override
    public String getModID() {
        return JAVD.MOD_ID;
    }
}
