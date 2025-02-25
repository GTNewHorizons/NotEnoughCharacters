package net.moecraft.nechar;

import static net.sst03.nechar.NecharUtils.contain;

import java.util.regex.Pattern;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import codechicken.nei.ItemList.PatternItemFilter;

public class NecharDisplayFilter extends PatternItemFilter {

    private final String searchText;

    public NecharDisplayFilter(String searchText, Pattern pattern) {
        super(pattern);
        this.searchText = searchText;
    }

    @Override
    public boolean matches(ItemStack itemStack) {
        String displayName = EnumChatFormatting.getTextWithoutFormattingCodes(itemStack.getDisplayName());

        if (!displayName.isEmpty() && contain(displayName, this.searchText)) {
            return true;
        }

        if (itemStack.hasDisplayName()) {
            displayName = EnumChatFormatting.getTextWithoutFormattingCodes(
                itemStack.getItem()
                    .getItemStackDisplayName(itemStack));

            if (!displayName.isEmpty() && contain(displayName, this.searchText)) {
                return true;
            }
        }

        return super.matches(itemStack);
    }

}