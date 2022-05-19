package se.datasektionen.lava.entities;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import se.datasektionen.lava.LavaMod;

public class ShapeshifterRenderer<T extends ShapeshifterEntity> extends MobRenderer<T, EntityModel<T>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LavaMod.MODID,
			"textures/entity/shapeshifter.png");

	public ShapeshifterRenderer(EntityRendererProvider.Context context) {
		//super(context, (EntityModel<T>) createModel(context, typeMob), 0.5f);
		super(context, (EntityModel<T>) createModel(context), 0.5f);


	}

	private static EntityModel<ShapeshifterEntity> createModel(Context context) {
		ShapeshifterModel m = new ShapeshifterModel<>(context.bakeLayer(ShapeshifterModel.SHAPESHIFTER_LAYER));
		int mobType = m.getTypeMob();
		if (mobType == 1) {
			return new SheepModel(context.bakeLayer(ModelLayers.SHEEP));
		}else if (mobType == 0) {
			return new ChickenModel<ShapeshifterEntity>(context.bakeLayer(ModelLayers.CHICKEN));
		}else {
			return new ShapeshifterModel<>(context.bakeLayer(ShapeshifterModel.SHAPESHIFTER_LAYER));
		}
		
	}

	public ResourceLocation getTextureLocation(ShapeshifterEntity entity) {
		return TEXTURE;
	}

}
