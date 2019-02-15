package com.ats.rusaadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.LoginLogs;
import com.ats.rusaadmin.model.LoginResponse;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse res,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		try {
		 
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        String hostName = addr.getHostName(); 
         String userAgent = request.getHeader("User-Agent"); 
         System.out.println("userAgent :" + userAgent);
         System.out.println("hostName" + hostName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		ModelAndView mav = new ModelAndView("login");
		RestTemplate rest = new RestTemplate();
		res.setContentType("text/html");
		HttpSession session = request.getSession();
		try {
			System.out.println("Login Process " + name);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
				mav.addObject("msg", "Invalid login");
			} else {
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("userName", name);
				map.add("password", password);
				LoginResponse loginResponse = rest.postForObject(Constant.url + "/loginResponse", map,
						LoginResponse.class);
				
				 
				if (loginResponse.isError()==false) {
					 
					
					mav = new ModelAndView("welcome");
					 
						session.setAttribute("UserDetail", loginResponse.getUser());
						InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
				        String hostName = addr.getHostName(); 
				        String userAgent = request.getHeader("User-Agent"); 
				         
				        Date date = new Date();
				        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				        LoginLogs saveLoginLogs = new LoginLogs();
				        saveLoginLogs.setIpAddress(hostName);
				        saveLoginLogs.setUserAgent(userAgent);
				        saveLoginLogs.setCreatedDate(sf.format(date));
				        saveLoginLogs.setUserId(loginResponse.getUser().getUserId());
				        
				        LoginLogs resp = rest.postForObject(Constant.url + "/saveLoginLogs", saveLoginLogs, LoginLogs.class);
						
				        System.out.println(resp);

				 
					
				} else {

					mav = new ModelAndView("login");
					System.out.println("Invalid login credentials");
					mav.addObject("msg", "Invalid login");
				}

				
			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
			e.printStackTrace();
			mav.addObject("msg", "Invalid login");
		}

		return mav;

	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}
	@ExceptionHandler(LoginFailException.class)
	public String redirectToLogin() {
		System.out.println("HomeController Login Fail Excep:");

		return "login";
	}
	
	@RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
	public String sessionTimeOut(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/sampleForm", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("sample/sampleForm");
		try {
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/sampleTableList", method = RequestMethod.GET)
	public ModelAndView sampleTableList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("sample/sampleTableList");
		try {
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/langConvert", method = RequestMethod.GET)
	public ModelAndView langConvert(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("sample/langConvert");
		try {
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/setSubModId", method = RequestMethod.GET)
	public @ResponseBody void setSubModId(HttpServletRequest request,
		HttpServletResponse response) {
		int subModId=Integer.parseInt(request.getParameter("subModId"));
		int modId=Integer.parseInt(request.getParameter("modId"));
		 System.out.println("subModId " + subModId);
		System.out.println("modId " + modId); 
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", modId);
		session.setAttribute("sessionSubModuleId",subModId);
		 session.removeAttribute( "exportExcelList" );
	}
	
}
