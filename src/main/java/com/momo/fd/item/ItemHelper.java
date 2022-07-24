package com.momo.fd.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemHelper {
    public static boolean compareStacks(ItemStack s1, ItemStack s2)
    {
        return s1.getItem() == s2.getItem() && (s1.getMetadata() == 32767 || s1.getMetadata() == s2.getMetadata());
    }

    public static String getMatchingItemKey(ArrayList<String> items, ItemStack item)
    {
        String name = getItemStringFromItemStack(item, true);

        // literal match
        if (items.contains(name)) return name;

        if (items.contains(name + "[*]")) return name + "[*]";

        String meta = "";
        if (name.contains("[")) {
            meta = name.substring(name.indexOf('[') + 1, name.indexOf(']'));
            name = name.substring(0, name.indexOf('['));
        }

        // matches after meta is stripped?
        if (items.contains(name)) return name;

        if (!meta.isEmpty() && items.contains(name + "[*]")) {
            return name + "[*]";
        }

        return "";
    }

    public static String getItemStringFromItemStack(ItemStack item, boolean withMeta)
    {
        String itemName = Objects.requireNonNull(item.getItem().getRegistryName()).toString();

        int meta = withMeta ? item.getItemDamage() : '*';
        itemName += "[" + meta + "]";

        return itemName;
    }

    public static ItemStack getItemStackFromItemString(String name)
    {
        return getItemStackFromItemString(name, 0);
    }

    public static ItemStack getItemStackFromItemString(String name, int defaultMeta)
    {
        ItemStack stack = null;
        String meta = "";
        if (name.contains("[")) {
            meta = name.substring(name.indexOf('[') + 1, name.indexOf(']'));
            name = name.substring(0, name.indexOf('['));
        }

        // parse meta
        Item item = Item.getByNameOrId(name);
        if (item != null) {
            if (meta.equals("*") || meta.isEmpty()) {
                stack = new ItemStack(item, 1, defaultMeta);
            } else {
                stack = new ItemStack(item, 1, Integer.parseInt(meta));
            }
        }

        return stack;
    }

    public static List<ItemStack> getItemStacksFromItemString(String name)
    {
        return getItemStacksFromItemString(name, 0);
    }

    public static List<ItemStack> getItemStacksFromItemString(String name, int defaultMeta)
    {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        String meta = "";
        if (name.contains("[")) {
            meta = name.substring(name.indexOf('[') + 1, name.indexOf(']'));
            name = name.substring(0, name.indexOf('['));
        }

        // parse meta
        Item item = Item.getByNameOrId(name);
        if (item != null) {
            if (meta.equals("*") || meta.isEmpty()) {
                if (item.getHasSubtypes()) {
                    NonNullList<ItemStack> subItems = NonNullList.create();
                    item.getSubItems(CreativeTabs.SEARCH, subItems);
                    stacks.addAll(subItems);
                } else {
                    stacks.add(new ItemStack(item, 1, defaultMeta));
                }
            } else {
                stacks.add(new ItemStack(item, 1, Integer.parseInt(meta)));
            }
        }

        return stacks;
    }
}
