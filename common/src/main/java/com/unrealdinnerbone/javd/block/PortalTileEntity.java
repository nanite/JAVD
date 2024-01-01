package com.unrealdinnerbone.javd.block;

import com.unrealdinnerbone.javd.JAVDRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PortalTileEntity extends BlockEntity {

    public PortalTileEntity(BlockPos blockPos, BlockState blockState) {
        super(JAVDRegistry.PORTAL.get(), blockPos, blockState);
    }

}
