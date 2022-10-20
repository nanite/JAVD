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
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class PortalBlock extends Block implements EntityBlock {


    public PortalBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE));
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            boolean isInVoid = level.dimension().location().equals(JAVD.DIM_ID);
            TelerportUtils.teleport(player, isInVoid ? Level.OVERWORLD : JAVD.MINING_KEY, pos, !isInVoid);
        }
        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return JAVDRegistry.PORTAL.get().create(pos, state);
    }
}
