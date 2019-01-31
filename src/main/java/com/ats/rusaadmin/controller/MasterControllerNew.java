package com.ats.rusaadmin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.BannerImages;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.GalleryDetail;
import com.ats.rusaadmin.model.Galleryheader;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.GetGalleryHeaderByCatId;
import com.ats.rusaadmin.model.GetSubCategory;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.User;
@Controller
@Scope("session")
public class MasterControllerNew {
	
	RestTemplate rest = new RestTemplate();
	User edituser=new User();
//	GetCategory editcat = new GetCategory();
	Section editSection = new Section();
	Category editSubCat=new Category();
	GetSubCategory editSubCategory = new GetSubCategory();
	Galleryheader editGalleryheader = new Galleryheader();
	BannerImages editbanner=new BannerImages();
	List<Languages> languagesList = new ArrayList<Languages>();
	List<GetCategory> categoryList;
	User user=new User();
	//List<Languages> languagesList = new ArrayList<Languages>();
	//GetCategory
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addSection(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addUser");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/userList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			List<User> getUser = rest.getForObject(Constant.url + "/getAllUserList",
					List.class);
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("userList", getUser);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session1 = request.getSession();
			User UserDetail =(User) session1.getAttribute("UserDetail");
			
			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			String roles = request.getParameter("roles");
			String firstName = request.getParameter("firstname");
			String middleName = request.getParameter("middlename");
			String lastName = request.getParameter("lastname");
			String email = request.getParameter("userEmail");
			String pass = request.getParameter("userPass");
		//	String userId = request.getParameter("middlename");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
		
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println(sf.format(date));

			if (userId == "" || userId == null) {
				user.setUserId(0);
				user.setCreatedDate(sf.format(date));
				user.setUserName(userName);
				user.setFirstname(firstName);
				user.setMiddlename(middleName);
				user.setRoles(roles);
				user.setDelStatus(1);
				user.setSortNo(seqNo);
				//user.setCreatedDate(sf.format(date));
				user.setIsActive(isActive);
				user.setUserEmail(email);
				user.setUserPass(pass);
				user.setLastname(lastName);
				user.setAddedByUserId(UserDetail.getUserId());
				//UserDetail.getUserId()
				//user.setEditByUserId(0);
				//editcat.setAddedByUserId(UserDetail.getUserId());
			}else {
				user.setUserId(Integer.parseInt(userId));
				user.setCreatedDate(sf.format(date));
				user.setUserName(userName);
				user.setFirstname(firstName);
				user.setMiddlename(middleName);
				user.setRoles(roles);
				user.setDelStatus(1);
				user.setSortNo(seqNo);
				//user.setCreatedDate(sf.format(date));
				user.setIsActive(isActive);
				user.setUserEmail(email);
				user.setUserPass(pass);
				user.setLastname(lastName);
			//	user.setAddedByUserId(0);
				user.setEditByUserId(UserDetail.getUserId());
				
				}
				//editcat.setEditByUserId(UserDetail.getUserId());
			
			
			User res = rest.postForObject(Constant.url + "/saveUser", user, User.class);

			//System.out.println("res " + res);
			
