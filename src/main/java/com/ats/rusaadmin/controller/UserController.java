package com.ats.rusaadmin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Commons;
import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.EmailUtility;
import com.ats.rusaadmin.model.EventCountDetails;
import com.ats.rusaadmin.model.EventDetail;
import com.ats.rusaadmin.model.EventDetails;
import com.ats.rusaadmin.model.EventRegistration;
import com.ats.rusaadmin.model.EventView;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.NewsDetails;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.Registration;
import com.ats.rusaadmin.model.RegistrationUserDetail;
import com.ats.rusaadmin.model.User;
import com.ats.rusaadmin.util.ItextPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@Scope("session")
public class UserController {

	RestTemplate rest = new RestTemplate();

	static String senderEmail = "akshaykasar72@gmail.com";
	static String senderPassword = "mh151772@123";
	static String mailsubject = " RUSA Login Credentials ";
	static String mailsubjectApprove = " About Event Shedule";
	RegistrationUserDetail editUser = new RegistrationUserDetail();
	EventRegistration editEvent = new EventRegistration();
	// --------------------------------------Register User
	// Mapping-----------------------------------------------------------//\

	List<Registration> getUser = new ArrayList<Registration>();
	@RequestMapping(value = "/activeUserList", method = RequestMethod.GET)
	public ModelAndView activeUserList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("activateUser");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			Registration[] userList = rest.getForObject(Constant.url + "/getAllRegUserList", Registration[].class);
			getUser = new ArrayList<Registration>(Arrays.asList(userList));
			model.addObject("regList", getUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/activeUserListPdf", method = RequestMethod.GET)
	public void activeUserListPdf(HttpServletRequest request, HttpServletResponse response) {

	 
		try {

			Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
			// 50, 45, 50, 60
			document.setMargins(Constant.marginLeft, Constant.marginRight, Constant.marginTop, Constant.marginBottom);
			document.setMarginMirroring(false);
			/*document.left(100f);
			document.top(150f);*/
			
			String FILE_PATH = Constant.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			 
			 
			ItextPageEvent event = new ItextPageEvent(header, title, "", "Event List");

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(9);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.0f, 6.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});

				Font headFontData = Constant.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constant.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constant.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(Constant.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Type ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Email", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Mobile No", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
 

				hcell = new PdfPCell(new Phrase("Reg via", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("SMS Verified status", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Email Verified status", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Status", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				 

				int index = 0;

				for (int i = 0; i < getUser.size(); i++) {
					// System.err.println("I " + i);
				 

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + getUser.get(i).getName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
					cell.setPadding(5); 
					table.addCell(cell);
					
					if(getUser.get(i).getUserType()==2) {
						cell = new PdfPCell(new Phrase("" + "Institute", headFontData));
					}else if(getUser.get(i).getUserType()==3){
						cell = new PdfPCell(new Phrase("" + "University", headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "Individual", headFontData));
					} 
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5); 
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + getUser.get(i).getEmails(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + getUser.get(i).getMobileNumber(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + getUser.get(i).getRegisterVia(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
 
					if(getUser.get(i).getSmsVerified()==1) {
						cell = new PdfPCell(new Phrase("" + "Verified", headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "Not Verified", headFontData));
					}  
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					if(getUser.get(i).getEmailVerified()==1) {
						cell = new PdfPCell(new Phrase("" + "Verified", headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "Not Verified", headFontData));
					}  
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					if(getUser.get(i).getIsActive()==0) {
						cell = new PdfPCell(new Phrase("" + "New User", headFontData)); 
					}else if(getUser.get(i).getIsActive()==1){
						cell = new PdfPCell(new Phrase("" + "Activate", headFontData)); 
					}else{
						cell = new PdfPCell(new Phrase("" + "Deactivate", headFontData)); 
					}
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
					cell.setPadding(5);
					table.addCell(cell);
					 

				}

				document.open();
				Font reportNameFont = Constant.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph("User List", reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				 
				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				 
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}

	@RequestMapping(value = "/editActivateUser/{regId}", method = RequestMethod.GET)
	public ModelAndView editActivateUser(@PathVariable("regId") int regId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("userActivationForm");
		try {
			System.out.println("id" + regId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", regId);
			editUser = rest.postForObject(Constant.url + "/getRegUserDetailbyRegId", map, RegistrationUserDetail.class);
			System.out.println("User: " + editUser.toString());

			model.addObject("editUser", editUser);
			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public String activateUser(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {

		HttpSession session = request.getSession();
		try {
			Info info = null;
			Info info1 = null;

			User UserDetail = (User) session.getAttribute("UserDetail");

			int regId = Integer.parseInt(request.getParameter("regId"));
			String alternateEmail = request.getParameter("email");
			String name = request.getParameter("name");
			 
			int type = Integer.parseInt(request.getParameter("type"));
			//String email = request.getParameter("userEmail");
			String phone = request.getParameter("phone");
			String aishe = request.getParameter("aishe");
			String collegeN = request.getParameter("collegeN");
			String uniName = request.getParameter("uniName");
			String designation = request.getParameter("designation");
			String userPass = request.getParameter("userPass");

			String deptN = request.getParameter("deptN");
			String btnsendmail = request.getParameter("btnsendmail");
			String authN = request.getParameter("authN");

			String btnsubmit = request.getParameter("btnsubmit");
			int status = Integer.parseInt(request.getParameter("status"));

			int smsVerified = Integer.parseInt(request.getParameter("smsVerified"));
			int emailVerified = Integer.parseInt(request.getParameter("emailVerified"));
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
 

			if (btnsendmail != null) {
				if (smsVerified == 1) {
					System.out.println("btnsendmail");
					if (userPass.equals("0")) {
						String password = Commons.getAlphaNumericString(5);
						System.out.println("Password: " + password);
						editUser.setUserPassword(password);
						editUser.setEmailVerified(1);
						info1 = EmailUtility.sendEmail(senderEmail, senderPassword, editUser.getEmails(), mailsubject, editUser.getEmails(),
								password);
						session.setAttribute("successMsg", "Infomation Updated successfully!");
						session.setAttribute("errorMsg", "false");
						RestTemplate restTemplate = new RestTemplate();
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

						map.add("senderID", "RUSAMH");
						map.add("user", "spdrusamah@gmail.com:Cyber@mva");
						map.add("receipientno", editUser.getMobileNumber());
						map.add("dcs", "0");
						map.add("msgtxt", "Your Username " + editUser.getEmails() + "\n Password " + password
								+ "\n don't share with any one.");
						map.add("state", "4");

						String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
								String.class);
					} else {
						editUser.setEmailVerified(1);
						info1 = EmailUtility.sendEmail(senderEmail, senderPassword, editUser.getEmails(), mailsubject, editUser.getEmails(),
								userPass);
						List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList", List.class);
						session.setAttribute("regList", getUser);
						session.setAttribute("successMsg", "Password Alredy Updated !");
						session.setAttribute("errorMsg", "false");
					}
				} else {
					editUser.setEmailVerified(0);
					List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList", List.class);
					session.setAttribute("regList", getUser);

					session.setAttribute("successMsg", "Please varify your mobile number !");
					session.setAttribute("errorMsg", "true");
				}

			}
			if (btnsubmit != null) {
				 
				if (emailVerified == 0) {
					editUser.setEmailVerified(0);
				} else {
					editUser.setEmailVerified(1);
				}

				editUser.setIsActive(status);

			}
			 
			editUser.setEditDate(sf.format(date));
			Registration regResponse = rest.postForObject(Constant.url + "/saveRegistration", editUser,
					Registration.class);

			List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList", List.class);
			session.setAttribute("regList", getUser);
			session.setAttribute("successMsg", "Infomation Updated successfully!");
			session.setAttribute("errorMsg", "false");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/activeUserList";
	}
//----------------------------------------Event Registration---------------------------------------------------------------------//

	@RequestMapping(value = "/eventRegList", method = RequestMethod.GET)
	public ModelAndView eventRegList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("approveUser");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			// map.add("delStatus", 1);
			List<EventDetails> getUser = rest.getForObject(Constant.url + "/getUserInfoByUserId", List.class);
			// List<Registration> userList = new
			// ArrayList<Registration>(Arrays.asList(getUser));
			model.addObject("regList", getUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editApproveUser/{userId}/{eventRegId}/{newsblogsId}", method = RequestMethod.GET)
	public ModelAndView editApproveUser(@PathVariable("userId") int userId, @PathVariable("eventRegId") int eventRegId,
			@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("approveForm");
		try {
			System.out.println("id" + userId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", userId);
			editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, RegistrationUserDetail.class);
			System.out.println("User: " + editUser.toString());
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("eventRegId", eventRegId);
			editEvent = rest.postForObject(Constant.url + "/getUserEventByEventRegId", map1, EventRegistration.class);
			System.out.println("User: " + editEvent.toString());

			model.addObject("editUser", editUser);
			model.addObject("newsblogsId", newsblogsId);
			model.addObject("editEvent", editEvent);
			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/approveUserStatus", method = RequestMethod.POST)
	public String approveUserStatus(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		int newsblogsId = Integer.parseInt(request.getParameter("newsblogsId"));
		try {
			// ModelAndView model=new ModelAndView("approveUser");

			User UserDetail = (User) session.getAttribute("UserDetail");
			Info info = null;
			Info info1 = null;
			String approval = null;
			int status = Integer.parseInt(request.getParameter("status"));
			int regId = Integer.parseInt(request.getParameter("regId"));

			System.out.println("newsblogsId : " + newsblogsId);
			String email = request.getParameter("userEmail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", regId);
			Registration user = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, Registration.class);

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("newsblogsId", newsblogsId);
			map1.add("langId", 1);
			NewsDetails eventList = rest.postForObject(Constant.url + "/getEventListByNewsId", map1, NewsDetails.class);
			System.out.println("List :" + eventList.toString());
			if (status == 1) {
				approval = "Approved";
				info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, email, mailsubjectApprove, approval,
						user.getName(), eventList.getHeading(), eventList.getEventLocation(),
						eventList.getEventDateFrom());
			}
			if (status == 2) {
				approval = "Not Approved";
				info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, email, mailsubjectApprove, approval,
						user.getName(), eventList.getHeading(), eventList.getEventLocation(),
						eventList.getEventDateFrom());
			}
			editEvent.setStatusApproval(status);
			editEvent.setApprovalDate(sf.format(date));
			editEvent.setApproveBy(UserDetail.getUserId());

			EventRegistration regResponse = rest.postForObject(Constant.url + "/saveEventRegister", editEvent,
					EventRegistration.class);
			MultiValueMap<String, Object> map3 = new LinkedMultiValueMap<String, Object>();
			map3.add("newsblogsId", newsblogsId);

			session.setAttribute("successMsg", "Infomation Updated successfully!");
			session.setAttribute("errorMsg", "false");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/detailEventList/" + newsblogsId;
	}

	@RequestMapping(value = "/approveUser", method = RequestMethod.GET)
	public String approveUser(HttpServletRequest request, HttpServletResponse response) {

		int newsId = 0;
		try {
			Info info = null;
			Info info1 = null;
			String approval = null;

			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			int userId = Integer.parseInt(request.getParameter("userId"));
			int regId = Integer.parseInt(request.getParameter("regId"));
			newsId = Integer.parseInt(request.getParameter("newsId"));
			int status = Integer.parseInt(request.getParameter("status"));

			System.out.println("id" + userId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", userId);
			editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, RegistrationUserDetail.class);

			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("eventRegId", regId);
			editEvent = rest.postForObject(Constant.url + "/getUserEventByEventRegId", map1, EventRegistration.class);

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			map = new LinkedMultiValueMap<String, Object>();
			map1.add("newsblogsId", newsId);
			map1.add("langId", 1);
			NewsDetails eventList = rest.postForObject(Constant.url + "/getEventListByNewsId", map1, NewsDetails.class);
			System.out.println("List :" + eventList.toString());
			if (status == 1) {
				approval = "Approved";
				info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, editUser.getEmails(),
						mailsubjectApprove, approval, editUser.getName(), eventList.getHeading(),
						eventList.getEventLocation(), eventList.getEventDateFrom());
				RestTemplate restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();

				map.add("senderID", "RUSAMH");
				map.add("user", "spdrusamah@gmail.com:Cyber@mva");
				map.add("receipientno", editUser.getMobileNumber().trim());
				map.add("dcs", "0");
				map.add("msgtxt", "Dear " + editUser.getName() + ", \n"
						+ "	I am pleased to invite you to the attend RUSA portal to track state's plans of higher education  at RUSA on the below mentioned Date and Venue. Please carry ID proof along with you.\n"
						+ "Date and Time:" + eventList.getEventDateFrom() + " \n" + "Venue:"
						+ eventList.getEventLocation());
				map.add("state", "4");

				// String response =
				// restTemplate.postForObject("http://control.bestsms.co.in/api/sendhttp.php",
				// map, String.class);

				String respons = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
						String.class);

			}
			/*
			 * if(status==2) { approval="Not Approved"; info1 =
			 * EmailUtility.sendApprovalEmail(senderEmail, senderPassword,
			 * editUser.getEmails(),
			 * mailsubjectApprove,approval,editUser.getName(),eventList.getHeading(),
			 * eventList.getEventLocation(),eventList.getEventDateFrom()); }
			 */
			editEvent.setStatusApproval(status);
			editEvent.setApprovalDate(sf.format(date));
			editEvent.setApproveBy(UserDetail.getUserId());

			EventRegistration regResponse = rest.postForObject(Constant.url + "/saveEventRegister", editEvent,
					EventRegistration.class);

			session.setAttribute("successMsg", "Infomation Updated successfully!");
			session.setAttribute("errorMsg", "false");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/detailEventList/" + newsId;
	}

	List<EventCountDetails> userList = new ArrayList<EventCountDetails>();

	@RequestMapping(value = "/getAllEventList", method = RequestMethod.GET)
	public ModelAndView getAllEventList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("allEventList");
		try {

			EventCountDetails[] getUser = rest.getForObject(Constant.url + "/getAllEventList",
					EventCountDetails[].class);
			userList = new ArrayList<EventCountDetails>(Arrays.asList(getUser));
			for (int i = 0; i < userList.size(); i++) {
				userList.get(i).setEventDateFrom(DateConvertor.convertToDMY(userList.get(i).getEventDateFrom()));

			}

			model.addObject("userList", userList);
			//System.out.println("userList :" + userList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/showEventListPdf", method = RequestMethod.GET)
	public void showEventListPdf(HttpServletRequest request, HttpServletResponse response) {

	 
		try {

			Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
			// 50, 45, 50, 60
			document.setMargins(Constant.marginLeft, Constant.marginRight, Constant.marginTop, Constant.marginBottom);
			document.setMarginMirroring(false);
			/*document.left(100f);
			document.top(150f);*/
			
			String FILE_PATH = Constant.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			 
			 
			ItextPageEvent event = new ItextPageEvent(header, title, "", "Event List");

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(9);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.0f, 6.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 2.0f, 2.2f });

				Font headFontData = Constant.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constant.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constant.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(Constant.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Event Name ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Date ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Event Manager", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Contact No", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Location", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Document Required", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Applied User", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Approve User", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				int index = 0;

				for (int i = 0; i < userList.size(); i++) {
					// System.err.println("I " + i);
				 

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + userList.get(i).getHeading(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
					cell.setPadding(5);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + userList.get(i).getEventDateFrom(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + userList.get(i).getEventContactPerson(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + userList.get(i).getEventContactNumber(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + userList.get(i).getEventLocation(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					String yesNo = new String();
					
					if(userList.get(i).getExInt2()==1) {
						yesNo="Required";
					}else {
						yesNo="Not Required";
					}
					cell = new PdfPCell(new Phrase("" + yesNo, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + userList.get(i).getApplied(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPadding(5);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + userList.get(i).getApproved(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPadding(5);
					table.addCell(cell);

				}

				document.open();
				Font reportNameFont = Constant.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph("Event List", reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				 
				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				 
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}

	List<EventDetail> eventDetailList = new ArrayList<>();
	
	@RequestMapping(value = "/detailEventList/{newsblogsId}", method = RequestMethod.GET)
	public ModelAndView detailEventList(@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("approveUser");
		try {
			// System.out.println("id" + regId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			// map.add("regId", regId);
			map.add("newsblogsId", newsblogsId);
			EventDetail[] eventDetail = rest.postForObject(Constant.url + "/getUserInfoByNewsblogsId", map,
					EventDetail[].class);
			
			eventDetailList = new ArrayList<>(Arrays.asList(eventDetail));
			//System.out.println("User: " + editUser.toString());

			model.addObject("editUser", eventDetailList);
			model.addObject("newsblogsId", newsblogsId);
			model.addObject("editEvent", editEvent);
			model.addObject("documentUrl", Constant.getCmsPdf);
			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/showEventDetailListPdf", method = RequestMethod.GET)
	public void showEventDetailListPdf(HttpServletRequest request, HttpServletResponse response) {

	 
		try {

			Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
			// 50, 45, 50, 60
			document.setMargins(Constant.marginLeft, Constant.marginRight, Constant.marginTop, Constant.marginBottom);
			document.setMarginMirroring(false);
			/*document.left(100f);
			document.top(150f);*/
			
			String FILE_PATH = Constant.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			 
			 
			ItextPageEvent event = new ItextPageEvent(header, title, "", "Event List");

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(8);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.0f, 6.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});

				Font headFontData = Constant.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constant.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constant.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(Constant.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Type ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Mobile No", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Apply Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Status", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Approve Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Feedback", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);
				 

				int index = 0;

				for (int i = 0; i < eventDetailList.size(); i++) {
					// System.err.println("I " + i);
				 

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + eventDetailList.get(i).getName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT); 
					cell.setPadding(5); 
					table.addCell(cell);
					
					if(eventDetailList.get(i).getUserType()==2) {
						cell = new PdfPCell(new Phrase("" + "Institute", headFontData));
					}else if(eventDetailList.get(i).getUserType()==3){
						cell = new PdfPCell(new Phrase("" + "University", headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "Individual", headFontData));
					} 
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5); 
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + eventDetailList.get(i).getMobileNumber(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + eventDetailList.get(i).getRegDate(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(5);
					table.addCell(cell);

					if(eventDetailList.get(i).getStatusApproval()==0) {
						cell = new PdfPCell(new Phrase("" + "Apply", headFontData));
					}else if(eventDetailList.get(i).getStatusApproval()==1){
						cell = new PdfPCell(new Phrase("" + "Approve", headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "Not Approve", headFontData));
					}  
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);
					
					if(eventDetailList.get(i).getApprovalDate()!="" && eventDetailList.get(i).getApprovalDate()!=null) {
						cell = new PdfPCell(new Phrase("" + eventDetailList.get(i).getApprovalDate(), headFontData));
					}else {
						cell = new PdfPCell(new Phrase("" + "-", headFontData));
					} 
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(5);
					table.addCell(cell);
					
					if(eventDetailList.get(i).getExVar1()!="" && eventDetailList.get(i).getExVar1()!=null) {
						cell = new PdfPCell(new Phrase("" + eventDetailList.get(i).getExVar1(), headFontData));
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}else {
						cell = new PdfPCell(new Phrase("" + "-", headFontData));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
					cell.setPadding(5);
					table.addCell(cell);
					 

				}

				document.open();
				Font reportNameFont = Constant.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph("Event Detail List", reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				 
				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				 
				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}

}