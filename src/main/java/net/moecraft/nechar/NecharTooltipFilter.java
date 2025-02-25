package net.moecraft.nechar;

import static net.sst03.nechar.NecharUtils.contain;
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
        return contain(getSearchTooltip(itemStack), this.searchText)
            || super.matches(itemStack);
    }

}
