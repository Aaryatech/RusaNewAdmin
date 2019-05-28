package com.ats.rusaadmin.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.BannerImages;
import com.ats.rusaadmin.model.ContactUs;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.ImageLink;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Logo;
import com.ats.rusaadmin.model.Maintainance;
import com.ats.rusaadmin.model.MetaData;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.SiteMaitenance;
import com.ats.rusaadmin.model.SocialChannels;
import com.ats.rusaadmin.model.User;


@Controller
public class NewController {
	RestTemplate rest = new RestTemplate();
	MetaData editMetaData = new MetaData();
	List<Languages> languagesList = new ArrayList<Languages>();
	ImageLink editImageLink=new ImageLink();
	List<MetaData> editMeta = new ArrayList<MetaData>();
	ContactUs contactUs=new ContactUs();
	SocialChannels socialChannel=new SocialChannels();
	Maintainance editSite=new Maintainance();
	List<SiteMaitenance> editsiteMain= new ArrayList<SiteMaitenance>();
	
	@RequestMapping(value = "/addMetaNew", method = RequestMethod.GET)
	public ModelAndView addMetaData(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addMetaData");
		try {
			editMetaData = new MetaData();
			     
			 Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			
			MetaData[] editMet = rest.getForObject(Constant.url + "/getAllMetaDataList", MetaData[].class);
			 editMeta = new ArrayList<MetaData>(Arrays.asList(editMet));
			
			model.addObject("editMetaData", editMeta);
			
			for(int i = 0 ; i< languagesList.size() ; i++) {
				
				int flag=0;
				
				for(int j = 0 ; j< editMeta.size() ; j++) {
					
					if(languagesList.get(i).getLanguagesId()==editMeta.get(j).getLanguageId()) {
						flag=1;
						break;
						
					}
				}
				
				if(flag==0) {
					MetaData MetaData = new MetaData();
					MetaData.setLanguageId(languagesList.get(i).getLanguagesId());
					editMeta.add(MetaData);
					
				}
			}
			 System.out.println(editMeta);
			model.addObject("editMeta", editMeta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

/*	@RequestMapping(value = "/sectionList", method = RequestMethod.GET)
	public ModelAndView sectionList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sectionList");
		try {
			
			 
			Section[] section = rest.getForObject(Constant.url + "/getAllSectionList", 
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);
  
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	*/
	
	
	@RequestMapping(value = "/insertMetaData", method = RequestMethod.POST)
	public String insertMetaData(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); 
			List<MetaData> metaDescriptionList = new ArrayList<MetaData>();
			 
			for(int i = 0 ; i<editMeta.size() ; i++) {
				  
				 
				editMeta.get(i).setSiteTitle(request.getParameter("siteName"+languagesList.get(i).getLanguagesId()));
				editMeta.get(i).setMetaDescription(request.getParameter("metaDescription"+languagesList.get(i).getLanguagesId()));
				editMeta.get(i).setMetaAuthor(request.getParameter("metaAuthor"+languagesList.get(i).getLanguagesId())); 
				editMeta.get(i).setMetaKeywords(request.getParameter("metaKeywords"+languagesList.get(i).getLanguagesId())); 
				
			 
			}
			//editSection.setAddedByUserId(UserDetail.getUserId());
		 System.out.println("siteTitle" + editMeta);

		 List<MetaData> res = rest.postForObject(Constant.url + "/saveMetaData", editMeta, List.class);

		 System.out.println("res " + res);
 		    session = request.getSession();
			if(editMetaData.getId()==0) {
				session.setAttribute("successMsg","Infomation added successfully!");
			}else {
				session.setAttribute("successMsg","Infomation updated successfully!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return "redirect:/addMetaNew";
	}
	
	/*@RequestMapping(value = "/editSection/{sectionId}", method = RequestMethod.GET)
	public ModelAndView editSection(@PathVariable int sectionId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSection");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sectionId", sectionId);

			 editSection = rest.postForObject(Constant.url + "/getSectionBySectionId", map, Section.class);
			

			System.out.println(editSection);
			
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			model.addObject("isEdit", 1);
			
			
			for(int i=0 ; i<languagesList.size() ; i++) {
				
				int flag=0;
				
				for(int j=0 ; j<editSection.getSectionDescriptionList().size() ; j++) {
					
					if(languagesList.get(i).getLanguagesId()==editSection.getSectionDescriptionList().get(j).getLanguageId()) {
						flag=1;
						break;
					}
				}
				
				if(flag==0) {
					SectionDescription sectionDescription = new SectionDescription();
					sectionDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editSection.getSectionDescriptionList().add(sectionDescription);
				}
			}
			
			model.addObject("editSection", editSection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}*/
	  
	@RequestMapping(value = "/addImageLink", method = RequestMethod.GET)
	public ModelAndView addSliderPic(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addImageLink");
		try {
		 
			 
			editImageLink=new ImageLink();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertImageLink", method = RequestMethod.POST)
	public String insertBannerImage(@RequestParam("docfile") List<MultipartFile> docfile,HttpServletRequest request,
			HttpServletResponse response) {

		 try {
			 
			 HttpSession session = request.getSession();
				User UserDetail =(User) session.getAttribute("UserDetail");
			 
			 	String id = request.getParameter("id");
			 	String urlLink = request.getParameter("urlLink");
			 	String titleName = request.getParameter("title_name");
			 	
				int sortOrder = Integer.parseInt(request.getParameter("sortNo"));
				int isActive = Integer.parseInt(request.getParameter("isActive"));
				 
				String imageName=request.getParameter("imageName");
				VpsImageUpload upload = new VpsImageUpload();
				String docFile = null;
				
				Date date = new Date(); // your date
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				  if(id.equalsIgnoreCase(null) || id.equalsIgnoreCase("")) {
					  
					  
							docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
							editImageLink.setSliderImage("re"+docFile);
							//editImageLink.setSliderImage(docFile);
							try {
								Info info = upload.saveUploadedImgeWithResize(docfile.get(0), Constant.bannerImageURL,docFile,Constant.values,1,140,60,0,0);
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							editImageLink.setAddDate(sf.format(date));
							editImageLink.setIsActive(isActive);
							//editbanner.setAddedByUserId(UserDetail.getUserId());
				  }else {
					  
					  if(docfile.get(0).getOriginalFilename()==null || docfile.get(0).getOriginalFilename()=="") {
						  editImageLink.setSliderImage(imageName);
						}else {
							docFile =  dateTimeInGMT.format(date)+"_"+docfile.get(0).getOriginalFilename();
							editImageLink.setSliderImage("re"+docFile);
							//editImageLink.setSliderImage(docFile);
							try {
								Info info = upload.saveUploadedImgeWithResize(docfile.get(0), Constant.bannerImageURL,docFile,Constant.values,1,140,60,0,0 );
								}catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
						}
					  editImageLink.setEditDate(sf.format(date));
					  editImageLink.setIsActive(isActive);
					  //editbanner.setEditByUserId(UserDetail.getUserId());
				  }
				
					  
				  editImageLink.setUrlLink(urlLink);
				  editImageLink.setTitleName(titleName);
				  editImageLink.setDelStatus(1); 
				 // editImageLink.setIsActive(1);
				  editImageLink.setSortOrder(sortOrder); 
				
						 
						System.out.println("editImageLink" + editImageLink);
						
						ImageLink res = rest.postForObject(Constant.url + "/saveImageLink", editImageLink, ImageLink.class);
						 
						if(editImageLink.getId()==0) {
							session.setAttribute("successMsg","Infomation added successfully!");
						}else {
							session.setAttribute("successMsg","Infomation updated successfully!");
						}
						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/imageLinkList";
	}
	
	@RequestMapping(value = "/deleteImageLink/{id}", method = RequestMethod.GET)
	public String deleteImageLink(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);  
			Info res = rest.postForObject(Constant.url + "/deleteImagesLink", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg","Infomation deleted successfully!");
			
			} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/imageLinkList";
	}
	
	@RequestMapping(value = "/imageLinkList", method = RequestMethod.GET)
	public ModelAndView sliderPicList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/imagesLinkList");
		try {
		 
			
			ImageLink[] imagesLink = rest.getForObject(Constant.url + "/getAllImageLinkList",  ImageLink[].class);
			List<ImageLink> imagesLinkList = new ArrayList<ImageLink>(Arrays.asList(imagesLink));
		
			for(int i=0 ; i<imagesLinkList.size() ; i++) {
				imagesLinkList.get(i).setAddDate(DateConvertor.convertToDMY(imagesLinkList.get(i).getAddDate()));
			}
			
			model.addObject("imagesLinkList", imagesLinkList);
			model.addObject("url", Constant.getBannerImageURL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	} 
	
	@RequestMapping(value = "/editImagesLink/{id}", method = RequestMethod.GET)
	public ModelAndView editImagesLink(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addImageLink");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id); 
			editImageLink = rest.postForObject(Constant.url + "/getImageLinksById", map, ImageLink.class);
			model.addObject("editImageLink", editImageLink);
			model.addObject("isEdit", 1);
			model.addObject("url", Constant.getBannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/editContactUs", method = RequestMethod.POST)
	public String editContactUs(HttpServletRequest request,HttpServletResponse response) {

		 try {
			 
				String remark = request.getParameter("remark");

				String id = request.getParameter("id");
				System.out.println("remark: "+remark);
		if(id!=null)
		{
			
				contactUs.setId(Integer.parseInt(id));
				contactUs.setStatusByAdmin(0);
				contactUs.setRemark(remark);
							
          			}
		ContactUs res = rest.postForObject(Constant.url + "/saveContactUs", contactUs, ContactUs.class);

						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/ContactList";
	}
	  
	
	@RequestMapping(value = "/ContactList", method = RequestMethod.GET)
	public ModelAndView ContactList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/contactList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			List<ContactUs> contactList = rest.getForObject(Constant.url + "/getAllContactList",
					List.class);
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("contactList", contactList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/editContact/{id}", method = RequestMethod.GET)
	public ModelAndView editContact(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/editContact");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);

			contactUs = rest.postForObject(Constant.url + "/getContactById", map, ContactUs.class);
		 /*   ContactUs[] contact = rest.getForObject(Constant.url + "/getContactById", 
					ContactUs[].class);
			List<ContactUs> editcontact = new ArrayList<ContactUs>(Arrays.asList(contact));*/
			
			
			model.addObject("editcontact", contactUs);
			model.addObject("isEdit", 1);
			
			System.out.println(contactUs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	
	@RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id); 
			//map.add("delStatus", 0); 
			Info res = rest.postForObject(Constant.url + "/deleteContact", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if(id==0)
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

		return "redirect:/ContactList";
	}
	
	@RequestMapping(value = "/multipleContactDelete", method = RequestMethod.GET)
	public String multipleContactDelete(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";
			
			for(int i=0 ; i<ids.length ; i++) {
				id=id+","+ids[i];
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);  
			Info res = rest.postForObject(Constant.url + "/deleteMultipleContact", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if(res.isError()==true)
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

		return "redirect:/ContactList";
	}
	
	@RequestMapping(value = "/editChannel", method = RequestMethod.POST)
	public String editChannel(HttpServletRequest request,HttpServletResponse response) {

		 try {
			 
				//String remark = request.getParameter("remark");
				String sortNo = request.getParameter("seqNo");
				String isActive = request.getParameter("isActive");
				String urlLink = request.getParameter("urlLink");
				String id = request.getParameter("id");
				
		if(id!=null)
		{
			
			socialChannel.setId(Integer.parseInt(id));
			socialChannel.setUrllinks(urlLink);
			socialChannel.setIsActive(Integer.parseInt(isActive));
			socialChannel.setSortNo(Integer.parseInt(sortNo));
					
        }
		SocialChannels res = rest.postForObject(Constant.url + "/saveSocialChannel", socialChannel, SocialChannels.class);

						
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 
	 return "redirect:/socialChannelList";
	}
	@RequestMapping(value = "/socialChannelList", method = RequestMethod.GET)
	public ModelAndView socialChannelList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/socialChannelList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			List<SocialChannels> socialList = rest.getForObject(Constant.url + "/getAllSocialList",
					List.class);
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("channelList", socialList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/editChannel/{id}", method = RequestMethod.GET)
	public ModelAndView editChannel(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/editSocialChannel");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);

			socialChannel= rest.postForObject(Constant.url + "/getSocialChannelById", map, SocialChannels.class);
		 /*   ContactUs[] contact = rest.getForObject(Constant.url + "/getContactById", 
					ContactUs[].class);
			List<ContactUs> editcontact = new ArrayList<ContactUs>(Arrays.asList(contact));*/
			
			
			model.addObject("editChannel", socialChannel);
			model.addObject("isEdit", 1);
			
			System.out.println(contactUs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	
	@RequestMapping(value = "/deleteChannel/{id}", method = RequestMethod.GET)
	public String deleteChannel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id); 
			//map.add("delStatus", 0); 
			Info res = rest.postForObject(Constant.url + "/deleteSocialChannel", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if(id==0)
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

		return "redirect:/socialChannelList";
	}
	
	
	
	@RequestMapping(value = "/siteMaintenances", method = RequestMethod.GET)
	public ModelAndView addSiteMaintenance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/siteMaintenances");
		try {
			
			editSite = rest.getForObject(Constant.url + "/getMaintananceRecord", Maintainance.class);
			model.addObject("editsiteMain", editSite);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/siteMaintenance", method = RequestMethod.POST)
	public String siteMaintenance(HttpServletRequest request, HttpServletResponse response) {

		 try
		 {	           
			 	HttpSession session = request.getSession();
			 
			 	String status = request.getParameter("status"); 
				String message = request.getParameter("message");  
				Date date = new Date(); // your date
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				editSite.setMaintenanceStatus(Integer.parseInt(status));
				 
				editSite.setEditDate(sf.format(date));
				editSite.setMessage(message);
				 
				Maintainance res = rest.postForObject(Constant.url + "/saveSiteMaintenance",editSite, Maintainance.class);
						 
					if(res.getId()==0) {
							session.setAttribute("successMsg","Error !");
						}else {
							session.setAttribute("successMsg","Infomation Updated successfully!");
						}
		 } catch (Exception e) {
				e.printStackTrace();
			}
		
	 return "redirect:/siteMaintenances";
	}
	@RequestMapping(value = "/siteMapList", method = RequestMethod.GET)
	public ModelAndView siteMapList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/siteMapList");
		try {
		/*	MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			List<SocialChannels> socialList = rest.getForObject(Constant.url + "/getAllSocialList",
					List.class);
			//List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("channelList", socialList);*/
			 
			Section[] section = rest.getForObject(Constant.url + "/getAllSectionList", 
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GetCategory[] getCategory = rest.postForObject(Constant.url + "/getAllCatList", map,
					GetCategory[].class);
			List<GetCategory> categoryList = new ArrayList<GetCategory>(Arrays.asList(getCategory));
			model.addObject("categoryList", categoryList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
}
