package calculatorUpgradeByYoungyooon.utils;

public class Util {
	public static String removeDigit(String removedSpaceInput, String regex) {
		return removedSpaceInput.replaceAll(regex, "");
	}
}
