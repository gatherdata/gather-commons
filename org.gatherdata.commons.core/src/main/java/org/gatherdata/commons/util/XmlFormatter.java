package org.gatherdata.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlFormatter {

	private boolean trimming = true;

	public boolean isTrimming() {
		return trimming;
	}

	public void setTrimming(boolean trimming) {
		this.trimming = trimming;
	}
	
	public String format(String unformatted) {
		if (unformatted == null) {
			return null;
		}
		
		String formatted = unformatted;
		
		if (trimming) {
			formatted = trim(unformatted);
		}
		
		return formatted;
	}
	
	public String trim(String untrimmed) {
		if (untrimmed == null) {
			return null;
		}
		
		Pattern trimInitial = Pattern.compile(">(\\s*)");
		Matcher initialMatcher = trimInitial.matcher(untrimmed);
		String initialTrim = initialMatcher.replaceAll(">");
		
		Pattern trimTail = Pattern.compile("(\\s*)</");
		Matcher tailMatcher = trimTail.matcher(initialTrim);
		String tailTrim = tailMatcher.replaceAll("</");
		
		return tailTrim;
	}
	
}

