package com.ats.rusaadmin;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssEscapeUtils {
	//a) in all input type text and textarea withoput ckeditor or any html editor in both project
	public  String jsoupParse(String str) {
	return Jsoup.parse(str).text();
	}

	//b) in all  textarea with ckeditor or any html editor in both project
	public  String jsoupParseClean(String str) {
	return Jsoup.clean(str, Whitelist.relaxed());
	}
	
	/*public static String jsoupParse(String str) {
		return Jsoup.parse(str).text();
		}

		public static String escapeHtml4(String html) {
		return StringEscapeUtils.escapeHtml4(html);
		}

		public static String unescapeHtml4(String html) {
		return StringEscapeUtils.unescapeHtml4(html);
		}*/
}
