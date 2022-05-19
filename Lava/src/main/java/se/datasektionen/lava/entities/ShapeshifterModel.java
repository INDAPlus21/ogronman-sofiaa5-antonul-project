package se.datasektionen.lava.entities;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import se.datasektionen.lava.LavaMod;

public class ShapeshifterModel<Type extends ShapeshifterEntity> extends ChickenModel<Type>{
	
	public static final String BODY = "body";
	
	private int typeMob;
	
	public static ModelLayerLocation SHAPESHIFTER_LAYER = new ModelLayerLocation(new ResourceLocation(LavaMod.MODID, "shapeshifter"), BODY);
	
	/**
	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = createMesh(CubeDeformation.NONE, 0.6f);
        return LayerDefinition.create(meshdefinition, 64, 32);
    }
    */
	
	public void prepareMobModel(Type entity, float p_102615_, float p_102616_, float p_102617_) {
		System.out.println("Getting the typeMob!!!");
		typeMob = entity.getCopyMob();
		System.out.println("The typeMob is: " + typeMob);
	}
	
	public ShapeshifterModel(ModelPart p_170490_) {
		super(p_170490_);
		// TODO Auto-generated constructor stub
	}

	public int getTypeMob() {
		return typeMob;
	}

	public void setTypeMob(int typeMob) {
		this.typeMob = typeMob;
	}
	
	

}
