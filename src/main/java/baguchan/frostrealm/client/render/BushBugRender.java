package baguchan.frostrealm.client.render;

import baguchan.frostrealm.FrostRealm;
import baguchan.frostrealm.client.FrostModelLayers;
import baguchan.frostrealm.client.model.BushBugModel;
import baguchan.frostrealm.client.render.layer.BushBugBushLayer;
import baguchan.frostrealm.entity.BushBug;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BushBugRender<T extends BushBug> extends MobRenderer<T, BushBugModel<T>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FrostRealm.MODID, "textures/entity/bush_bug/bush_bug.png");

    public BushBugRender(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new BushBugModel<>(p_173952_.bakeLayer(FrostModelLayers.BUSH_BUG)), 0.3F);
        this.addLayer(new BushBugBushLayer<>(this));
    }

    public void render(T p_116219_, float p_116220_, float p_116221_, PoseStack p_116222_, MultiBufferSource p_116223_, int p_116224_) {
        int color = p_116219_.level().getBlockTint(p_116219_.blockPosition(), BiomeColors.GRASS_COLOR_RESOLVER);
        int i = (color & 0xFF0000) >> 16;
        int j = (color & 0xFF00) >> 8;
        int k = (color & 0xFF) >> 0;

        this.model.setColor(i / 255.0F, j / 255.0F, k / 255.0F);
        super.render(p_116219_, p_116220_, p_116221_, p_116222_, p_116223_, p_116224_);
        this.model.setColor(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void scale(T p_115314_, PoseStack p_115315_, float p_115316_) {
        float size = p_115314_.getScale();
        p_115315_.scale(size, size, size);
        super.scale(p_115314_, p_115315_, p_115316_);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return TEXTURE;
    }
}