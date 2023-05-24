package com.momo.fd.blocks.blockVariant;

import net.minecraft.util.IStringSerializable;

public enum EnumVariants implements IStringSerializable {
    Block0(0, "0"),
    Block1(1, "1"),
    Block2(2, "2"),
    Block3(3, "3"),
    Block4(4, "4"),
    Block5(5, "5"),
    Block6(6, "6"),
    Block7(7, "7"),
    Block8(8, "8"),
    Block9(9, "9"),
    Block10(10, "10"),
    Block11(11, "11"),
    Block12(12, "12"),
    Block13(13, "13"),
    Block14(14, "14"),
    Block15(15, "15");


    static final EnumVariants[] META_LOOKUP = new EnumVariants[values().length];
    final int meta;
    final String name, unlocalizedName;

    private EnumVariants(int meta, String name) {
        this(meta, name, name);
    }

    private EnumVariants(int meta, String name, String unlocalizedName) {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getMeta() {
        return meta;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public String toString() {
        return name;
    }

    public static EnumVariants byMetadata(int meta) {
        return META_LOOKUP[meta];
    }

    static {
        for (EnumVariants enumType : values()) {
            META_LOOKUP[enumType.getMeta()] = enumType;
        }
    }
}
