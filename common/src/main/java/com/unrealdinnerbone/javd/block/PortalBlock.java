package com.unrealdinnerbone.javd.block;

import com.unrealdinnerbone.javd.JAVDRegistry;
import com.unrealdinnerbone.javd.util.TelerportUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class PortalBlock extends Block implements EntityBlock {


    public PortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hand) {
        if (!level.isClientSide()) {
            boolean isInVoid = level.dimensionTypeRegistration().is(JAVDRegistry.Keys.DIMENSION_TYPE);
            TelerportUtils.teleport(player, isInVoid ? Level.OVERWORLD : JAVDRegistry.Keys.LEVEL, pos, !isInVoid);
            return InteractionResult.SUCCESS;
        }else {
            return InteractionResult.PASS;
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return JAVDRegistry.PORTAL.value().create(pos, state);
    }
}
