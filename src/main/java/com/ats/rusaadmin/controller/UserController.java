package com.ats.rusaadmin.controller;

import java.text.SimpleDateFormat;
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
import com.ats.rusaadmin.common.EmailUtility;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.Registration;
import com.ats.rusaadmin.model.User;

@Controller
@Scope("session")
public class UserController {

	RestTemplate rest = new RestTemplate();
	
	 static String senderEmail = "atsinfosoft@gmail.com";
	 static String senderPassword = "atsinfosoft@123";
	 static String mailsubject = " RUSA Login Credentials ";
	 Registration editUser =new Registration();
	 
		//--------------------------------------Register User Mapping-----------------------------------------------------------//\
		
		@RequestMapping(value = "/activeUserList", method = RequestMethod.GET)
		public ModelAndView activeUserList(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("activateUser");
			try {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				//map.add("delStatus", 1);
				List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList",	List.class);
				//List<Registration> userList = new ArrayList<Registration>(Arrays.asList(getUser));
				model.addObject("regList", getUser);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return model;
		}
		
		
		@RequestMapping(value = "/editActivateUser/{regId}", method = RequestMethod.GET)
		public ModelAndView editActivateUser(@PathVariable("regId") int regId, HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("userActivationForm");
			try {
			 System.out.println("id"+regId);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("regId", regId);
				 editUser = rest.postForObject(Constant.url + "/getRegUserbyRegId",map,Registration.class);
					System.out.println("User: "+editUser.toString());	
				
				model.addObject("editUser", editUser);
				model.addObject("isEdit", 1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return model;
		}
		
		@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
		public String activateUser(HttpServletRequest request,HttpServletResponse response,ModelAndView model) {

			HttpSession session = request.getSession();
			try {
				Info info=null;
				Info info1=null;

				User UserDetail =(User) session.getAttribute("UserDetail");
				
				int regId=Integer.parseInt(request.getParameter("regId"));
				String alternateEmail=request.getParameter("email");
				String name=request.getParameter("name");
				String uuid=request.getParameter("uuid");
				int type=Integer.parseInt(request.getParameter("type"));
				String email=request.getParameter("userEmail");
				String phone=request.getParameter("phone");
				String aishe=request.getParameter("aishe");
				String collegeN=request.getParameter("collegeN");
				String uniName=request.getParameter("uniName");
				String designation=request.getParameter("designation");
				
				String deptN=request.getParameter("deptN");
				String btnsendmail=request.getParameter("btnsendmail");
				String authN=request.getParameter("authN");
				
				String btnsubmit=request.getParameter("btnsubmit");
				int status=Integer.parseInt(request.getParameter("status"));
				
				int smsVerified=Integer.parseInt(request.getParameter("smsVerified"));
				int emailVerified=Integer.parseInt(request.getParameter("emailVerified"));
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				 
				String password=Commons.getAlphaNumericString(5);
				System.out.println("Password: "+password);
				System.out.println("Emails: "+email);
				System.out.println("name: "+name);
				System.out.println("btnsubmit: "+btnsubmit);
				System.out.println("btnsendmail: "+btnsendmail);
				
				//Registration editUser=new Registration();
				
				if(btnsendmail!=null)
				{
					  if(smsVerified==1) 
					  {
						  System.out.println("btnsendmail");
						  editUser.setEmailVerified(1);
						  info1 = EmailUtility.sendEmail(senderEmail,senderPassword,email,mailsubject,name,  password);
						  List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList",List.class);
						  session.setAttribute("regList", getUser);
						  session.setAttribute("successMsg","Infomation Updated successfully!");
						  session.setAttribute("errorMsg","false"); 
					  }
					  else
					  {
						  editUser.setEmailVerified(0);
						  List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList",List.class);
						  session.setAttribute("regList", getUser);
						  session.setAttribute("successMsg","Please varify your mobile number !");
						  session.setAttribute("errorMsg","true"); 
					  }
					 
				}
				if(btnsubmit!=null)
				{
					System.out.println("btnsubmit");
					  editUser.setRegId(regId);
					  editUser.setAlternateEmail(alternateEmail);
					  editUser.setUserUuid(uuid);
					  editUser.setUserType(type);
					  editUser.setEmails(email);
					  editUser.setUserPassword(password); 
					  editUser.setName(name);
					  editUser.setAisheCode(aishe);
					  editUser.setCollegeName(collegeN);
					  editUser.setUnversityName(uniName);
					  editUser.setDesignationName(designation);
					  editUser.setDepartmentName(deptN);					  
					  editUser.setMobileNumber(phone); editUser.setAuthorizedPerson(authN);					  
					  editUser.setEditByAdminuserId(UserDetail.getUserId()); 
					  
					  if(status==0)
					  {
						  editUser.setIsActive(status);
					  }
					  if(status==1)
					  {
						  editUser.setIsActive(status);
					  }
					  if(status==2)
					  {
						  editUser.setIsActive(status);
					  }
					  
					  Registration regResponse = rest.postForObject(Constant.url + "/saveRegistration",editUser, Registration.class);
					  
					  List<Registration> getUser = rest.getForObject(Constant.url + "/getAllRegUserList",List.class);
					  session.setAttribute("regList", getUser);
					  session.setAttribute("successMsg","Infomation Updated successfully!");
					  session.setAttribute("errorMsg","false"); 
				}
			
			/*
			 * if(smsVerified==1 && emailVerified==1) { System.out.println("1");
			 * List<Registration> getUser1 = rest.getForObject(Constant.url +
			 * "/getAllRegUserList",List.class); session.setAttribute("regList", getUser1);
			 * session.setAttribute("successMsg","Information Already Updated !"); } else {
			 * System.out.println("2"); if(smsVerified==1 && emailVerified==0 ||
			 * emailVerified==2 && status==1) { System.out.println("3");
			 * 
			 * if(info1.isError()==false) { System.out.println("4");
			 * System.out.println("info1"); MultiValueMap<String, Object> map = new
			 * LinkedMultiValueMap<String, Object>(); map.add("regId", regId); info =
			 * rest.postForObject(Constant.url +"/updateUserByRegId", map, Info.class);
			 * 
			 * if(info.isError()==false) { System.out.println("5");
			 * editUser.setEmailVerified(1); List<Registration> getUser =
			 * rest.getForObject(Constant.url + "/getAllRegUserList",List.class);
			 * session.setAttribute("regList", getUser);
			 * session.setAttribute("successMsg","Infomation Updated successfully!");
			 * session.setAttribute("errorMsg","false"); } else {
			 * editUser.setEmailVerified(2); System.out.println("5");
			 * session.setAttribute("successMsg","Error while Updateing !");
			 * session.setAttribute("errorMsg","true"); } } } else {
			 * System.out.println("6"); editUser.setEmailVerified(2); List<Registration>
			 * getUser1 = rest.getForObject(Constant.url + "/getAllRegUserList",List.class);
			 * session.setAttribute("regList", getUser1);
			 * session.setAttribute("successMsg","Infomation Updated successfullyr !"); } }
			 * System.out.println("7");
			 */
			
			  
			 
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "activateUser";
		}

}
