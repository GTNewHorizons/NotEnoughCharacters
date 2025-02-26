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
            str = str.replaceAll("\ud872\udf3b","\ue900") // 钅卢
                .replaceAll("\ud872\udf4a","\ue901") // 钅杜
                .replaceAll("\ud872\udf73","\ue902") // 钅喜
                .replaceAll("\ud872\udf5b","\ue903") // 钅波
                .replaceAll("\ud872\udf76","\ue904") // 钅黑
                .replaceAll("\ud872\udf2d","\ue907"); // 钅仑
        }

        if (str.contains("\ud86d")) {
            str = str.replaceAll("\ud86d\udffc","\ue906") // 钅达
                .replaceAll("\ud86d\udce7","\ue90a") // 钅夫
                .replaceAll("\ud86d\udff7","\ue90c"); // 钅仑
        }

        return str;
    }

    public static boolean contain(String sourseText, String searchText) {

        searchText = replaceExtraChars(searchText);
        sourseText = replaceExtraChars(sourseText);

        if (EnableIgnoreComma && !searchText.contains(",")) {
            searchText = deleteComma(searchText);
            sourseText = deleteComma(sourseText);
        }

        if (CONTEXT.contains(sourseText, searchText)) {
            return true;
        }

        // may be very slow, only try to find 1 voltage level
        if (!EnableVoltageSpecialSearch) {
            return false;
        }

        searchText = searchText.toLowerCase();

        if (searchText.contains("max")) {
            sourseText = sourseText.toLowerCase();

            if (!sourseText.contains("max")) { //check if "max" can be found
                return false;
            }

            String[] sList = searchText.split("max"); // check if texts between "max" can be found

            if (sList.length == 2 && !CONTEXT.contains(sourseText, sList[1])) {
                return false;
            }

            return CONTEXT.contains(sourseText, sList[0]);

        } else if (searchText.contains("zpm")) {
            sourseText = sourseText.toLowerCase();

            if (!sourseText.contains("zpm")) { //check if "zpm" can be found
                return false;
            }

            String[] sList = searchText.split("zpm"); // check if texts between "zpm" can be found

            if (sList.length == 2 && !CONTEXT.contains(sourseText, sList[1])) {
                return false;
            }

            return CONTEXT.contains(sourseText, sList[0]);

        } else if (searchText.contains("v")) {
            sourseText = sourseText.toLowerCase();

            if (!sourseText.contains("v")) {
                return false;
            }

            int i = 1;// voltage name is like "??v", not "v?"
            final int l = searchText.length;

            //try to find a voltage name
            while (i < l){
                int j = searchText.indexOf('v',i);

                if (j == -1) { // cannot find any voltage name
                    return false;
                }

                i = j + 1;
                switch(searchText.charAt(j - 1)) { // ulv/umv/uhv/uiv/uev are basicly same
                    case 'l' :

                        if (!(sourseText.contains("lv") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'u' && sourseText.contains("ulv") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));

                    case 'm' :

                        if (!(sourseText.contains("mv") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'u' && sourseText.contains("umv") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));

                    case 'h' :

                        if (!(sourseText.contains("hv") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'u' && sourseText.contains("uhv") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));

                    case 'e' :

                        if (!(sourseText.contains("ev") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'u' && sourseText.contains("uev") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));

                    case 'i' :

                        if (!(sourseText.contains("iv") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'u' && sourseText.contains("uiv") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));

                    case 'u' :

                        if (!(sourseText.contains("uv") 
                            || CONTEXT.contains(sourseText, searchText.substring(j + 1)))) {
                            return false;
                        }

                        if (searchText.charAt(j - 2) == 'l' && sourseText.contains("luv") 
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2))) {
                            return true;
                        }

                        return CONTEXT.contains(sourseText, searchText.substring(0,j - 1));
                        
                    case 'x' :

                        if (!searchText.charAt(j - 2) == 'u') {
                            break;
                        }

                        return sourseText.contains("uxv") && CONTEXT.contains(sourseText, searchText.substring(j + 1))
                            && CONTEXT.contains(sourseText, searchText.substring(0,j - 2));

                    default :
                        break;
                }
            }
        }

        return false;
 
    }
        //return CONTEXT.contains(sourseText, searchText);

}
