package com.ats.rusaadmin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.ContentImages;
import com.ats.rusaadmin.model.GallaryCategory;
import com.ats.rusaadmin.model.GallaryCategoryDescriptioin;
import com.ats.rusaadmin.model.GallaryDetail; 
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages; 
import com.ats.rusaadmin.model.PagesModule; 
import com.ats.rusaadmin.model.User;
import com.fasterxml.jackson.databind.ObjectMapper; 

@Controller
@Scope("session")
public class GallaryController{
	
	RestTemplate rest = new RestTemplate(); 
	List<Languages> languagesList = new ArrayList<Languages>();
	GallaryCategory editGallaryCategory = new GallaryCategory();
	
	@RequestMapping(value = "addGalleryCategory", method = RequestMethod.GET)
	public ModelAndView addGallaryCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("gallary/addGalleryCategory");
		try {
			editGallaryCategory = new GallaryCategory();
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertGalleryCategory", method = RequestMethod.POST)
	public String insertGalleryCategory(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
			String catId = request.getParameter("catId");
			int isActive = Integer.parseInt(request.getParameter("isActive")); 
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<GallaryCategoryDescriptioin> gallaryCategoryDescriptioinList = new ArrayList<GallaryCategoryDescriptioin>();

			if (catId == "" || catId == null) {
				editGallaryCategory.setGalleryCatId(0);
				editGallaryCategory.setAddDate(sf.format(date));
				
				for(int i = 0 ; i<languagesList.size() ; i++) {
					
					GallaryCategoryDescriptioin gallaryCategoryDescriptioin = new GallaryCategoryDescriptioin();
					gallaryCategoryDescriptioin.setLanguageId(languagesList.get(i).getLanguagesId());
					gallaryCategoryDescriptioin.setCateName(request.getParameter("catName"+languagesList.get(i).getLanguagesId())); 
					
					if(languagesList.get(i).getLanguagesId()==1) {
						
						editGallaryCategory.setCateName(request.getParameter("catName"+languagesList.get(i).getLanguagesId())); 
					 
						String text = editGallaryCategory.getCateName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editGallaryCategory.setSlugName(text);
					}
					gallaryCategoryDescriptioinList.add(gallaryCategoryDescriptioin);
				}
				editGallaryCategory.setGallaryCategoryDescriptioinList(gallaryCategoryDescriptioinList);
				editGallaryCategory.setAddedByUserId(UserDetail.getUserId());
				
			}else {
				editGallaryCategory.setGalleryCatId(Integer.parseInt(catId));
				editGallaryCategory.setEditDate(sf.format(date));
				editGallaryCategory.setAddDate(DateConvertor.convertToYMD(editGallaryCategory.getAddDate()));
				
				for(int i = 0 ; i<editGallaryCategory.getGallaryCategoryDescriptioinList().size() ; i++) {
					 
					editGallaryCategory.getGallaryCategoryDescriptioinList().get(i).setCateName(request.getParameter("catName"+editGallaryCategory.getGallaryCategoryDescriptioinList().get(i).getLanguageId()));
					 
					if(editGallaryCategory.getGallaryCategoryDescriptioinList().get(i).getLanguageId()==1) {
						
						editGallaryCategory.setCateName(request.getParameter("catName"+editGallaryCategory.getGallaryCategoryDescriptioinList().get(i).getLanguageId()));  
						String text = editGallaryCategory.getCateName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editGallaryCategory.setSlugName(text);
					}
					 
				}
				editGallaryCategory.setEditByUserId(UserDetail.getUserId());
			}
			
			 
			editGallaryCategory.setSortNo(seqNo); 
			editGallaryCategory.setIsActive(isActive);
			editGallaryCategory.setDelStatus(1);
			System.out.println("category" + editGallaryCategory);

			GallaryCategory res = rest.postForObject(Constant.url + "/saveGalleryCategory", editGallaryCategory, GallaryCategory.class);

			System.out.println("res " + res);
			
			if(editGallaryCategory.getGalleryCatId()==0) {
				session.setAttribute("successMsg","Infomation added successfully!");
			}else {
				session.setAttribute("successMsg","Infomation updated successfully!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/galleryCategoryList";
	}
	
	@RequestMapping(value = "/editGalleryCategory/{galleryCatId}", method = RequestMethod.GET)
	public ModelAndView editSubCategory(@PathVariable int galleryCatId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("gallary/addGalleryCategory");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryCatId", galleryCatId); 
			editGallaryCategory = rest.postForObject(Constant.url + "/getGalleryCatByCatId", map, GallaryCategory.class);
			 
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			
			for(int i=0 ; i<languagesList.size() ; i++) {
				
				int flag=0;
				
				for(int j=0 ; j<editGallaryCategory.getGallaryCategoryDescriptioinList().size() ; j++) {
					
					if(languagesList.get(i).getLanguagesId()==editGallaryCategory.getGallaryCategoryDescriptioinList().get(j).getLanguageId()) {
						flag=1;
						break;
					}
				}
				
				if(flag==0) {
					GallaryCategoryDescriptioin gallaryCategoryDescriptioin = new GallaryCategoryDescriptioin();
					gallaryCategoryDescriptioin.setLanguageId(languagesList.get(i).getLanguagesId());
					editGallaryCategory.getGallaryCategoryDescriptioinList().add(gallaryCategoryDescriptioin);
				}
			}
			System.out.println(editGallaryCategory);
			model.addObject("editGallaryCategory", editGallaryCategory);
			
			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/galleryCategoryList", method = RequestMethod.GET)
	public ModelAndView categoryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("gallary/galleryCategoryList");
		try {
			
			 
			GallaryCategory[] getCategory = rest.getForObject(Constant.url + "/getGalleryCategoryList", 
					GallaryCategory[].class);
			List<GallaryCategory> gallaryCategoryList = new ArrayList<GallaryCategory>(Arrays.asList(getCategory));
			model.addObject("gallaryCategoryList", gallaryCategoryList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteGalleryCategory/{galleryCatId}", method = RequestMethod.GET)
	public String deleteGalleryCategory(@PathVariable("galleryCatId") int galleryCatId, HttpServletRequest request, HttpServletResponse response) {

		//ModelAndView model = new ModelAndView("gallary/galleryCategoryList");
		try {
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryCatId", galleryCatId);
			 
			Info info = rest.postForObject(Constant.url + "/deleteGalleryCategory",map ,
					Info.class);
			HttpSession session = request.getSession();
			session.setAttribute("successMsg","Infomation deleted successfully!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/galleryCategoryList";
	}
	
	@RequestMapping(value = "/gallaryForm/{pageId}/{moduleId}", method = RequestMethod.GET)
	public ModelAndView gallaryForm(@PathVariable("pageId") int pageId,@PathVariable("moduleId") int moduleId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/gallaryForm");
		try {
		 
			 model.addObject("pageId", pageId);
			 model.addObject("moduleId", moduleId);
			
			 GallaryCategory[] getCategory = rest.getForObject(Constant.url + "/getGalleryCategoryList", 
						GallaryCategory[].class);
				List<GallaryCategory> gallaryCategoryList = new ArrayList<GallaryCategory>(Arrays.asList(getCategory));
				model.addObject("catList", gallaryCategoryList);
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/uploadMediaForm", method = RequestMethod.GET)
	public ModelAndView uploadMediaForm( HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		try {
		  
			int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int catId = Integer.parseInt(request.getParameter("catId"));
			
			if(moduleId==3) {
				model = new ModelAndView("moduleForms/uploadMediaForm");
				model.addObject("moduleId", moduleId);
				model.addObject("catId", catId);
				model.addObject("pageId", pageId);
				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("catId", catId); 
				 map.add("moduleId", moduleId);
				 GallaryDetail[] gallaryDetail = rest.postForObject(Constant.url + "/galleryDetailListByCatId", map, GallaryDetail[].class);
				 List<GallaryDetail> gallaryDetailList = new ArrayList<GallaryDetail>(Arrays.asList(gallaryDetail));
				 model.addObject("gallaryDetailList", gallaryDetailList);
				 model.addObject("imageUrl", Constant.getGallryImageURL);
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/uploadedImageList", method = RequestMethod.GET)
	public ModelAndView uploadedImageList( HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/uploadedImageList");
		try {
		  
			 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
				 map.add("moduleId", 3);
				 GallaryDetail[] gallaryDetail = rest.postForObject(Constant.url + "/galleryDetailList", map, GallaryDetail[].class);
				 List<GallaryDetail> gallaryDetailList = new ArrayList<GallaryDetail>(Arrays.asList(gallaryDetail));
				 model.addObject("gallaryDetailList", gallaryDetailList);
				 model.addObject("imageUrl", Constant.getGallryImageURL);
		 
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
	public String deleteImage( HttpServletRequest request, HttpServletResponse response) {

		 
		int moduleId = 0;
		int pageId = 0;
		int catId = 0;
		int id = 0;
		int flag=0;
		
		try {
		   try {
			 id = Integer.parseInt(request.getParameter("id"));
			 moduleId = Integer.parseInt(request.getParameter("moduleId"));
			 pageId = Integer.parseInt(request.getParameter("pageId"));
			 catId = Integer.parseInt(request.getParameter("catId"));
			 
		   }catch (Exception e) {
			   flag=1;
		}
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("galleryDetailsId", id);  
				 Info info = rest.postForObject(Constant.url + "/deleteGalleryDetails", map, Info.class);
				 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag==1) {
			return "redirect:/uploadedImageList";
		}else {
			return "redirect:/uploadMediaForm?pageId="+pageId+"&moduleId="+moduleId+"&catId="+catId;
		}
		
	}
	
	@RequestMapping(value = "/updateTitleName/{id}/{title}", method = RequestMethod.GET)
	public String updateTitleName(@PathVariable("id") int id,@PathVariable("title") String title, HttpServletRequest request, HttpServletResponse response) {
 
		
		try {
		    System.out.println(id+""+title);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				 map.add("galleryDetailsId", id);  
				 map.add("title", title); 
				 Info info = rest.postForObject(Constant.url + "/updateTitleName", map, Info.class);
				 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
			return "redirect:/uploadedImageList";
		 
		
	}
	
	@RequestMapping(value = "/uploadMediaProccess", method = RequestMethod.POST)
	public void uploadMediaProccess(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
			
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
		   
		   Date date = new Date();
		   SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		   String imageName = new String(); 
			imageName =  dateTimeInGMT.format(date)+"_"+file.get(0).getOriginalFilename();
			VpsImageUpload upload = new VpsImageUpload();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			String moduleId =  request.getParameter("moduleId") ;
			int catId = Integer.parseInt(request.getParameter("catId"));
			System.out.println(pageId);
			
			 try {
				 upload.saveUploadedImge(file.get(0), Constant.gallryImageURL,imageName,Constant.values,1,184,134,0,0);
				 
				 GallaryDetail gallaryDetail = new GallaryDetail();
				 gallaryDetail.setAddDate(sf.format(date));
				 gallaryDetail.setFileName(imageName);
				 gallaryDetail.setTitle(file.get(0).getOriginalFilename());
				 gallaryDetail.setPageId(pageId);
				 gallaryDetail.setTypeVideoImage(moduleId);
				 gallaryDetail.setGalleryCatId(catId);
				 gallaryDetail.setFileSize(String.valueOf(file.get(0).getSize()));
				 gallaryDetail.setFileType(file.get(0).getContentType());
				 gallaryDetail.setAddedByUserId(UserDetail.getAddedByUserId());
				 gallaryDetail.setIsActive(1);
				 gallaryDetail.setDelStatus(1);
				 
				 GallaryDetail res = rest.postForObject(Constant.url + "/saveGalleryDetails", gallaryDetail, GallaryDetail.class);
				 
				 if(res!=null) {
					 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					 map.add("moduleId", moduleId);
					 map.add("pageId", pageId);
					 PagesModule pagesModule = rest.postForObject(Constant.url + "/findPageModuleByModuleIdAndPageId", map, PagesModule.class);
					 
					 if(pagesModule.getId()==0) {
						 pagesModule.setModuleId(Integer.parseInt(moduleId));
						 pagesModule.setPageId(pageId);
						 pagesModule.setPrimaryKeyId(pageId);
						 PagesModule pagesModuleres = rest.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					 }
				 }
				 
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
	
	
	
	@RequestMapping(value = "/uploadOtherMedia", method = RequestMethod.GET)
	public ModelAndView uploadOtherMedia( HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("gallary/uploadOtherMedia");
		try {
		   
				List<ContentImages> list = new ArrayList<ContentImages>();
				
				File folder = new File(Constant.otherDocURL);
			    File[] listOfFiles = folder.listFiles();
				 
				for (int i = 0; i < listOfFiles.length; i++) {
				      if (listOfFiles[i].isFile()) {
				    	   
				    	  
				        ContentImages contentImages = new ContentImages();
				        contentImages.setImage(listOfFiles[i].getName());
				        contentImages.setThumb(Constant.getOtherDocURL+listOfFiles[i].getName());
				       
				        contentImages.setLastmod(String.valueOf(listOfFiles[i].lastModified()));
				        contentImages.setType(Files.probeContentType(listOfFiles[i].toPath()));
				         
				        
				        DiskFileItem fileItem = new DiskFileItem(listOfFiles[i].getName(), contentImages.getType(), false, listOfFiles[i].getName(), (int) listOfFiles[i].length() , listOfFiles[i].getParentFile());
				        fileItem.getOutputStream();
				        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
				        contentImages.setSize(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
				        
				        
				        list.add(contentImages);
				      }
				}
				System.out.println(list);
				 
			 model.addObject("list", list);
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/deleteOtherMediaFile/{file}/{extension}", method = RequestMethod.GET)
	public String deleteOtherMediaFile(@PathVariable("file") String file,@PathVariable("extension") String extension, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
			
			File files = new File(Constant.otherDocURL+file);
			//File files = new File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download (1).jpeg");
			
			if(files.delete()){
	            System.out.println(" File deleted  " + Constant.otherDocURL+file );
	        }else System.out.println("doesn't exists  " + Constant.otherDocURL+file);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/uploadOtherMedia";
 
	}
	@RequestMapping(value = "/uploadOtherMediaProccess", method = RequestMethod.POST)
	public void uploadOtherMediaProccess(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
			
			 
		   Date date = new Date();
		   SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		   String imageName = new String(); 
			imageName =  dateTimeInGMT.format(date)+"_"+file.get(0).getOriginalFilename();
			VpsImageUpload upload = new VpsImageUpload();
			 
			 
			 try {
				 upload.saveUploadedImge(file.get(0), Constant.otherDocURL,imageName,Constant.values,0,0,0,0,0);
				 upload.saveUploadedImge(file.get(0), Constant.uploadDocURL,imageName,Constant.DocValues,0,0,0,0,0);
				 
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
	
	

}
