package com.leoregulus.eclosion.gui;

import com.leoregulus.eclosion.block.entity.server.AnimatedBlockMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AnimatedBlockScreen extends AbstractContainerScreen<AnimatedBlockMenu> {
    // 你的 GUI 贴图路径（需要你在 src/main/resources/assets/eclosion/textures/gui/ 下放一张图片）
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("eclosion", "textures/gui/animated_block_gui.png");

    public AnimatedBlockScreen(AnimatedBlockMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // 这里可以设置你的 GUI 图片的宽高
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        // 如果去掉了玩家背包槽位，这里可以调整标题显示的位置
        this.inventoryLabelY = 10000; // 把玩家背包的标题移出屏幕外隐藏掉
    }

    // 绘制 GUI 背景贴图
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // 计算把 GUI 画在屏幕正中央的坐标
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        // 渲染贴图
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    // 渲染主干（必须包含，以确保能画出暗色背景和工具提示）
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics); // 画后面的半透明黑底
        super.render(guiGraphics, mouseX, mouseY, delta); // 画 GUI 本体和槽位
        renderTooltip(guiGraphics, mouseX, mouseY); // 画鼠标悬停的物品信息
    }
}