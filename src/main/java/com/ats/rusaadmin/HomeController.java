package com.ats.rusaadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import com.ats.rusaadmin.model.DashboardCount;
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
	public String home(HttpServletRequest request, HttpServletResponse res, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		try {

			InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
			String hostName = addr.getHostName();
			String userAgent = request.getHeader("User-Agent");
			/*
			 * System.out.println("userAgent :" + userAgent); System.out.println("hostName"
			 * + hostName);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping("/loginProcess")
	public String helloWorld(HttpServletRequest request, HttpServletResponse res, Model model) throws IOException {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		String mav = new String();

		res.setContentType("text/html");
		HttpSession session = request.getSession();
		try {
			// System.out.println("Login Process " + name);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = "login";
				model.addAttribute("msg", "Invalid login");
			} else {
				String captcha = session.getAttribute("captcha_security").toString();
				String verifyCaptcha = request.getParameter("captcha");

				//if (captcha.equals(verifyCaptcha)) {

					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] messageDigest = md.digest(password.getBytes());
					BigInteger number = new BigInteger(1, messageDigest);
					String hashtext = number.toString(16);

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("userName", name);
					map.add("password", hashtext);
					LoginResponse loginResponse = Constant.getRestTemplate()
							.postForObject(Constant.url + "/loginResponse", map, LoginResponse.class);

					if (loginResponse.isError() == false) {

						mav = "redirect:/dashboard";

						session.setAttribute("UserDetail", loginResponse.getUser());
						session.setAttribute("imageProfileUrl", Constant.getUserProfileURL);
						session.setAttribute("siteAdminUrl", Constant.siteAdminUrl);
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

						LoginLogs resp = Constant.getRestTemplate().postForObject(Constant.url + "/saveLoginLogs",
								saveLoginLogs, LoginLogs.class);

						System.out.println(resp);

					} else {

						mav = "login";
						// System.out.println("Invalid login credentials");
						model.addAttribute("msg", "Invalid login");
					}
				/*} else {
					mav = "login";
					// System.out.println("Invalid login credentials");
					model.addAttribute("msg", "Invalid Text");
				}*/
			}

			Random randChars = new Random();
			String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, 6);
			session.setAttribute("captcha_security", sImageCode);

		} catch (Exception e) {
			Random randChars = new Random();
			String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, 6);
			session.setAttribute("captcha_security", sImageCode);
			e.printStackTrace();
			model.addAttribute("msg", "Invalid login");
		}

		return mav;

	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("welcome");
		try {

			DashboardCount resp = Constant.getRestTemplate().getForObject(Constant.url + "/dashboardCount",
					DashboardCount.class);
			model.addObject("count", resp);

			/*
			 * String aks = "             akshay  kasar      ";
			 * 
			 * aks = aks.trim().replaceAll("[ ]{2,}", " ");
			 * 
			 * System.out.println(aks);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
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
	public @ResponseBody void setSubModId(HttpServletRequest request, HttpServletResponse response) {
		int subModId = Integer.parseInt(request.getParameter("subModId"));
		int modId = Integer.parseInt(request.getParameter("modId"));
		System.out.println("subModId " + subModId);
		System.out.println("modId " + modId);
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", modId);
		session.setAttribute("sessionSubModuleId", subModId);
		session.removeAttribute("exportExcelList");
	}

	@RequestMapping(value = "/smsgatway", method = RequestMethod.GET)
	public String smsgatway(HttpServletRequest request, HttpServletResponse response) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("username", "rusamah-wb");
			map.add("password", "Rus@@123456");
			map.add("senderid", "MHRUSA");
			map.add("mobileno", "7588519473");
			map.add("content", "hello java testing");
			map.add("smsservicetype", "singlemsg");
			String sms = Constant.getRestTemplate().postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest",
					map, String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	@RequestMapping(value = "/javaScriptWarning", method = RequestMethod.GET)
	public String javaScriptWarning(HttpServletRequest request, HttpServletResponse response, Model model) {

		String ret = "javaScriptWarning";
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

}
