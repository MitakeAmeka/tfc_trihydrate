package com.example.examplemod.item;

import com.example.examplemod.item.customSword.CustomSwordItem;
import com.example.examplemod.item.customSword.CustomTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.example.examplemod.TFCTrihydrate.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> LTK_TOOL = ITEMS.register("ltk_tool",
            () -> new CustomSwordItem(
                    CustomTiers.LTK,
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
                            .stacksTo(1)
                            .attributes(
                                    SwordItem.createAttributes(CustomTiers.LTK, 3, -2.4f)
                            )
            )
    );

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }



}
