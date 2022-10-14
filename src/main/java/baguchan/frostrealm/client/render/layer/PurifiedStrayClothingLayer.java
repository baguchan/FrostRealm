package baguchan.frostrealm.client.render.layer;

import baguchan.frostrealm.FrostRealm;
import baguchan.frostrealm.client.model.PurifiedStrayModel;
import baguchan.frostrealm.entity.PurifiedStray;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PurifiedStrayClothingLayer<T extends PurifiedStray, M extends PurifiedStrayModel<T>> extends RenderLayer<T, M> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(FrostRealm.MODID, "textures/entity/gazener/purified_stray_overlay.png");
	private final PurifiedStrayModel<T> layerModel;

	public PurifiedStrayClothingLayer(RenderLayerParent<T, M> p_174544_, EntityModelSet p_174545_) {
		super(p_174544_);
		this.layerModel = new PurifiedStrayModel<>(p_174545_.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
	}

	public void render(PoseStack p_117553_, MultiBufferSource p_117554_, int p_117555_, T p_117556_, float p_117557_, float p_117558_, float p_117559_, float p_117560_, float p_117561_, float p_117562_) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, TEXTURE, p_117553_, p_117554_, p_117555_, p_117556_, p_117557_, p_117558_, p_117560_, p_117561_, p_117562_, p_117559_, 1.0F, 1.0F, 1.0F);
	}
}