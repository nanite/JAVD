package com.unrealdinnerbone.javd.block;

import com.unrealdinnerbone.javd.JAVD;
import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.javd.util.TelerportUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class PortalBlock extends Block implements EntityBlock {


    public PortalBlock() {
        super(Properties.of().strength(5.0F, 6.0F).sound(SoundType.STONE).mapColor(MapColor.COLOR_BLUE));
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hand) {
        if (!level.isClientSide()) {
            boolean isInVoid = level.dimensionTypeRegistration().is(JAVDRegistry.Keys.DIMENSION_TYPE);
            TelerportUtils.teleport(player, isInVoid ? Level.OVERWORLD : JAVDRegistry.Keys.LEVEL, pos, !isInVoid);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }else {
            return InteractionResult.PASS;
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return JAVDRegistry.PORTAL.get().create(pos, state);
    }
}
