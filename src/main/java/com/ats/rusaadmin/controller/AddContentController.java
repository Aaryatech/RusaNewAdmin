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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.ModulesNames;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.SectionTree;
import com.ats.rusaadmin.model.User; 

@Controller
@Scope("session")
public class AddContentController {

	
	RestTemplate rest = new RestTemplate();
	List<Languages> languagesList = new ArrayList<Languages>();
	int pageId;
	Page page = new Page();
	
	@RequestMapping(value = "/sectionTreeList", method = RequestMethod.GET)
	public ModelAndView getSectionTreeStructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sectionTreeList");
		try {
		 
			  
			SectionTree[] sectionTree = rest.getForObject(Constant.url + "/getSectionTreeStructure",  SectionTree[].class);
			List<SectionTree> list = new ArrayList<SectionTree>(Arrays.asList(sectionTree));
			model.addObject("list", list);
			
			ModulesNames[] mdulesNames = rest.getForObject(Constant.url + "/getAllModuleNameList",  ModulesNames[].class);
			List<ModulesNames> mdulesList = new ArrayList<ModulesNames>(Arrays.asList(mdulesNames));
			model.addObject("mdulesList", mdulesList);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	
	@RequestMapping(value = "/showModuleForm", method = RequestMethod.GET)
	public String showModuleForm(HttpServletRequest request, HttpServletResponse response) {

		String url = new String();
		try {
		 
			int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			 pageId = Integer.parseInt(request.getParameter("pageId"));
			
			  if(moduleId==1) {
				  
				  url="redirect:/cmsForm";
			  }
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return url;
	}
	
	@RequestMapping(value = "/cmsForm", method = RequestMethod.GET)
	public ModelAndView cmsForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/cmsForm");
		try {
		 
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = rest.postForObject(Constant.url + "/getPageByPageId",map,
					 Page.class);
			model.addObject("page", page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	/*@RequestMapping(value = "/insertCmsForm", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images,@RequestParam("pagePdf") List<MultipartFile> pagePdf
			,HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
			String aligment = request.getParameter("header_top_alignment");
			int isActive = Integer.parseInt(request.getParameter("status")); 
			int seqNo = Integer.parseInt(request.getParameter("page_order"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			
			List<CategoryDescription> categoryDescriptionList = new ArrayList<CategoryDescription>();

			if (catId == "" || catId == null) {
				editcat.setCatId(0);
				editcat.setCatAddDate(sf.format(date));
				
				for(int i = 0 ; i<languagesList.size() ; i++) {
					
					CategoryDescription categoryDescription = new CategoryDescription();
					categoryDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					categoryDescription.setCatName(request.getParameter("catName"+languagesList.get(i).getLanguagesId()));
					categoryDescription.setCatDesc(request.getParameter("catDesc"+languagesList.get(i).getLanguagesId()));
					
					if(languagesList.get(i).getLanguagesId()==1) {
						
						editcat.setCatName(request.getParameter("catName"+languagesList.get(i).getLanguagesId())); 
						editcat.setCatDesc(request.getParameter("catDesc"+languagesList.get(i).getLanguagesId()));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editcat.setSlugName(text);
					}
					categoryDescriptionList.add(categoryDescription);
				}
				editcat.setCategoryDescriptionList(categoryDescriptionList);
				//editcat.setAddedByUserId(UserDetail.getUserId());
			}else {
				editcat.setCatId(Integer.parseInt(catId));
				editcat.setCatEditDate(sf.format(date));
				editcat.setCatAddDate(DateConvertor.convertToYMD(editcat.getCatAddDate()));
				for(int i = 0 ; i<editcat.getCategoryDescriptionList().size() ; i++) {
					 
					editcat.getCategoryDescriptionList().get(i).setCatName(request.getParameter("catName"+editcat.getCategoryDescriptionList().get(i).getLanguageId()));
					editcat.getCategoryDescriptionList().get(i).setCatDesc(request.getParameter("catDesc"+editcat.getCategoryDescriptionList().get(i).getLanguageId()));
					
					if(editcat.getCategoryDescriptionList().get(i).getLanguageId()==1) {
						
						editcat.setCatName(request.getParameter("catName"+editcat.getCategoryDescriptionList().get(i).getLanguageId())); 
						editcat.setCatDesc(request.getParameter("catDesc"+editcat.getCategoryDescriptionList().get(i).getLanguageId()));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();   
						System.out.println(text);
						editcat.setSlugName(text);
					}
					 
				}
				//editcat.setEditByUserId(UserDetail.getUserId());
			}
			
			editcat.setSectionId(sectionId);
			editcat.setCatSortNo(seqNo); 
			editcat.setIsActive(isActive);
			editcat.setDelStatus(1);
			System.out.println("category" + editcat);

			Category res = rest.postForObject(Constant.url + "/saveUpdateCategory", editcat, Category.class);

			System.out.println("res " + res);
			
			if(editcat.getCatId()==0) {
				session.setAttribute("successMsg","Infomation added successfully!");
			}else {
				session.setAttribute("successMsg","Infomation updated successfully!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/categoryList";
	}*/
	
}
