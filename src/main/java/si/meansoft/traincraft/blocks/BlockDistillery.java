/*
 * This file ("BlockDistillery.java") is part of the Traincraft mod for Minecraft.
 * It is created by all people that are listed with @author below.
 * It is distributed under the Traincraft License (https://github.com/Traincraft/Traincraft/blob/master/LICENSE.md)
 * You can find the source code at https://github.com/Traincraft/Traincraft
 *
 * © 2011-2017
 */

package si.meansoft.traincraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.EnumFacing;
import si.meansoft.traincraft.client.gui.GuiDistillery;
import si.meansoft.traincraft.container.ContainerDistillery;
import si.meansoft.traincraft.network.GuiHandler;
import si.meansoft.traincraft.tile.TileEntityDistillery;

/**
 * @author canitzp
 */
public class BlockDistillery extends BlockContainerBase {

    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockDistillery() {
        super(Material.IRON, "distillery", TileEntityDistillery.class);
        addGuiContainer(GuiHandler.DISTILLERY, GuiDistillery.class, ContainerDistillery.class);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(ACTIVE, false).withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVE, FACING);
    }

}
