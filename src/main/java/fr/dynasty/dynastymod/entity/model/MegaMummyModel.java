package fr.dynasty.dynastymod.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.dynasty.dynastymod.entity.custom.MegaMummyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MegaMummyModel <T extends MegaMummyEntity> extends EntityModel<T> {
    private final ModelRenderer waist;
    private final ModelRenderer body;
    private final ModelRenderer body_r1;
    private final ModelRenderer head;
    private final ModelRenderer head_r1;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightArm_r1;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightLeg;
    private final ModelRenderer rightLeg_r1;
    private final ModelRenderer leftLeg;
    private final ModelRenderer leftLeg_r1;

    public MegaMummyModel() {
        texWidth = 256;
        texHeight = 256;

        waist = new ModelRenderer(this);
        waist.setPos(0.0F, -38.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        waist.addChild(body);


        body_r1 = new ModelRenderer(this);
        body_r1.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(body_r1);
        setRotationAngle(body_r1, 0.0F, 0.0F, 0.0F);
        body_r1.texOffs(56, 48).addBox(-18.0F, 0.0F, -9.0F, 36.0F, 36.0F, 18.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(head);


        head_r1 = new ModelRenderer(this);
        head_r1.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(head_r1);
        setRotationAngle(head_r1, 0.0F, 0.0F, 0.0F);
        head_r1.texOffs(0, 0).addBox(-12.0F, -24.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setPos(-20.0F, 10.0F, 0.0F);
        body.addChild(rightArm);


        rightArm_r1 = new ModelRenderer(this);
        rightArm_r1.setPos(0.0F, 0.0F, 0.0F);
        rightArm.addChild(rightArm_r1);
        setRotationAngle(rightArm_r1, 0.0F, 0.0F, 0.0F);
        rightArm_r1.texOffs(0, 48).addBox(-12.0F, -10.0F, -7.0F, 14.0F, 40.0F, 14.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setPos(20.0F, 10.0F, 0.0F);
        body.addChild(leftArm);
        leftArm.texOffs(0, 48).addBox(-2.0F, -10.0F, -7.0F, 14.0F, 40.0F, 14.0F, 0.0F, true);

        rightLeg = new ModelRenderer(this);
        rightLeg.setPos(-8.0F, 36.0F, 0.0F);
        body.addChild(rightLeg);


        rightLeg_r1 = new ModelRenderer(this);
        rightLeg_r1.setPos(0.0F, 0.0F, 0.0F);
        rightLeg.addChild(rightLeg_r1);
        setRotationAngle(rightLeg_r1, 0.0F, 0.0F, 0.0F);
        rightLeg_r1.texOffs(164, 62).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 26.0F, 14.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setPos(8.0F, 36.0F, 0.0F);
        body.addChild(leftLeg);


        leftLeg_r1 = new ModelRenderer(this);
        leftLeg_r1.setPos(0.0F, 0.0F, 0.0F);
        leftLeg.addChild(leftLeg_r1);
        setRotationAngle(leftLeg_r1, 0.0F, 0.0F, 0.0F);
        leftLeg_r1.texOffs(164, 62).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 26.0F, 14.0F, 0.0F, true);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F) - 1f / 180f;
        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        waist.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    public boolean isAggressive(T entityIn) {
        return entityIn.isAggressive();
    }
}
