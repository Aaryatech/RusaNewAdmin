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
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.CMSPageDescription;
import com.ats.rusaadmin.model.CMSPages;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.TestImonial;
import com.ats.rusaadmin.model.User;

@Controller
@Scope("session")
public class ContentModuleController {
	
	RestTemplate rest = new RestTemplate();
	List<Languages> languagesList = new ArrayList<Languages>();
	int pageId;
	Page page = new Page();


	@RequestMapping(value = "/textimonialForm", method = RequestMethod.GET)
	public ModelAndView textimonialForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/textImonialForm");
		try {
		 
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
	
	@RequestMapping(value = "/insertImonialForm", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images ,HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
		
			
			String formName = request.getParameter("form_name");
			String designation = request.getParameter("designation");
			
			String location = request.getParameter("location");
			int isActive = Integer.parseInt(request.getParameter("status")); 
			//int seqNo = Integer.parseInt(request.getParameter("page_order"));
			int sortNo = Integer.parseInt(request.getParameter("sortNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			TestImonial textImonial = new TestImonial();
			
			List<TestImonial> TestImonialList = new ArrayList<TestImonial>();

			VpsImageUpload upload = new VpsImageUpload();
			
				
				 
				//cMSPages.setAddedByUserId(UserDetail.getUserId());
				if(images.get(0).getOriginalFilename()==null || images.get(0).getOriginalFilename()=="") {
				  
				}else {
				 
					String imageName = new String(); 
					imageName =  dateTimeInGMT.format(date)+"_"+images.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedImge(images.get(0), Constant.gallryImageURL,imageName,Constant.values,0,0,0,0,0);
						 textImonial.setImageName(imageName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					 
				}
				
				
				textImonial.setPageId(pageId);
				textImonial.setDesignation(designation); 
				textImonial.setFromName(formName);
				textImonial.setIsActive(isActive);
				textImonial.setDelStatus(1);
				textImonial.setAddDate(sf.format(date));
				textImonial.setLocation(location);
				textImonial.setSortNo(sortNo);
			
			System.out.println("textImonial" + textImonial); 
			TestImonial res = rest.postForObject(Constant.url + "/saveCMSPagesHeaderAndDetail", textImonial, TestImonial.class);
/*
			if(res!=null && res.getDetailList()!=null) {
				
				PagesModule pagesModule = new PagesModule();
				
				pagesModule.setPageId(res.getPageId());
				pagesModule.setPrimaryKeyId(res.getCmsPageId());
				pagesModule.setModuleId(1);
				PagesModule pagesModuleres = rest.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
				System.out.println("res " + res);  
				
				session.setAttribute("successMsg","Infomation added successfully!");
				session.setAttribute("errorMsg","false");
			}else {
				
				session.setAttribute("successMsg","Error While Uploading Content !");
				session.setAttribute("errorMsg","true");
			}
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/textimonialForm";
	}
}
