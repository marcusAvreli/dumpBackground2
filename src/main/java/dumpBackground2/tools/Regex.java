package dumpBackground2.tools;

public class Regex {

	private static final String parenthesesPattern = "\\(([^)]+)\\)";
	private static final String curlyBracesPattern = "\\{([^}]+)\\}";

	public static String getParenthesespattern() {
		return parenthesesPattern;
	}

	public static String getCurlybracespattern() {
		return curlyBracesPattern;
	}

}
