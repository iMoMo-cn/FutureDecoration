package com.momo.fd.init;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class ModMerchantRecipes {
    public static class ItemAForItemB implements EntityVillager.ITradeList
    {
        private final EntityVillager.PriceInfo itemAInfo, itemBInfo;
        private Item itemA, itemB;
        private int metaA, metaB;

        public ItemAForItemB(Item item, EntityVillager.PriceInfo ItemAInfo, EntityVillager.PriceInfo ItemBInfo){
            this.itemAInfo = ItemAInfo; this.itemBInfo = ItemBInfo;
            this.itemA = item; this.itemB = Items.EMERALD;
            this.metaA = 0; this.metaB = 0;
        }

        public ItemAForItemB(Item item, int metaA, EntityVillager.PriceInfo ItemAInfo, EntityVillager.PriceInfo ItemBInfo){
            this.itemAInfo = ItemAInfo; this.itemBInfo = ItemBInfo;
            this.itemA = item; this.itemB = Items.EMERALD;
            this.metaA = metaA; this.metaB = 0;
        }

        public ItemAForItemB(Item itemA, EntityVillager.PriceInfo ItemAInfo, Item itemB, EntityVillager.PriceInfo ItemBInfo){
            this.itemAInfo = ItemAInfo; this.itemBInfo = ItemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = 0; this.metaB = 0;
        }

        public ItemAForItemB(Item itemA, int metaA, EntityVillager.PriceInfo ItemAInfo, Item itemB, EntityVillager.PriceInfo ItemBInfo){
            this.itemAInfo = ItemAInfo; this.itemBInfo = ItemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = metaA; this.metaB = 0;
        }

        public ItemAForItemB(Item itemA, int metaA, EntityVillager.PriceInfo ItemAInfo, Item itemB, int metaB, EntityVillager.PriceInfo ItemBInfo){
            this.itemAInfo = ItemAInfo; this.itemBInfo = ItemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = metaA; this.metaB = metaB;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int itemA_Amount = this.itemAInfo.getPrice(random), itemB_Amount = this.itemBInfo.getPrice(random);
            Item itemA = this.itemA, itemB = this.itemB;
            int metaA = this.metaA, metaB = this.metaB;
            recipeList.add(new MerchantRecipe(
                    new ItemStack(itemA, itemA_Amount, metaA),
                    new ItemStack(itemB, itemB_Amount, metaB)));
        }
    }

    public static class EmeraldForItem implements EntityVillager.ITradeList {
        private final EntityVillager.PriceInfo itemAInfo, itemBInfo;
        private Item itemA, itemB;
        private int metaA, metaB;

        public EmeraldForItem(Item item, EntityVillager.PriceInfo itemInfo){
            this.itemAInfo = itemInfo; this.itemBInfo = new EntityVillager.PriceInfo(1,1);
            this.itemA = item; this.itemB = Items.EMERALD;
            this.metaA = 0; this.metaB = 0;
        }

        public EmeraldForItem(Item item, int meta, EntityVillager.PriceInfo itemInfo){
            this.itemAInfo = itemInfo; this.itemBInfo = new EntityVillager.PriceInfo(1,1);
            this.itemA = item; this.itemB = Items.EMERALD;
            this.metaA = meta; this.metaB = 0;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int itemA_Amount = this.itemAInfo.getPrice(random), itemB_Amount = this.itemBInfo.getPrice(random);
            Item itemA = this.itemA, itemB = this.itemB;
            int metaA = this.metaA, metaB = this.metaB;
            recipeList.add(new MerchantRecipe(
                    new ItemStack(itemA, itemA_Amount, metaA),
                    new ItemStack(itemB, itemB_Amount, metaB)));
        }
    }

    public static class ItemForEmerald implements EntityVillager.ITradeList {
        private final EntityVillager.PriceInfo itemAInfo, itemBInfo;
        private Item itemA, itemB;
        private int metaA, metaB;

        public ItemForEmerald(Item item, EntityVillager.PriceInfo itemInfo){
            this.itemAInfo = itemInfo; this.itemBInfo = new EntityVillager.PriceInfo(1,1);
            this.itemA = Items.EMERALD; this.itemB = item;
            this.metaA = 0; this.metaB = 0;
        }

        public ItemForEmerald(Item item, int meta, EntityVillager.PriceInfo itemInfo){
            this.itemAInfo = itemInfo; this.itemBInfo = new EntityVillager.PriceInfo(1,1);
            this.itemA = Items.EMERALD; this.itemB = item;
            this.metaA = 0; this.metaB = meta;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int itemA_Amount = this.itemAInfo.getPrice(random), itemB_Amount = this.itemBInfo.getPrice(random);
            Item itemA = this.itemA, itemB = this.itemB;
            int metaA = this.metaA, metaB = this.metaB;
            recipeList.add(new MerchantRecipe(
                    new ItemStack(itemA, itemA_Amount, metaA),
                    new ItemStack(itemB, itemB_Amount, metaB)));
        }
    }

    public static class ItemAEmeraldForItemB implements EntityVillager.ITradeList {
        private final EntityVillager.PriceInfo itemAInfo, itemBInfo;
        private final EntityVillager.PriceInfo emeraldInfo;
        private Item itemA, itemB;
        private int metaA, metaB;

        public ItemAEmeraldForItemB(Item itemA, EntityVillager.PriceInfo itemAInfo, EntityVillager.PriceInfo emeraldInfo, Item itemB, EntityVillager.PriceInfo itemBInfo){
            this.itemAInfo = itemAInfo; this.itemBInfo = itemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = 0; this.metaB = 0;
            this.emeraldInfo = emeraldInfo;
        }

        public ItemAEmeraldForItemB(Item itemA, int metaA, EntityVillager.PriceInfo itemAInfo, EntityVillager.PriceInfo emeraldInfo, Item itemB, int metaB, EntityVillager.PriceInfo itemBInfo){
            this.itemAInfo = itemAInfo; this.itemBInfo = itemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = metaA; this.metaB = metaB;
            this.emeraldInfo = emeraldInfo;
        }

        public ItemAEmeraldForItemB(Item itemA, int metaA, EntityVillager.PriceInfo itemAInfo, EntityVillager.PriceInfo emeraldInfo, Item itemB, EntityVillager.PriceInfo itemBInfo){
            this.itemAInfo = itemAInfo; this.itemBInfo = itemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = metaA; this.metaB = 0;
            this.emeraldInfo = emeraldInfo;
        }

        public ItemAEmeraldForItemB(Item itemA, EntityVillager.PriceInfo itemAInfo, EntityVillager.PriceInfo emeraldInfo, Item itemB, int metaB, EntityVillager.PriceInfo itemBInfo){
            this.itemAInfo = itemAInfo; this.itemBInfo = itemBInfo;
            this.itemA = itemA; this.itemB = itemB;
            this.metaA = 0; this.metaB = metaB;
            this.emeraldInfo = emeraldInfo;
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int itemA_Amount = this.itemAInfo.getPrice(random), itemB_Amount = this.itemBInfo.getPrice(random);
            Item itemA = this.itemA, itemB = this.itemB;
            int metaA = this.metaA, metaB = this.metaB;
            int emeraldAmount = this.emeraldInfo.getPrice(random);
            recipeList.add(new MerchantRecipe(
                    new ItemStack(itemA, itemA_Amount, metaA),
                    new ItemStack(Items.EMERALD, emeraldAmount, 0),
                    new ItemStack(itemB, itemB_Amount, metaB)));
        }
    }
}
