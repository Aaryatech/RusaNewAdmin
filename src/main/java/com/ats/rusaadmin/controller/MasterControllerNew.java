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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.Galleryheader;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.GetSubCategory;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.User;
@Controller
@Scope("session")
public class MasterControllerNew {
	
	RestTemplate rest = new RestTemplate();
//	GetCategory editcat = new GetCategory();
	Section editSection = new Section();
	Category editSubCat=new Category();
	GetSubCategory editSubCategory = new GetSubCategory();
	Galleryheader editGalleryheader = new Galleryheader();
	List<Languages> languagesList = new ArrayList<Languages>();
	List<GetCategory> categoryList;

	//List<Languages> languagesList = new ArrayList<Languages>();
	//GetCategory
	@RequestMapping(value = "/addSubCategoryNew", method = RequestMethod.GET)
	public ModelAndView addSection(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSubCategory");
		try {
			editSection = new Section();
			 
			 Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			 model.addObject("languagesList", languagesList);
			 List<Section> section = rest.getForObject(Constant.url + "/getAllSectionList", 
					 List.class);
			 model.addObject("sectionList", section);
			 
			/* List<GetCategory> category = rest.postForObject(Constant.url + "/getAllCatList", map,
					 List.class);
			 model.addObject("categoryList", category);
			
			System.out.println("Section List: "+category.toString());*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/getCategoryBySectionId", method = RequestMethod.GET)
	public @ResponseBody List<GetCategory> getCategoryBySectionId(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("in method");

			String sectionId = request.getParameter("sectionId");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sectionId", sectionId);

			GetCategory[] categoryL = rest.postForObject(Constant.url + "/getAllCatIdBySectionId", map,
					GetCategory[].class);

			categoryList = new ArrayList<GetCategory>(Arrays.asList(categoryL));

			System.out.println("Get Category List" + categoryList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryList;
	}
	
	/*@RequestMapping(value = "/insertSubCategory", method = RequestMethod.POST)
	public String insertSubCategory(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); 
			
			String sectionId = request.getParameter("sectionId"); 
			int catId =Integer.parseInt(request.getParameter("catId"));
			int seqNo =  Integer.parseInt(request.getParameter("seqNo")); 
			int isActive =  Integer.parseInt(request.getParameter("isActive")); 
			
			List<Category> subCatDescList = new ArrayList<Category>();
			
			//Section section = new Section();

			if (sectionId == "" || sectionId == null) {
				editSubCat.setSectionId(0);
				editSubCat.setCatAddDate(sf.format(date));
				editSubCat.setParentId(catId);
				for(int i = 0 ; i<languagesList.size() ; i++) {
					
					CategoryDescription sectionDescription = new CategoryDescription();
					sectionDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					sectionDescription.setCatName(request.getParameter("subCatName"+languagesList.get(i).getLanguagesId()));
					sectionDescription.setCatDesc(request.getParameter("subCatDesc"+languagesList.get(i).getLanguagesId()));
					
					if(languagesList.get(i).getLanguagesId()==1) {
						
						editSubCat.setSectionName(request.getParameter("subCatName"+languagesList.get(i).getLanguagesId())); 
						editSubCat.setSectionDesc(request.getParameter("subCatDesc"+languagesList.get(i).getLanguagesId()));
						String text = editSection.getSectionName(); 
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editSection.setSectionSlugname(text);
					}
					sectionDescriptionList.add(sectionDescription);
				}
				//editSection.setAddedByUserId(UserDetail.getUserId());
			}else {
				editSection.setSectionId(Integer.parseInt(sectionId));
				editSection.setSectionEditDate(sf.format(date));
				sectionDescriptionList=editSection.getSectionDescriptionList();
				
				for(int i = 0 ; i<sectionDescriptionList.size() ; i++) {
					
					 
					sectionDescriptionList.get(i).setLanguageId(languagesList.get(i).getLanguagesId());
					sectionDescriptionList.get(i).setSectionName(request.getParameter("sectionName"+sectionDescriptionList.get(i).getLanguageId()));
					sectionDescriptionList.get(i).setSectionDesc(request.getParameter("sectionDesc"+sectionDescriptionList.get(i).getLanguageId()));
					
					if(sectionDescriptionList.get(i).getLanguageId()==1) {
						
						editSection.setSectionName(request.getParameter("sectionName"+languagesList.get(i).getLanguagesId())); 
						editSection.setSectionDesc(request.getParameter("sectionDesc"+languagesList.get(i).getLanguagesId()));
						String text = editSection.getSectionName(); 
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editSection.setSectionSlugname(text);
					}
				 
				}
				//editSection.setEditByUserId(UserDetail.getUserId());
			}  
			  
			
			editSection.setSectionSortNo(seqNo); 
			editSection.setSectionDateTime(sf.format(date));
			editSection.setDelStatus(1);
			editSection.setIsActive(isActive);
			
			editSection.setSectionDescriptionList(sectionDescriptionList);
			System.out.println("section" + editSection);

			Section res = rest.postForObject(Constant.url + "/saveSection", editSection, Section.class);

			System.out.println("res " + res);
			
			session = request.getSession();
			if(editSection.getSectionId()==0) {
				session.setAttribute("successMsg","Infomation added successfully!");
			}else {
				session.setAttribute("successMsg","Infomation updated successfully!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionList";
	}*/
			  
}
