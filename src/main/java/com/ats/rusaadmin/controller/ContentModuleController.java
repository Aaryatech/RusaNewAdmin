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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.BannerImages;
import com.ats.rusaadmin.model.GetPagesModule;
import com.ats.rusaadmin.model.Info;
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
	TestImonial editTestImonial=new TestImonial();


	@RequestMapping(value = "/textimonialForm/{pageId}", method = RequestMethod.GET)
	public ModelAndView textimonialForm(@PathVariable int pageId,HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/textImonialForm");
		try {
		 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = rest.postForObject(Constant.url + "/getPageByPageId",map,
					 Page.class);
			model.addObject("page", page);
			model.addObject("isEdit", 0);
			
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
			String msg = request.getParameter("msg");
			int pageId = Integer.parseInt(request.getParameter("pageId")); 
			String location = request.getParameter("location");
			int isActive = Integer.parseInt(request.getParameter("status")); 
			int sortNo = Integer.parseInt(request.getParameter("sortNo"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			TestImonial textImonial = new TestImonial();
			
			List<TestImonial> TestImonialList = new ArrayList<TestImonial>();

			VpsImageUpload upload = new VpsImageUpload();
		
				if(images.get(0).getOriginalFilename()==null || images.get(0).getOriginalFilename()=="") {
				  
				}else {
				 
					String imageName = new String(); 
					imageName =  dateTimeInGMT.format(date)+"_"+images.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedImge(images.get(0), Constant.gallryImageURL,imageName,Constant.values,0,0,0,0,0);
						 editTestImonial.setImageName(imageName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				
				editTestImonial.setPageId(pageId);
				editTestImonial.setDesignation(designation); 
				editTestImonial.setFromName(formName);
				editTestImonial.setIsActive(isActive);
				editTestImonial.setDelStatus(1);
				editTestImonial.setAddDate(sf.format(date));
				editTestImonial.setLocation(location);
				editTestImonial.setSortNo(sortNo);
				editTestImonial.setMessage(msg); 
				
			System.out.println("textImonial" + editTestImonial); 
			TestImonial res = rest.postForObject(Constant.url + "/saveTextImonial", editTestImonial, TestImonial.class);

				if(res!=null && isEdit==0) {
				
				PagesModule pagesModule = new PagesModule();
				
				pagesModule.setPageId(pageId);
				pagesModule.setPrimaryKeyId(res.getId());
				pagesModule.setModuleId(6);
				PagesModule pagesModuleres = rest.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
				System.out.println("res " + res);  
				
				session.setAttribute("successMsg","Infomation added successfully!");
				session.setAttribute("errorMsg","false");
			}else {
				
				session.setAttribute("successMsg","Error While Uploading Content !");
				session.setAttribute("errorMsg","true");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/testImonialList";
	}
	@RequestMapping(value = "/editTestImonial/{id}", method = RequestMethod.GET)
	public ModelAndView editTestImonial(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/textImonialForm");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id); 
			editTestImonial = rest.postForObject(Constant.url + "/getTestImonialById", map, TestImonial.class);
			model.addObject("editTestImonial", editTestImonial);
			model.addObject("url", Constant.gallryImageURL);
			model.addObject("isEdit", 1);
		//	model.addObject("url", Constant.bannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	@RequestMapping(value = "/testImonialList", method = RequestMethod.GET)
	public ModelAndView testImonialList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/testImonialList");
		try {
		 
			GetPagesModule[] getPagesModule = rest.getForObject(Constant.url + "/getTestImonialList",
					GetPagesModule[].class);
			
			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			
			model.addObject("getPagesModuleList", getPagesModuleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteTestImonial/{id}", method = RequestMethod.GET)
	public String deleteTestImonial(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {

		 
		try {		 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("id", id);
			 Info info = rest.postForObject(Constant.url + "/deleteTestImonial",map,
					 Info.class);
			  
			 HttpSession session = request.getSession();
			 
			 if(info.isError()==false) {
				 session.setAttribute("successMsg","Infomation Delete successfully!"); 
			 }else {
				 session.setAttribute("successMsg","Error while Deleting !");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/testImonialList";
	}
}
