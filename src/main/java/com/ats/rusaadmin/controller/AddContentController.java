package com.ats.rusaadmin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.ModulesNames;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.SectionTree; 

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
	
}
