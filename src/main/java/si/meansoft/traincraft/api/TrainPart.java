/*
 * This file ("TrainPart.java") is part of the Traincraft mod for Minecraft.
 * It is created by all people that are listed with @author below.
 * It is distributed under the Traincraft License (https://github.com/Traincraft/Traincraft/blob/master/LICENSE.md)
 * You can find the source code at https://github.com/Traincraft/Traincraft
 *
 * © 2011-2017
 */

package si.meansoft.traincraft.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import si.meansoft.traincraft.client.models.TrainModel;
import si.meansoft.traincraft.client.models.TrainModelRenderer;

/**
 * @author canitzp
 */
public class TrainPart<T extends TrainBase> extends Entity {

    private final T train;
    private final TrainParts part;
    private float xOffset, yOffset, zOffset;

    public TrainPart(T train, TrainParts part, float width, float height, float xOffset, float yOffset, float zOffset, TrainModel<? extends TrainBase> model) {
        super(train.getEntityWorld());
        this.train = train;
        this.part = part;
        this.setSize(width / 16, height / 16);
        this.xOffset = xOffset / 16 - model.getMaxWidth() / 2;
        this.yOffset = yOffset / 16 - model.getMaxHeight() / 2;
        this.zOffset = zOffset / 16 - model.getMaxDepth() / 2;
    }

    public TrainPart(T train, TrainParts part, TrainModelRenderer renderer, TrainModel<? extends TrainBase> model) {
        super(train.getEntityWorld());
        this.train = train;
        this.part = part;
        float width = renderer.getMaxWidth() / 16.0F;
        float height = renderer.getHeight() / 16.0F;
        this.setSize(width, height);

        this.xOffset = -renderer.getOffX() / 16 - model.getMaxWidth() / 2 + (renderer.getWidth() / 16) / 2;
        this.yOffset = renderer.getOffY() / 16 - model.getMaxHeight() / 2 + (renderer.getHeight() / 16) / 2 + model.getWheelHeight();
        this.zOffset = renderer.getOffZ() / 16 - model.getMaxDepth() / 2 + (renderer.getDepth() / 16) / 2;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    //@Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        return this.train.processInitialInteractPart(this, this.part, player, hand);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return !this.isEntityInvulnerable(source) && this.train.attackTrainPart(this, this.part, source, amount);
    }

    @Override
    public boolean isEntityEqual(Entity entityIn) {
        return this == entityIn || this.train == entityIn;
    }

    @Override
    public double getMountedYOffset() {
        return this.train.getMountedYOffset();
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return this.part.equals(TrainParts.SEET);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public float getzOffset() {
        return zOffset;
    }

    public enum TrainParts {
        MAIN,
        WHEEL,
        SEET
    }

}
