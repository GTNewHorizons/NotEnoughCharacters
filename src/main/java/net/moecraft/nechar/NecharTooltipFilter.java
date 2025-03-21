package net.moecraft.nechar;

import static net.moecraft.nechar.NecharUtils.contain;
import static net.vfyjxf.nechar.NechConfig.enableVoltageSpecialSearchTooltips;

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
        return contain(getSearchTooltip(itemStack), this.searchText, enableVoltageSpecialSearchTooltips)
            || super.matches(itemStack);
    }

}
