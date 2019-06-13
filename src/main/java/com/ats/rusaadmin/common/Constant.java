package com.ats.rusaadmin.common;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class Constant {

	//public static final String url="http://ats.aaryatechindia.in:15063/rusawebapi/";
	public static final String url = "http://localhost:8094/";

	// Local path------------------------
	/*
	 * public static final int gallryImage = 1; public static final String
	 * lgogImageURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/logo/"; public
	 * static final String bannerImageURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/banenr/"; public
	 * static final String uploadDocURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/pdf/"; public
	 * static final String gallryImageURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/gallery/"; public
	 * static final String otherDocURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/"; public
	 * static final String cmsPdf =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/pdf/"; public
	 * static final String userProfileURL =
	 * "/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/userprofile/";
	 * public static String[] values = { "jpg", "jpeg", "gif", "png", "JPG", "JPEG",
	 * "GIF", "PNG" }; public static String[] DocValues = { "txt", "doc", "pdf",
	 * "xls" }; public static String[] DocImgValues = { "txt", "doc", "pdf", "xls",
	 * "jpg", "jpeg", "gif", "png" }; public static int mod = 0; public static int
	 * subMod = 0;
	 * 
	 * public static final String logoName = "Russa-Logo"; public static final
	 * String getLgogImageURL = "http://192.168.2.17:8080/media/logo/"; public
	 * static final String getBannerImageURL =
	 * "http://192.168.2.17:8080/media/banenr/"; public static final String
	 * getUploadDocURL = "http://192.168.2.17:8080/media/pdf/"; public static final
	 * String getGallryImageURL = "http://192.168.2.17:8080/media/gallery/"; public
	 * static final String getCmsPdf = "http://192.168.2.17:8080/media/pdf/"; public
	 * static final String getOtherDocURL = "http://192.168.2.17:8080/media/other/";
	 * public static final String getUserProfileURL =
	 * "http://192.168.2.17:8080/media/userprofile/"; // public static final String
	 * siteAdminUrl = // "http://192.168.10.226:8080/RusaAdmin/"; public static
	 * final String siteAdminUrl = "http://localhost:8081/rusaadmin/";
	 */

	public static final int gallryImage = 1;
	public static final String lgogImageURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/logo/";
	public static final String bannerImageURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/banenr/";
	public static final String uploadDocURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/pdf/";
	public static final String gallryImageURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/gallery/";
	public static final String otherDocURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/other/";
	public static final String cmsPdf = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/pdf/";
	public static final String userProfileURL = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/media/userprofile/";
	public static String[] values = { "jpg", "jpeg", "gif", "png" };
	public static String[] DocValues = { "txt", "doc", "pdf", "xls", ".ppt",".pptx" };
	public static String[] DocImgValues = { "txt", "doc", "pdf", "xls", "jpg", "jpeg", "gif", "png" };
	public static int mod = 0;
	public static int subMod = 0;
	public static final String logoName = "Russa-Logo";
	public static final String getLgogImageURL = "http://ats.aaryatechindia.in:15063/media/logo/";
	public static final String getBannerImageURL = "http://ats.aaryatechindia.in:15063/media/banenr/";
	public static final String getUploadDocURL = "http://ats.aaryatechindia.in:15063/media/pdf/";
	public static final String getGallryImageURL = "http://ats.aaryatechindia.in:15063/media/gallery/";
	public static final String getCmsPdf = "http://ats.aaryatechindia.in:15063/media/pdf/";
	public static final String getOtherDocURL = "http://ats.aaryatechindia.in:15063/media/other/";
	public static final String getUserProfileURL = "http://ats.aaryatechindia.in:15063/media/userprofile/";
	public static final String siteAdminUrl = "http://ats.aaryatechindia.in:15063/ats/";
	public static final String getUserDocURL = "http://192.168.2.17:8080/media/userdocument/";
	public static Font headFontData = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
	public static Font tableHeaderFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
	public static final BaseColor tableHeaderFontBaseColor = BaseColor.WHITE;
	public static final BaseColor baseColorTableHeader = BaseColor.BLUE;

	public static final Font reportNameFont = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.UNDERLINE, BaseColor.BLACK);

	public static float marginLeft = 50;
	public static float marginRight = 45;
	public static float marginTop = 50;
	public static float marginBottom = 50;
	public static final String REPORT_SAVE = "/home/tomcataaryatechi/ats.aaryatechindia.in/tomcat-8.0.18/webapps/ats/rusa_report2019.pdf";
	 //public static final String REPORT_SAVE = "/home/lenovo/Documents/rusa_report2019.pdf";

}
