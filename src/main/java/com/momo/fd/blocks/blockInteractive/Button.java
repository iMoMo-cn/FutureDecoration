package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Button extends BlockButton implements IHasModel {
    public boolean isWood;

    public Button(String name, boolean isWood){
        super(isWood);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(0.5F);

        this.isWood = isWood;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this){
            @Override
            public int getItemBurnTime(ItemStack itemStack)
                    {
                        return isWood? 100:-1;
                    }
        }.setRegistryName(this.getRegistryName())
        );
    }

    @Override
    protected void playClickSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos) {
        SoundEvent sound = SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;

        if (this.isWood) { sound = SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON;}

        worldIn.playSound(player, pos, sound, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    @Override
    protected void playReleaseSound(World worldIn, BlockPos pos) {
        SoundEvent sound = SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;

        if (this.isWood) { sound = SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF;}

        worldIn.playSound(null, pos, sound, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
