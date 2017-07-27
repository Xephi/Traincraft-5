/*
 * This file ("BlockTrackCurve.java") is part of the Traincraft mod for Minecraft.
 * It is created by all people that are listed with @author below.
 * It is distributed under the Traincraft License (https://github.com/Traincraft/Traincraft/blob/master/LICENSE.md)
 * You can find the source code at https://github.com/Traincraft/Traincraft
 *
 * © 2011-2017
 */

package si.meansoft.traincraft.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import si.meansoft.traincraft.Registry;
import si.meansoft.traincraft.api.AbstractBlockTrack;
import si.meansoft.traincraft.items.ItemBlockBase;
import si.meansoft.traincraft.items.ItemBlockTrack;
import si.meansoft.traincraft.tile.TileEntityTrack;
import si.meansoft.traincraft.track.TrackPoint;
import si.meansoft.traincraft.track.TrackType;

import javax.annotation.Nullable;

/**
 * @author canitzp
 */
public class BlockTrackCurve extends AbstractBlockTrack {

    public BlockTrackCurve(TrackType type) {
        super(type, TileEntityTrack.class);
    }

    public static void register() {
        for (TrackType type : TrackType.values()) {
            if (type.isCurve()) {
                Registry.register(type.setBlock(new BlockTrackCurve(type)));
            }
        }
    }

    @Override
    public void removeTrack(World world, BlockPos pos, boolean dropBlocks) {
        TileEntityTrack tile = (TileEntityTrack) world.getTileEntity(pos);
        if (tile != null) {
            if (tile.defaultTrackPosition != null) {
                IBlockState state = world.getBlockState(tile.defaultTrackPosition);
                if (state.getBlock() instanceof BlockTrackCurve) {
                    ((BlockTrackCurve) state.getBlock()).removeTrack(world, tile.defaultTrackPosition, dropBlocks);
                }
            } else if (!tile.toDestroy.isEmpty()) {
                for (BlockPos pos1 : tile.toDestroy) {
                    world.destroyBlock(pos1, dropBlocks);
                }
                world.destroyBlock(tile.getPos(), dropBlocks);
            }
        }
    }

    @Nullable
    @Override
    public TrackPoint getWaypoints(World world, BlockPos pos, IBlockState state, int blockIndex) {
        //TODO
        return BlockTrackStraight.block.getWaypoints(world, pos, state, blockIndex);
    }

    @Override
    public boolean canDriveOver(TileEntityTrack thisTrack, TileEntityTrack incomingTrack) {
        //TODO
        return true;
    }

    @Override
    protected ItemBlockBase getItemBlock(AbstractBlockTrack track) {
        return new ItemBlockTrack(this);
    }

    @Override
    protected boolean shouldRenderDefault() {
        return false;
    }

}
