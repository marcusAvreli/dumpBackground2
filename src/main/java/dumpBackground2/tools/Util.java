package dumpBackground2.tools;

import java.util.List;

public class Util {

	public static boolean isNotNullOrEmpty(String inString) {
		boolean notNullOrEmpty = false;
		if(null != inString && !inString.isEmpty()) {
			notNullOrEmpty=true;
		}
		return notNullOrEmpty;
	}
	public static <T>  boolean isNotNullOrEmpty(List<T> inList) {
		boolean notNullOrEmpty = false;
		if(null != inList && !inList.isEmpty()) {
			notNullOrEmpty=true;
		}
		return notNullOrEmpty;
	}
	public static <T> T getFirstElement(List<T> inList) {
		if(isNotNullOrEmpty(inList)) {
			return inList.get(0);
		}
		return null;
	}
}
