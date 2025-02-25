package net.moecraft.nechar;

import static net.moecraft.nechar.NotEnoughCharacters.CONTEXT;

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
    
    private String deleteComma(String str){
        return str.replaceAll(",(?=[0,9])","");
    }
    
    @Override
    public boolean matches(ItemStack itemStack) {
        String displayName = EnumChatFormatting.getTextWithoutFormattingCodes(itemStack.getDisplayName());

        if (!displayName.isEmpty() && CONTEXT.contains(deleteComma(displayName), deleteComma(this.searchText))) {
            return true;
        }

        if (itemStack.hasDisplayName()) {
            displayName = EnumChatFormatting.getTextWithoutFormattingCodes(
                itemStack.getItem()
                    .getItemStackDisplayName(itemStack));

            if (displayName.isEmpty() && CONTEXT.contains(deleteComma(displayName), deleteComma(this.searchText))) {
                return true;
            }
        }

        return super.matches(itemStack);
    }

}
