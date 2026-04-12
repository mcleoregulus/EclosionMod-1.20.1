package com.leoregulus.eclosion.block.entity.server;

import com.leoregulus.eclosion.block.ModBlocks;
import com.leoregulus.eclosion.block.entity.AnimatedBlockEntity;
import com.leoregulus.eclosion.gui.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AnimatedBlockMenu extends AbstractContainerMenu {
    public final AnimatedBlockEntity blockEntity;

    // 客户端调用的构造函数（通过数据包传递位置）
    public AnimatedBlockMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    // 服务端调用的构造函数
    public AnimatedBlockMenu(int containerId, Inventory playerInventory, BlockEntity entity) {
        // ModMenuTypes.ANIMATED_BLOCK_MENU 是我们在第三步要注册的东西
        super(ModMenuTypes.ANIMATED_BLOCK_MENU.get(), containerId);
        this.blockEntity = (AnimatedBlockEntity) entity;

        // 【重要】如果你需要放物品，这里需要编写添加“玩家背包槽位”和“方块自身槽位”的代码
        // 暂略：如果只是个纯按钮界面，不加槽位也可以
    }

    // 决定玩家距离方块多远时，界面会自动关闭（防止玩家跑远了还在操作）
    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                player, ModBlocks.ANIMATED_BLOCK.get());
    }

    // 必须重写的方法：处理玩家按 Shift 快速移动物品的逻辑（如果没有槽位，直接返回 ItemStack.EMPTY）
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}