package cc.cfrench.shopsleuth.shop;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;

enum Resource {
    IRON("Iron",                 true, false, false, true,  true),
    GOLD("Gold",                 true, false, false, true,  true),
    EMERALD("Emerald",           true, false, false, true,  true),
    EMERALD_SPECIAL("Emerald",   true, false, true,  true,  true),
    EMERALDS("Emeralds",         true, false, false, true,  true),
    EMERALDS_SPECIAL("Emeralds", true, false, true,  true,  true),
    DIAMONDS("Diamonds",         true, null,  false, false, true);

    private final NBTTagCompound tag = new NBTTagCompound();

    Resource(final String name,
             final Boolean overrideMeta,
             final Boolean unbreakable,
             final boolean hideFlags,
             final boolean extraAttributes,
             final boolean attributeModifiers) {
        if (overrideMeta != null) {
            this.tag.setBoolean("overrideMeta", overrideMeta);
        }
        if (unbreakable != null) {
            this.tag.setBoolean("Unbreakable", unbreakable);
        }
        if (hideFlags) {
            this.tag.setInteger("HideFlags", 254);
        }
        if (extraAttributes) {
            this.tag.setTag("ExtraAttributes", new NBTTagCompound());
        }
        if (attributeModifiers) {
            this.tag.setTag("AttributeModifiers", new NBTTagList());
        }

        final NBTTagCompound displayTag = new NBTTagCompound();
        this.tag.setTag("display", displayTag);
        final NBTTagList loreTag = new NBTTagList();
        displayTag.setTag("Lore", loreTag);

        if (!"Diamonds".equals(name)) {
            loreTag.appendTag(new NBTTagString(EnumChatFormatting.GRAY + "Cost: " + EnumChatFormatting.ITALIC + "??"));
            loreTag.appendTag(new NBTTagString());
            loreTag.appendTag(new NBTTagString(EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + "????"));
            loreTag.appendTag(new NBTTagString());
            loreTag.appendTag(new NBTTagString(EnumChatFormatting.RED + "You don't have enough " + name + "!"));
        } else {
            loreTag.appendTag(new NBTTagString(String.valueOf(EnumChatFormatting.GRAY)));
            loreTag.appendTag(new NBTTagString(EnumChatFormatting.GRAY + "" + EnumChatFormatting.GRAY + "Cost: " + EnumChatFormatting.ITALIC + "??"));
            loreTag.appendTag(new NBTTagString());
            loreTag.appendTag(new NBTTagString(EnumChatFormatting.RED + "You don't have enough " + name + "!"));
        }

        displayTag.setString("Name", EnumChatFormatting.RED + "Unknown");
    }

    public NBTTagCompound getTag() {
        return this.tag;
    }
}
