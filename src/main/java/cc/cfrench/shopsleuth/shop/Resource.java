package cc.cfrench.shopsleuth.shop;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

enum Resource {
    IRON("Iron",                 true, false, false, false, true),
    GOLD("Gold",                 true, false, false, false, true),
    EMERALD("Emerald",           true, false, false, false, true),
    EMERALD_SPECIAL("Emerald",   true, false, true,  false, true),
    EMERALDS("Emeralds",         true, false, false, false, true),
    EMERALDS_SPECIAL("Emeralds", true, false, true,  false, true),
    DIAMONDS("Diamonds",         true, null,  false, null,  true);

    private static class LazyHolder {
        private static final Map<NBTBase, Resource> RESOURCE_MAP = new HashMap<>();
    }

    private final NBTBase tag;

    Resource(final String name,
             final Boolean overrideMeta,
             final Boolean unbreakable,
             final boolean hideFlags,
             final Boolean extraAttributes,
             final boolean attributeModifiers) {
        final NBTTagCompound newTag = new NBTTagCompound();

        if (overrideMeta != null) {
            newTag.setBoolean("overrideMeta", overrideMeta);
        }
        if (unbreakable != null) {
            newTag.setBoolean("Unbreakable", unbreakable);
        }
        if (hideFlags) {
            newTag.setInteger("HideFlags", 254);
        }
        if (extraAttributes != null) {
            final NBTTagCompound extraAttributesTag = new NBTTagCompound();
            newTag.setTag("ExtraAttributes", extraAttributesTag);
            if (extraAttributes) {
                extraAttributesTag.setBoolean("TOWER_ITEM", true);
            }
        }
        if (attributeModifiers) {
            newTag.setTag("AttributeModifiers", new NBTTagList());
        }

        final NBTTagCompound displayTag = new NBTTagCompound();
        newTag.setTag("display", displayTag);
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

        this.tag = newTag;
        LazyHolder.RESOURCE_MAP.put(this.tag, this);
    }

    public static Optional<Resource> fromTag(final NBTBase tag) {
        return Optional.ofNullable(LazyHolder.RESOURCE_MAP.get(tag));
    }
}
