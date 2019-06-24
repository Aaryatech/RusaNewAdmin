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

import org.apache.commons.io.FilenameUtils;
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
import com.ats.rusaadmin.common.FormValidation;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.BannerImages;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.DocumentUpload;
import com.ats.rusaadmin.model.GallaryCategory;
import com.ats.rusaadmin.model.GalleryDetail;
import com.ats.rusaadmin.model.Galleryheader;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.GetGalleryHeaderByCatId;
import com.ats.rusaadmin.model.GetSubCategory;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Logo;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.User;
@Controller
@Scope("session")
public class MasterControllerNew {
	
	RestTemplate rest = new RestTemplate();
	User edituser=new User();
	Section editSection = new Section();
	Category editSubCat=new Category();
	//GetSubCategory editSubCategory = new GetSubCategory();
	Galleryheader editGalleryheader = new Galleryheader();
	BannerImages editbanner=new BannerImages();
	List<Languages> languagesList = new ArrayList<Languages>();
	List<GetCategory> categoryList;
	int pageId;
	Page page = new Page();  
	User user=new User();
	DocumentUpload editupload=new DocumentUpload();

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
			List<User> getUser =  Constant.getRestTemplate().getForObject(Constant.url + "/getAllUserList",
					List.class);
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("userList", getUser);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(@RequestParam("docfile") List<MultipartFile> docfile,HttpServletRequest request, HttpServletResponse response) {

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
		
			int removePhoto = Integer.parseInt(request.getParameter("remove"));
			
			
		//	String userId = request.getParameter("middlename");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
		
			//int seqNo = Integer.parseInt(request.getParameter("seqNo"));
            String docFile=null;
        	VpsImageUpload upload = new VpsImageUpload();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat yy = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			System.out.println(sf.format(date));
			  if(userId.equalsIgnoreCase(null) || userId.equalsIgnoreCase("")) {
				  
				  if(docfile.get(0).getOriginalFilename()==null || docfile.get(0).getOriginalFilename()=="") {
					
					user.setFileName("");
					
				  }
				  else {
					  docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
						user.setFileName(docFile);
						try {
							Info info = upload.saveUploadedImge(docfile.get(0), Constant.userProfileURL,docFile,Constant.values,0,0,0,0,0);
							}catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
				  }
					user.setUserId(0);
					user.setCreatedDate(sf.format(date));
					user.setUserName(userName);
					user.setFirstname(firstName);
					user.setMiddlename(middleName);
					user.setRoles(roles);
					user.setDelStatus(1);
					user.setSortNo(0);
					//user.setCreatedDate(sf.format(date));
					user.setIsActive(isActive);
					user.setUserEmail(email);
					user.setUserPass(pass);
					user.setLastname(lastName);
					user.setAddedByUserId(UserDetail.getUserId());
					//editbanner.setAddedByUserId(UserDetail.getUserId());
				  
		  }else {
			  
			  if(docfile.get(0).getOriginalFilename()==null || docfile.get(0).getOriginalFilename()=="") {
				  try {
				 System.out.println("File");
				 if(removePhoto==1) {
						System.out.println("Remove :"+removePhoto);
						user.setFileName("");
						}
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}else {
					
						docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
						user.setFileName(docFile);
					
					try {
						Info info = upload.saveUploadedImge(docfile.get(0), Constant.userProfileURL,docFile,Constant.values,0,0,0,0,0);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					
				}
			    user.setUserId(Integer.parseInt(userId));
				//user.setCreatedDate(sf.format(date));
				user.setUserName(userName);
				user.setFirstname(firstName);
				user.setMiddlename(middleName);
				user.setRoles(roles);
				user.setDelStatus(1);
				user.setSortNo(0);
				user.setUserPass(pass);
				user.setIsActive(isActive);
				user.setUserEmail(email);
				//user.setUserPass(pass);
				user.setLastname(lastName);
				user.setModifiedDate(yy.format(date));
				user.setEditByUserId(UserDetail.getUserId());
			   
		  }		
			
			User res =  Constant.getRestTemplate().postForObject(Constant.url + "/saveUser", user, User.class);

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

		return "redirect:/userList";
	}
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public String changePass(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session1 = request.getSession();
			User UserDetail =(User) session1.getAttribute("UserDetail");
			 String userId = request.getParameter("userId");
			 String pass = request.getParameter("userPass");
			
			
					user.setUserId(Integer.parseInt(userId));					
					user.setUserPass(pass);
					
					User res =  Constant.getRestTemplate().postForObject(Constant.url + "/saveUser", user, User.class);
					System.out.println("Id: "+userId);
					
						HttpSession session = request.getSession();
						session.setAttribute("successMsg","User Infomation updated successfully!");			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/userList";
	}
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable int userId, HttpServletRequest request,
			HttpServletResponse response,ModelAndView model) {

		//;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId);

			user =  Constant.getRestTemplate().postForObject(Constant.url + "/getUserByUserId", map, User.class);
			   
			 User[] user1 =  Constant.getRestTemplate().getForObject(Constant.url + "/getAllUserList", 
					 User[].class);
			List<User> userList = new ArrayList<User>(Arrays.asList(user1));
			model.addObject("userList", userList);
			HttpSession session = request.getSession();
			session.setAttribute("editUser", user);
			session.setAttribute("isEdit", 1);
			session.setAttribute("imageUrl", Constant.getUserProfileURL); 
			
			if(user.getRoles().equals("DA")) {
				return "redirect:/userList";
			}
			 
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "master/editUser";
	}
	 
	@RequestMapping(value = "/clearUserSessionAttribute", method = RequestMethod.GET)
	public @ResponseBody Info clearUserSessionAttribute(HttpServletRequest request,
			HttpServletResponse response) {

		Info Info = new Info();
		try {
			
			HttpSession session = request.getSession();
			session.removeAttribute("editUser");
			session.removeAttribute("isEdit");
			session.removeAttribute("imageUrl");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Info;
	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("userId", userId); 
			//map.add("delStatus", 0); 
			Info res =  Constant.getRestTemplate().postForObject(Constant.url + "/deleteUser", map, Info.class);
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
				//int pageId = Integer.parseInt(request.getParameter("pageId")); 
				
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
								Info info = upload.saveUploadedImge(docfile.get(0), Constant.bannerImageURL,docFile,Constant.values,0,0,0,0,0);
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
								Info info = upload.saveUploadedImge(docfile.get(0), Constant.bannerImageURL,docFile,Constant.values,0,0,0,0,0 );
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
						
						BannerImages res =  Constant.getRestTemplate().postForObject(Constant.url + "/saveBannerImages", editbanner, BannerImages.class);
						 
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
			Info res =  Constant.getRestTemplate().postForObject(Constant.url + "/deleteBanner", map, Info.class);
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
		 
			
			BannerImages[] bannerImages =  Constant.getRestTemplate().getForObject(Constant.url + "/getAllBannerList",  BannerImages[].class);
			List<BannerImages> bannerImagesList = new ArrayList<BannerImages>(Arrays.asList(bannerImages));
		
			for(int i=0 ; i<bannerImagesList.size() ; i++) {
				bannerImagesList.get(i).setAddDate(DateConvertor.convertToDMY(bannerImagesList.get(i).getAddDate()));
			}
			
			model.addObject("bannerImagesList", bannerImagesList);
			model.addObject("url", Constant.getBannerImageURL);
			
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
			editbanner =  Constant.getRestTemplate().postForObject(Constant.url + "/getSliderImagesById", map, BannerImages.class);
			model.addObject("editbanner", editbanner);
			model.addObject("isEdit", 1);
			model.addObject("url", Constant.getBannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	Logo logo = new Logo();
	
	@RequestMapping(value = "/addLogo", method = RequestMethod.GET)
	public ModelAndView addLogo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addLogo");
		try {
		 
			editbanner=new BannerImages();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", 1); 
			logo =  Constant.getRestTemplate().postForObject(Constant.url + "/getLogoListById", map, Logo.class);
			model.addObject("logo", logo);
			model.addObject("url", Constant.getLgogImageURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/updateOrInsertLogo", method = RequestMethod.POST)
	public String updateOrInsertLogo(@RequestParam("mainLogo") List<MultipartFile> mainLogo,
			@RequestParam("Logo2") List<MultipartFile> Logo2, @RequestParam("Logo3") List<MultipartFile> Logo3,
			HttpServletRequest request, HttpServletResponse response) {

		 try {
			 
			 HttpSession session = request.getSession();
				User UserDetail =(User) session.getAttribute("UserDetail");
			  
				VpsImageUpload upload = new VpsImageUpload();
				
				
				Date date = new Date(); // your date
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				 
				System.out.println(" mainLogo.get(0).getOriginalFilename() " + mainLogo.get(0).getOriginalFilename());
				System.out.println(" Logo2.get(0).getOriginalFilename()) " + Logo2.get(0).getOriginalFilename());
				System.out.println("Logo3.get(0).getOriginalFilename()  " + Logo3.get(0).getOriginalFilename());
				
				Info info = new Info();
						
				 if(logo.getId()==0) {
					  
					 logo.setAddDate(sf.format(date));
				 }else
				 {
					 logo.setEditDate(sf.format(date));
				 }
							 
				 if(mainLogo.get(0).getOriginalFilename()==null || mainLogo.get(0).getOriginalFilename()=="") {
					 
					  info = new Info();
					  info.setError(false);
					  info.setMsg("Updated Successfully ");
					 
				 }else {
					 String imageName = null;
					 String extension = FilenameUtils.getExtension(mainLogo.get(0).getOriginalFilename());
					 imageName =  Constant.logoName+"."+extension;
					 System.err.println("extension " + extension);
					 try {
							info = upload.saveUploadedImge(mainLogo.get(0), Constant.lgogImageURL,imageName,Constant.values,0,0,0,0,0 );
							
							}catch (Exception e) {
								// TODO: handle exception 
							}
					 logo.setLogoMain(imageName);
				 }
				 
				 if(info.isError()==false) {
					 
				 
						 if(Logo2.get(0).getOriginalFilename()==null || Logo2.get(0).getOriginalFilename()=="") {
							 
							 info = new Info();
							  info.setError(false);
							  info.setMsg("Updated Successfully ");
							
						 }else {
							 String imageName = null;
							 String extension = FilenameUtils.getExtension(Logo2.get(0).getOriginalFilename());
							 imageName =  Constant.logoName+"2."+extension;
							 
							 try {
								 info = upload.saveUploadedImge(Logo2.get(0), Constant.lgogImageURL,imageName,Constant.values,0,0,0,0,0 );
									}catch (Exception e) {
										// TODO: handle exception 
									}
							 logo.setLogo2(imageName);
						 }
						
						 if(info.isError()==false) {
							 
						 
								 if(Logo3.get(0).getOriginalFilename()==null || Logo3.get(0).getOriginalFilename()=="") {
									 
									 info = new Info();
									  info.setError(false);
									  info.setMsg("Updated Successfully ");
									
								 }else {
									 String imageName = null;
									 String extension = FilenameUtils.getExtension(Logo3.get(0).getOriginalFilename());
									 imageName =  Constant.logoName+"3."+extension;
									 
									 try {
										 
										 info = upload.saveUploadedImge(Logo3.get(0), Constant.lgogImageURL,imageName,Constant.values,0,0,0,0,0 );
										 
											}catch (Exception e) {
												// TODO: handle exception 
											}
									 logo.setLogo3(imageName);
								 }
								 
									   
										 
										System.out.println("logo" + logo);
								
								Logo res =  Constant.getRestTemplate().postForObject(Constant.url + "/saveLogo", logo, Logo.class);
						 }
				 }  
				 
				 
					session.setAttribute("successMsg",info.getMsg());
					session.setAttribute("errorMsg",info.isError());
						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/addLogo";
	}
	
	@RequestMapping(value = "/uploadDocForm/{pageId}", method = RequestMethod.GET)
	public ModelAndView uploadDocForm(@PathVariable int pageId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/uploadDocument");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page =  Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId",map, Page.class);
			//map.add("delStatus",1);
			GallaryCategory[] category =  Constant.getRestTemplate().getForObject(Constant.url + "/getGalleryCategoryList", GallaryCategory[].class);
			List<GallaryCategory> categoryList = new ArrayList<GallaryCategory>(Arrays.asList(category));
			System.out.println(""+categoryList.toString());
			model.addObject("categoryList",categoryList);
			model.addObject("page", page);
			model.addObject("isEdit", 0);
			//model.addObject("isEdit", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertUploadDoc", method = RequestMethod.POST)
	public String insertDocument(@RequestParam("pagePdf") List<MultipartFile> pagePdf,HttpServletRequest request,
			HttpServletResponse response) {

		 try {
			 
			    HttpSession session = request.getSession();
				User UserDetail =(User) session.getAttribute("UserDetail");
			 
			 	String docId = request.getParameter("docId");
			 	String catId = request.getParameter("cateId");
			 	System.out.println("category: "+catId);
		        String docName = request.getParameter("docName");
				int sortNo = Integer.parseInt(request.getParameter("sortNo"));
				int isActive = Integer.parseInt(request.getParameter("isActive"));
				int isEdit = Integer.parseInt(request.getParameter("isEdit"));
				int pageId = Integer.parseInt(request.getParameter("pageId")); 
				System.out.println("Id: "+isEdit);
				VpsImageUpload upload = new VpsImageUpload();
				String pdfName = null;
				
				boolean ret = false;
				if (FormValidation.Validaton(request.getParameter("cateId"), "") == true
						|| FormValidation.Validaton(request.getParameter("docName"), "") == true
						|| FormValidation.Validaton(request.getParameter("sortNo"), "") == true
						|| FormValidation.Validaton(request.getParameter("isActive"), "") == true) {

					ret = true;
				}

				
				Date date = new Date(); // your date
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				
				 String extension = null;
				 long fileSize =0;
				
 				String fileType= null;
				//System.out.println("docfile extension:"+fileType);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				if(ret==false) {
			    if(docId.equalsIgnoreCase(null) || docId.equalsIgnoreCase("")) {
			    	System.out.println("id null");
					
			    	pdfName =  dateTimeInGMT.format(date)+"_"+pagePdf.get(0).getOriginalFilename();
							editupload.setFileName(pdfName);
							try {
								 
								  extension = FilenameUtils.getExtension(pagePdf.get(0).getOriginalFilename());
								  fileSize = pagePdf.get(0).getSize();
								  fileType= pagePdf.get(0).getContentType();
								   editupload.setFileSize(fileSize);
								    editupload.setFileType(fileType);
								upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf,pdfName);
								 	
							}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							editupload.setAddDate(sf.format(date));
							
							//editbanner.setAddedByUserId(UserDetail.getUserId());
				  }else {
					
					  if(pagePdf.get(0).getOriginalFilename()==null || pagePdf.get(0).getOriginalFilename()=="") {
						  editupload.setEditDate(sf.format(date));
						
						   
						}else {
						
							pdfName =  dateTimeInGMT.format(date)+"_"+pagePdf.get(0).getOriginalFilename();
							editupload.setFileName(pdfName);
							try {
								 
								  extension = FilenameUtils.getExtension(pagePdf.get(0).getOriginalFilename());
								  fileSize =pagePdf.get(0).getSize();
								  fileType= pagePdf.get(0).getContentType();
								  editupload.setFileSize(fileSize);
								  editupload.setFileType(fileType);
								  upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf,pdfName);
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();  
								}
						}
					  editupload.setEditDate(sf.format(date));
					  //editbanner.setEditByUserId(UserDetail.getUserId());
				  }
			 //   editupload.setDocId(0);
			    editupload.setExVar1(docName.trim().replaceAll("[ ]{2,}", " ")); 
			    editupload.setPageId(pageId);
			    editupload.setCateType(catId);
			    editupload.setSortNo(sortNo);
			    editupload.setIsActive(isActive);
			    editupload.setDelStatus(1); 
			
				
				DocumentUpload res =  Constant.getRestTemplate().postForObject(Constant.url + "/saveDocumentFiles", editupload, DocumentUpload.class);
				if(res!=null && isEdit==0) {
					
					PagesModule pagesModule = new PagesModule();
					
					pagesModule.setPageId(pageId);
					pagesModule.setPrimaryKeyId(res.getDocId());
					pagesModule.setModuleId(7);
					PagesModule pagesModuleres =  Constant.getRestTemplate().postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					System.out.println("res " + res);  
				}
				if(res!=null) {
					
					session.setAttribute("successMsg","Infomation added successfully!");
					session.setAttribute("errorMsg","false");
				}else {
					session.setAttribute("successMsg","Infomation added successfully!");
					session.setAttribute("errorMsg","false");
				}
				}
				else {
					session.setAttribute("successMsg", "Invalid Infomation!");
					session.setAttribute("errorMsg", "false");
				}
				
						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/documentUploadList";
	}
	@RequestMapping(value = "/documentUploadList", method = RequestMethod.GET)
	public ModelAndView documentUploadList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/DocUploadList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			DocumentUpload[] getDoc =  Constant.getRestTemplate().getForObject(Constant.url + "/getDocumentList",	DocumentUpload[].class);
			List<DocumentUpload> getDocList = new ArrayList<DocumentUpload>(Arrays.asList(getDoc));
			
			for(int i=0 ; i<getDocList.size() ; i++) {
				getDocList.get(i).setAddDate(DateConvertor.convertToDMY(getDocList.get(i).getAddDate()));
			}
			
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("docList", getDocList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model; 
	}
	@RequestMapping(value = "/editDocument/{docId}", method = RequestMethod.GET)
	public ModelAndView editDocument(@PathVariable int docId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/uploadDocument");
		try {
  
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", docId); 
			editupload =  Constant.getRestTemplate().postForObject(Constant.url + "/getDocumentById", map, DocumentUpload.class);
			// System.out.println("sdf");
			//map.add("delStatus",1);
			GallaryCategory[] category =  Constant.getRestTemplate().getForObject(Constant.url + "/getGalleryCategoryList", GallaryCategory[].class);
			List<GallaryCategory> categoryList = new ArrayList<GallaryCategory>(Arrays.asList(category));
			model.addObject("categoryList",categoryList);
			model.addObject("editupload", editupload);
			model.addObject("isEdit", 1);
			model.addObject("url", Constant.getUploadDocURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/deleteDocument/{docId}", method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int docId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("docId", docId); 
			//map.add("delStatus", 0); 
			Info res =  Constant.getRestTemplate().postForObject(Constant.url + "/deleteDocument", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if(res==null)
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

		return "redirect:/documentUploadList";
	}

}
