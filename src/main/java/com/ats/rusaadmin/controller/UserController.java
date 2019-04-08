package com.ats.rusaadmin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
import com.ats.rusaadmin.model.User;

@Controller
@Scope("session")
public class UserController {

	RestTemplate rest = new RestTemplate();

	static String senderEmail = "akshaykasar72@gmail.com";
	static String senderPassword = "mh151889";
	static String mailsubject = " RUSA Login Credentials ";
	static String mailsubjectApprove = " About Event Shedule";
	Registration editUser = new Registration();
	EventRegistration editEvent = new EventRegistration();
	// --------------------------------------Register User
	// Mapping-----------------------------------------------------------//\

	@RequestMapping(value = "/activeUserList", method = RequestMethod.GET)
	public ModelAndView activeUserList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("activateUser");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			// map.add("delStatus", 1);
			List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList", List.class);
			// List<Registration> userList = new
			// ArrayList<Registration>(Arrays.asList(getUser));
			model.addObject("regList", getUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editActivateUser/{regId}", method = RequestMethod.GET)
	public ModelAndView editActivateUser(@PathVariable("regId") int regId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("userActivationForm");
		try {
			System.out.println("id" + regId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", regId);
			editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, Registration.class);
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
			String uuid = request.getParameter("uuid");
			int type = Integer.parseInt(request.getParameter("type"));
			String email = request.getParameter("userEmail");
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

		
			System.out.println("Emails: " + email);
			System.out.println("name: " + name);
			System.out.println("btnsubmit: " + btnsubmit);
			System.out.println("btnsendmail: " + btnsendmail);

			// Registration editUser=new Registration();

			if (btnsendmail != null) {
				if (smsVerified == 1) {
					System.out.println("btnsendmail");
					if(userPass.equals("0")) {
					String password = Commons.getAlphaNumericString(5);
					System.out.println("Password: " + password);					
					editUser.setUserPassword(password);
					editUser.setEmailVerified(1);
					info1 = EmailUtility.sendEmail(senderEmail, senderPassword, email, mailsubject, email, password);
					session.setAttribute("successMsg", "Infomation Updated successfully!");
					session.setAttribute("errorMsg", "false");
					}
					else {
					editUser.setEmailVerified(1);
					info1 = EmailUtility.sendEmail(senderEmail, senderPassword, email, mailsubject, email, userPass);
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
				System.out.println("btnsubmit");
				
				editUser.setAlternateEmail(alternateEmail);
				editUser.setUserType(type);
				editUser.setEmails(email);
			
				editUser.setName(name);
				editUser.setAisheCode(aishe);
				editUser.setCollegeName(collegeN);
				editUser.setUnversityName(uniName);
				editUser.setDesignationName(designation);
				editUser.setDepartmentName(deptN);
				editUser.setMobileNumber(phone);
				editUser.setAuthorizedPerson(authN);
				editUser.setEditByAdminuserId(UserDetail.getUserId());
				editUser.setName(name);
			if(emailVerified==0)
			{
				editUser.setEmailVerified(0);
			}
			else
			{
				editUser.setEmailVerified(1);
			}
				
				if (status == 0) {
					editUser.setIsActive(status);
					
				}
				if (status == 1) {
					editUser.setIsActive(status);
					
				}
				if (status == 2) {
					editUser.setEmailVerified(0);
					
				}

			
			}
			editUser.setRegId(regId);
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

		return "activateUser";
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
	public ModelAndView editApproveUser(@PathVariable("userId") int userId, @PathVariable("eventRegId") int eventRegId,@PathVariable("newsblogsId") int newsblogsId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("approveForm");
		try {
			System.out.println("id" + userId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", userId);
			editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, Registration.class);
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
			String approval=null;
			int status = Integer.parseInt(request.getParameter("status"));
			int regId = Integer.parseInt(request.getParameter("regId"));
			
			System.out.println("newsblogsId : "+newsblogsId);
			String email=request.getParameter("userEmail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId",regId);
			Registration user = rest.postForObject(Constant.url + "/getRegUserbyRegId",map, Registration .class);
			
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("newsblogsId", newsblogsId);	
			map1.add("langId", 1);
			NewsDetails eventList = rest.postForObject(Constant.url + "/getEventListByNewsId", map1,
					NewsDetails.class);
			System.out.println("List :"+eventList.toString());
			if(status==1)
			{
				 approval="Approved";
				 info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, email, mailsubjectApprove,approval,user.getName(),eventList.getHeading(),eventList.getEventLocation(),eventList.getEventDateFrom());
			}
			if(status==2)
			{
				 approval="Not Approved";
				 info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, email, mailsubjectApprove,approval,user.getName(),eventList.getHeading(),eventList.getEventLocation(),eventList.getEventDateFrom());
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

		return "redirect:/detailEventList/" +newsblogsId ;
	}
	
	@RequestMapping(value = "/approveUser", method = RequestMethod.GET)
	public String approveUser( HttpServletRequest request, HttpServletResponse response) {

		 
		int newsId= 0;
		try {
			Info info = null;
			Info info1 = null;
			String approval=null;
			
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");
			
			int userId= Integer.parseInt(request.getParameter("userId"));
			int regId= Integer.parseInt(request.getParameter("regId"));
			newsId= Integer.parseInt(request.getParameter("newsId"));
			int status= Integer.parseInt(request.getParameter("status"));
			
			
			System.out.println("id" + userId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("regId", userId);
			editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId", map, Registration.class); 
			
			MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<String, Object>();
			map1.add("eventRegId", regId);
			editEvent = rest.postForObject(Constant.url + "/getUserEventByEventRegId", map1, EventRegistration.class);
			
			
		 
		 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			 
			
			 map = new LinkedMultiValueMap<String, Object>();
			map1.add("newsblogsId", newsId);	
			map1.add("langId", 1);
			NewsDetails eventList = rest.postForObject(Constant.url + "/getEventListByNewsId", map1,
					NewsDetails.class);
			System.out.println("List :"+eventList.toString());
			if(status==1)
			{
				 approval="Approved";
				 info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, editUser.getEmails(), mailsubjectApprove,approval,editUser.getName(),eventList.getHeading(),eventList.getEventLocation(),eventList.getEventDateFrom());
			}
			if(status==2)
			{
				 approval="Not Approved";
				 info1 = EmailUtility.sendApprovalEmail(senderEmail, senderPassword, editUser.getEmails(), mailsubjectApprove,approval,editUser.getName(),eventList.getHeading(),eventList.getEventLocation(),eventList.getEventDateFrom());
			}
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

		return "redirect:/detailEventList/"+newsId ;
	}
	
	@RequestMapping(value = "/getAllEventList", method = RequestMethod.GET)
		public ModelAndView getAllEventList(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("allEventList");
			try {
				
				EventCountDetails[] getUser = rest.getForObject(Constant.url + "/getAllEventList",EventCountDetails[].class);
				List<EventCountDetails> userList = new ArrayList<EventCountDetails>(Arrays.asList(getUser));
				for(int i=0; i<userList.size();i++)
				{
					userList.get(i).setEventDateFrom(DateConvertor.convertToDMY(userList.get(i).getEventDateFrom()));					
					
				}
				
				model.addObject("userList", userList);
				System.out.println("userList :"+userList)	;	
			} catch (Exception e) {
				e.printStackTrace();
			}

			return model;
		}

	@RequestMapping(value = "/detailEventList/{newsblogsId}", method = RequestMethod.GET)
	public ModelAndView detailEventList(@PathVariable("newsblogsId") int newsblogsId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("approveUser");
		try {
			//System.out.println("id" + regId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			//map.add("regId", regId);
			map.add("newsblogsId", newsblogsId);
			List<EventDetail> editUser = rest.postForObject(Constant.url + "/getUserInfoByNewsblogsId", map, List.class);
			System.out.println("User: " + editUser.toString());
			//List<EventDetail> userList = new ArrayList<EventDetail>(Arrays.asList(editUser));
			
			/*
			 * for(int i=0; i<userList.size();i++) {
			 * userList.get(i).setApprovalDate(DateConvertor.convertToDMY(userList.get(i).
			 * getApprovalDate()));
			 * 
			 * }
			 */

			model.addObject("editUser", editUser);
			model.addObject("newsblogsId", newsblogsId);
			model.addObject("editEvent", editEvent);
			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	

}