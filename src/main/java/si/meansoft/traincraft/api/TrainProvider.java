/*
 * This file ("TrainProvider.java") is part of the Traincraft mod for Minecraft.
 * It is created by all people that are listed with @author below.
 * It is distributed under the Traincraft License (https://github.com/Traincraft/Traincraft/blob/master/LICENSE.md)
 * You can find the source code at https://github.com/Traincraft/Traincraft
 *
 * © 2011-2017
 */

package si.meansoft.traincraft.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import si.meansoft.traincraft.IRegistryEntry;
import si.meansoft.traincraft.client.models.TrainModel;
import si.meansoft.traincraft.client.renderer.TrainRenderer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class TrainProvider<T extends TrainBase> implements IRegistryEntry {

    public static final Map<String, TrainModel<? extends TrainBase>> modelMap = new HashMap<>();

    private Class<T> trainClass;
    private String trainName;
    private TrainModel<T> trainModel;

    private TrainProvider(Class<T> trainClass, String trainName, TrainModel<T> model) {
        this.trainClass = trainClass;
        this.trainName = trainName;
        this.trainModel = model;
        modelMap.put(trainName, model);
    }

    @Override
    public IRegistryEntry[] getRegisterElements() {
        return new IRegistryEntry[]{this};
    }

    @Override
    public String getRegisterName() {
        return this.trainName;
    }

    @Override
    public void onRegister(IRegistryEntry[] otherEntries) {

    }

    @Override
    public void ownRegistry() {
        //EntityRegistry.registerModEntity(new ResourceLocation(Traincraft.MODID, this.getRegisterName()), this.trainClass, this.getRegisterName(), Registry.entityIds++, Traincraft.INSTANCE, 64, 1, true);
    }

    @Override
    public void loadClientSide() {
        RenderingRegistry.registerEntityRenderingHandler(this.trainClass, manager -> {
            TrainRenderer<T> renderer = new TrainRenderer<>(manager, trainModel);
            ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(renderer);
            return renderer;
        });
    }

    public static <T extends TrainBase> TrainProvider<T> create(Class<T> clazz, String name, TrainModel<T> model) {
        return new TrainProvider<>(clazz, name, model);
    }

}
