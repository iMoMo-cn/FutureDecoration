package com.momo.fd.util;

import com.momo.fd.item.skills.ItemSkillBase;
import com.momo.fd.util.NBTStrDef.IDLNBTDef;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.momo.fd.util.CommonDef.INT_AS_FLOAT;
import static com.momo.fd.util.NBTStrDef.IDLNBTDef.*;
import static com.momo.fd.util.NBTStrDef.IDLNBTUtil.*;

public class IDLSkillNBT {
    public static final String SUCCESS_DESC_KEY = ".on_sucess";
    public static final String IN_CD_DESC_KEY = ".on_cooldown";
    public int level;
    public int range_boost = 0;
    public int duration_boost = 0;
    public int[] gua_boost = new int[8];

//    private final NBTTagCompound basic;

//    public IDLSkillNBT()
//    {
//        level = 1;
//        range_boost = 0;
//        duration_boost = 0;
//
//        basic = new NBTTagCompound();
//    }
//
//    public IDLSkillNBT(NBTTagCompound srcNBT)
//    {
//        readFromBasic(srcNBT);
//        basic = srcNBT;
//    }
//
//    public void readFromBasic(NBTTagCompound tag)
//    {
//        if (tag != null)
//        {
//            level = tag.getInteger(IDLNBTDef.LEVEL_TAG);
//            range_boost = tag.getInteger(IDLNBTDef.RANGE_BOOST);
//            duration_boost = tag.getInteger(IDLNBTDef.DURA_BOOST);
//        }
//    }

//    public void writeToBasic(NBTTagCompound tag)
//    {
//        if (tag == null)
//        {
//            tag = new NBTTagCompound();
//        }
//
//        tag.setInteger(IDLNBTDef.LEVEL_TAG, level);
//        tag.setInteger(IDLNBTDef.RANGE_BOOST, range_boost);
//        tag.setInteger(IDLNBTDef.DURA_BOOST, duration_boost);
//    }
//
//    public NBTTagCompound getBasic()
//    {
//        NBTTagCompound tag = basic.copy();
//        writeToBasic(tag);
//
//        return tag;
//    }
//
//    public void save()
//    {
//        writeToBasic(basic);
//    }
    //----------------------
    //Integer
    @SideOnly(Side.CLIENT)
    public static void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag,
                                       boolean shiftToShowDesc, boolean showGuaSocketDesc, boolean use_flavor, String mainDescOrFlavor) {

        boolean shiftPressed = !shiftToShowDesc || CommonFunctions.isShiftPressed();
        if (shiftPressed)
        {
            if (!mainDescOrFlavor.isEmpty())
            {
                tooltip.add(mainDescOrFlavor);
            }
        }
        else {
            if (use_flavor)
            {
                tooltip.add(TextFormatting.ITALIC +  mainDescOrFlavor);
            }
        }
    }

    public static int getLevel(ItemStack stack)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return 0;
        }
        ItemSkillBase skillBase = (ItemSkillBase) stack.getItem();

        int level = GetInt(stack, LEVEL_TAG);

        if (level <= 0)
        {
            return 1;
        }

        int lvMax = skillBase.GetLevelMax(stack);

        if (level > lvMax)
        {
            return lvMax;
        }

        return level;
    }

    public static void SetLevel(ItemStack stack, int count)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return;
        }
        ItemSkillBase skillBase = (ItemSkillBase) stack.getItem();

        if (count <= skillBase.GetLevelMax(stack)) {
            SetInt(stack, LEVEL_TAG, count);
        }
    }

    public static boolean IsCasting(ItemStack stack)
    {
        return GetBoolean(stack, IDLNBTDef.IS_CASTING);
    }

    public static void SetCasting(ItemStack stack, boolean bool)
    {
        SetBoolean(stack, IDLNBTDef.IS_CASTING, bool);
    }

    public static void SetDura(ItemStack stack, float count)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return;
        }
        ItemSkillBase skillBase = (ItemSkillBase) stack.getItem();

        SetInt(stack, CUR_TIME_LEFT, (int) (count * INT_AS_FLOAT));
    }

    public static void SetCharge(ItemStack stack, float count)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return;
        }
        ItemSkillBase skillBase = (ItemSkillBase) stack.getItem();

        SetInt(stack, CUR_CHARGE, (int) (count * INT_AS_FLOAT));
    }

    public static float GetCharge(ItemStack stack)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return 0;
        }

        int charge = GetInt(stack, CUR_CHARGE);

        return (float)charge / INT_AS_FLOAT;
    }

    public static float GetDura(ItemStack stack)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return 0;
        }
        int dura = GetInt(stack, CUR_TIME_LEFT);

        return (float)dura / INT_AS_FLOAT;
    }

    public static void AddLevelByCount(ItemStack stack, int count)
    {
        if (!(stack.getItem() instanceof ItemSkillBase)) {
            return;
        }
        ItemSkillBase skillBase = (ItemSkillBase) stack.getItem();
        int lvMax = skillBase.GetLevelMax(stack);
        int anticipatedCount = count + getLevel(stack);
        if (anticipatedCount <= lvMax ) {
            SetInt(stack, LEVEL_TAG, anticipatedCount);
        }
        else {
            SetInt(stack, LEVEL_TAG, lvMax);
        }
    }
}
