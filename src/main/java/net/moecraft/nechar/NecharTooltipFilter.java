package net.moecraft.nechar;

import static net.moecraft.nechar.NotEnoughCharacters.CONTEXT;

import java.util.regex.Pattern;

import net.minecraft.item.ItemStack;

import codechicken.nei.search.TooltipFilter;

public class NecharTooltipFilter extends TooltipFilter {

    private final String searchText;

    public NecharTooltipFilter(String searchText, Pattern pattern) {
        super(pattern);
        this.searchText = searchText;
    }

    @Override
    public boolean matches(ItemStack itemStack) {
        return CONTEXT.contains(getSearchTooltip(itemStack).replaceAll(",(?=[0,9])",""), this.searchText.replaceAll(",(?=[0,9])","")) || super.matches(itemStack);
    }

}
