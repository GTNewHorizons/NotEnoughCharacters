package net.sst03.nechar;

import static net.moecraft.nechar.NotEnoughCharacters.CONTEXT;
import static net.vfyjxf.nechar.NechConfig.EnableIgnoreComma;
import static net.vfyjxf.nechar.NechConfig.EnableVoltageSpecialSearch;

public class NecharUtils {

    private static String deleteComma(String str) {
        return str.replaceAll("(?<=[0-9]),(?=[0-9])", "");
    }

    private static String replaceExtraChars(String str) {

        if (str.contains("\ud872")) {
            str = str.replaceAll("\ud872\udf3b", "\ue900") // 钅卢
                .replaceAll("\ud872\udf4a", "\ue901") // 钅杜
                .replaceAll("\ud872\udf73", "\ue902") // 钅喜
                .replaceAll("\ud872\udf5b", "\ue903") // 钅波
                .replaceAll("\ud872\udf76", "\ue904") // 钅黑
                .replaceAll("\ud872\udf2d", "\ue907"); // 钅仑
        }

        if (str.contains("\ud86d")) {
            str = str.replaceAll("\ud86d\udffc", "\ue906") // 钅达
                .replaceAll("\ud86d\udce7", "\ue90a") // 钅夫
                .replaceAll("\ud86d\udff7", "\ue90c"); // 钅仑
        }

        return str;
    }

    private static boolean containWithVoltage(String voltage, String sourseText, String searchText) {
        if (!searchText.contains(voltage)) {
            return false;
        }
        if (!sourseText.contains(voltage)) {
            return false;
        } // check if [voltage] can be found
        String[] sList = searchText.split(voltage, 2); // check if texts between "max" can be found
        if (sList.length == 2 && !CONTEXT.contains(sourseText, sList[1])) {
            return false;
        }
        return CONTEXT.contains(sourseText, sList[0]);
    }

    private static String[] voltageListWithLetterV = { "ulv", "lv", "mv", "hv", "ev", "iv", "luv", "uv", "uhv", "uev",
        "uiv", "umv", "uxv" };

    public static boolean contain(String sourseText, String searchText, Boolean enableSpecialSearch) {

        sourseText = replaceExtraChars(sourseText);
        searchText = replaceExtraChars(searchText);

        if (EnableIgnoreComma && !searchText.contains(",")) {
            sourseText = deleteComma(sourseText);
            searchText = deleteComma(searchText);
        }

        if (CONTEXT.contains(sourseText, searchText)) {
            return true;
        }

        // may be very slow, only try to find 1 voltage level
        if (!(EnableVoltageSpecialSearch || enableSpecialSearch)) {
            return false;
        }

        sourseText = sourseText.toLowerCase();
        searchText = searchText.toLowerCase();

        if (containWithVoltage("zpm", sourseText, searchText) || containWithVoltage("max", sourseText, searchText)) {
            return true;
        }
        if (!(sourseText.contains('v') || searchText.contains('v'))) {
            return false;
        }
        for (String name : voltageListWithLetterV) {
            if (containWithVoltage(name, sourseText, searchText)) {
                return true;
            }
        }
        return false;
    }
    // return CONTEXT.contains(sourseText, searchText);

}
