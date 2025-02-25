package net.sst03.nechar;

import static net.moecraft.nechar.NotEnoughCharacters.CONTEXT;

public class NecharUtils {

    private static String deleteComma(String str) {
        return str.replaceAll("(?<=[0-9]),(?=[0-9])", "");
    }

    private static String deleteExtraChars(String str) {
        return str.replaceAll("\ud872", "")
            .replaceAll("\ud86d", "");
    }

    public static boolean contain(String searchText, String sourseText) {

        if (!(searchText.contains("\ud872") || searchText.contains("\ud86d"))) {
            searchText = deleteExtraChars(searchText);
            sourseText = deleteExtraChars(sourseText);
        }

        if (!searchText.contains(",")) {
            searchText = deleteComma(searchText);
            sourseText = deleteComma(sourseText);
        }

        return CONTEXT.contains(sourseText, searchText);
    }
}