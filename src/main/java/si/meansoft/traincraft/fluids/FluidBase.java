/*
 * This file ("FluidBase.java") is part of the Traincraft mod for Minecraft.
 * It is created by all people that are listed with @author below.
 * It is distributed under the Traincraft License (https://github.com/Traincraft/Traincraft/blob/master/LICENSE.md)
 * You can find the source code at https://github.com/Traincraft/Traincraft
 *
 * © 2011-2017
 */

package si.meansoft.traincraft.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import si.meansoft.traincraft.IRegistryEntry;
import si.meansoft.traincraft.Registry;
import si.meansoft.traincraft.Traincraft;
import si.meansoft.traincraft.items.ItemBlockBase;
import si.meansoft.traincraft.network.CommonProxy;

/**
 * @author canitzp
 */
public class FluidBase extends Fluid implements IRegistryEntry {

    private Material material;
    public FluidBlockBase fluidBlock;

    public FluidBase(String fluidName, String textureName, Material material) {
        super(fluidName, new ResourceLocation(Traincraft.MODID, "fluids/" + textureName + "_still"), new ResourceLocation(Traincraft.MODID, "fluids/" + textureName + "_flowing"));
        this.material = material;
    }

    @Override
    public IRegistryEntry[] getRegisterElements() {
        return new IRegistryEntry[]{this};
    }

    @Override
    public String getRegisterName() {
        return this.getName();
    }

    @Override
    public void onRegister(IRegistryEntry[] otherEntries) {

    }

    @Override
    public void ownRegistry() {
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
        CommonProxy.addFluid(this);
        this.fluidBlock = new FluidBlockBase(this, this.material);
        Registry.register(this.fluidBlock, new ItemBlockBase(this.fluidBlock));
        CommonProxy.addForgeRender(Item.getItemFromBlock(this.fluidBlock));
    }
}
