package net.moecraft.nechar;

import net.minecraft.util.EnumChatFormatting;

import codechicken.nei.SearchTokenParser.ISearchParserProvider;
import codechicken.nei.SearchTokenParser.SearchMode;
import codechicken.nei.api.ItemFilter;

public class NecharSearchProvider implements ISearchParserProvider {

    @Override
    public ItemFilter getFilter(String searchText) {
        return new NecharSearchFilter(searchText);
    }

    @Override
    public char getPrefix() {
        return '*';
    }

    @Override
    public EnumChatFormatting getHighlightedColor() {
        return EnumChatFormatting.BLUE;
    }

    @Override
    public SearchMode getSearchMode() {
        return SearchMode.ALWAYS;
    }
}
