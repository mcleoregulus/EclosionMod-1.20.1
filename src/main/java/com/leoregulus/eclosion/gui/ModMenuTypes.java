package com.leoregulus.eclosion.gui;

import com.leoregulus.eclosion.block.entity.server.AnimatedBlockMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "eclosion"); // 填入你的 modid

    // 注册 MenuType，并指定它使用我们刚才写的带有 FriendlyByteBuf 的构造函数
    public static final RegistryObject<MenuType<AnimatedBlockMenu>> ANIMATED_BLOCK_MENU =
            MENUS.register("animated_block_menu", () -> IForgeMenuType.create(AnimatedBlockMenu::new));
}