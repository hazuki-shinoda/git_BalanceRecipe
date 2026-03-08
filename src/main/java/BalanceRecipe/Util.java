package BalanceRecipe;

public class Util {
    public static String replaceEscapeChar(String inputText) {
        if (inputText == null) return null;
        return inputText.replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                        .replace("\"", "&quot;")
                        .replace("'", "&#039;");
    }
}