			if(user.getUserId()==0) {
				HttpSession session = request.getSession();
				session.setAttribute("successMsg","User Infomation added successfully!");
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("successMsg","User Infomation updated successfully!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addUser";
	}
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable int userId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addUser");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId);

			 edituser = rest.postForObject(Constant.url + "/getUserByUserId", map, User.class);
			 
			 User[] user = rest.getForObject(Constant.url + "/getAllUserList", 
					 User[].class);
			List<User> userList = new ArrayList<User>(Arrays.asList(user));
			model.addObject("userList", userList);
			
		
			model.addObject("editUser", edituser);
			
			model.addObject("isEdit", 1);
			
			System.out.println(edituser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId); 
			//map.add("delStatus", 0); 
			Info res = rest.postForObject(Constant.url + "/deleteUser", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if(userId==1)
			{
				session.setAttribute("successMsg","Sorry, Can't deleted!");
			}
			else
			{
			session.setAttribute("successMsg","Infomation deleted successfully!");
			}
			} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/addSliderPic", method = RequestMethod.GET)
	public ModelAndView addSliderPic(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSliderPic");
		try {
		 
			editbanner=new BannerImages();
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	} 
	
	@RequestMapping(value = "/insertBannerImage", method = RequestMethod.POST)
	public String insertBannerImage(@RequestParam("docfile") List<MultipartFile> docfile,HttpServletRequest request,
			HttpServletResponse response) {

		 try {
			 
			 HttpSession session = request.getSession();
				User UserDetail =(User) session.getAttribute("UserDetail");
			 
			 	String id = request.getParameter("id");
		        String imageName = request.getParameter("imageName");
				String sliderName = request.getParameter("sliderName");
				String text1 = request.getParameter("text1");
				String text2 = request.getParameter("text2");
				String urlLink = request.getParameter("urlLink");
				String linkName = request.getParameter("linkName");
				int isActive = Integer.parseInt(request.getParameter("isActive"));
				 
				
				VpsImageUpload upload = new VpsImageUpload();
				String docFile = null;
				
				Date date = new Date(); // your date
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				  if(id.equalsIgnoreCase(null) || id.equalsIgnoreCase("")) {
					  
					  
							docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
							editbanner.setSliderImage(docFile);
							try {
								upload.saveUploadedFiles(docfile.get(0), Constant.gallryImage,
										docFile);
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							editbanner.setAddDate(sf.format(date));
							//editbanner.setAddedByUserId(UserDetail.getUserId());
				  }else {
					  
					  if(docfile.get(0).getOriginalFilename()==null || docfile.get(0).getOriginalFilename()=="") {
							editbanner.setSliderImage(imageName);
						}else {
							docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
							editbanner.setSliderImage(docFile);
							try {
								upload.saveUploadedFiles(docfile.get(0), Constant.gallryImage,
										docFile);
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
						}
					  editbanner.setEditDate(sf.format(date));
					  //editbanner.setEditByUserId(UserDetail.getUserId());
				  }
				
					  
						editbanner.setLinkName(linkName);
						editbanner.setSliderName(sliderName);
						editbanner.setUrlLink(urlLink); 
						editbanner.setIsActive(isActive);
						editbanner.setDelStatus(1); 
						editbanner.setText1(text1);
						editbanner.setText2(text2);
						 
						System.out.println("editbanner" + editbanner);
						
						BannerImages res = rest.postForObject(Constant.url + "/saveBannerImages", editbanner, BannerImages.class);
						 
						if(editbanner.getId()==0) {
							session.setAttribute("successMsg","Infomation added successfully!");
						}else {
							session.setAttribute("successMsg","Infomation updated successfully!");
						}
						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/sliderPicList";
	}
	
	@RequestMapping(value = "/deleteSliderPic/{id}", method = RequestMethod.GET)
	public String deleteSliderPic(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);  
			Info res = rest.postForObject(Constant.url + "/deleteBanner", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg","Infomation deleted successfully!");
			
			} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sliderPicList";
	}
	
	@RequestMapping(value = "/sliderPicList", method = RequestMethod.GET)
	public ModelAndView sliderPicList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sliderPicList");
		try {
		 
			
			BannerImages[] bannerImages = rest.getForObject(Constant.url + "/getAllBannerList",  BannerImages[].class);
			List<BannerImages> bannerImagesList = new ArrayList<BannerImages>(Arrays.asList(bannerImages));
		
			for(int i=0 ; i<bannerImagesList.size() ; i++) {
				bannerImagesList.get(i).setAddDate(DateConvertor.convertToDMY(bannerImagesList.get(i).getAddDate()));
			}
			
			model.addObject("bannerImagesList", bannerImagesList);
			model.addObject("url", Constant.gallryImageURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	} 
	@RequestMapping(value = "/editSliderImages/{id}", method = RequestMethod.GET)
	public ModelAndView editPhotoGallary(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSliderPic");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id); 
			editbanner = rest.postForObject(Constant.url + "/getSliderImagesById", map, BannerImages.class);
			model.addObject("editbanner", editbanner);
			model.addObject("isEdit", 1);
			model.addObject("url", Constant.gallryImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
}
