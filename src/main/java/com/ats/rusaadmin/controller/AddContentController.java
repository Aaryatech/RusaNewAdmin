package com.ats.rusaadmin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.CMSPageDescription;
import com.ats.rusaadmin.model.CMSPages;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.FreqAskQue;
import com.ats.rusaadmin.model.FreqAskQueDescription;
import com.ats.rusaadmin.model.GetPagesModule;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.ModulesNames;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.SectionTree;
import com.ats.rusaadmin.model.User; 

@Controller
@Scope("session")
public class AddContentController {

	
	RestTemplate rest = new RestTemplate();
	List<Languages> languagesList = new ArrayList<Languages>();
	int pageId;
	Page page = new Page();
	FreqAskQue editFreqAskQue =  new FreqAskQue();
	CMSPages editCMSPages = new CMSPages();
	
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
				  
			  }else if(moduleId==2){
				  
				  url="redirect:/faqForm";
			  } else if(moduleId==6){
				  
				  url="redirect:/textimonialForm/"+pageId;
			  }else if(moduleId==10){
				  
				  url="redirect:/linkExternalUrl/";
			  }else if(moduleId==7){
				  
				  url="redirect:/uploadDocForm/"+pageId;
			  }else if(moduleId==9){
			  
				  url="redirect:/NewsBlogForm/"+pageId;
			  }else if(moduleId==11){
			  
			  url="redirect:/gallaryForm/"+pageId;
			  }else if(moduleId==3 || moduleId==4 || moduleId==5){
				  
				  url="redirect:/gallaryForm/"+pageId+"/"+moduleId;
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
			
			GetPagesModule[] getPagesModule = rest.postForObject(Constant.url + "/getCmsPagesModuleListByPageId",map,
					GetPagesModule[].class);
			
			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule)); 
			model.addObject("getPagesModuleList", getPagesModuleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertCmsForm", method = RequestMethod.POST)
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
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			CMSPages cMSPages = new CMSPages();
			
			List<CMSPageDescription> cMSPageDescriptionList = new ArrayList<CMSPageDescription>();

			VpsImageUpload upload = new VpsImageUpload();
			
				for(int i = 0 ; i<languagesList.size() ; i++) {
					
					CMSPageDescription cMSPageDescription = new CMSPageDescription();
					cMSPageDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					cMSPageDescription.setHeading(request.getParameter("heading1"+languagesList.get(i).getLanguagesId()));
					cMSPageDescription.setSmallheading(request.getParameter("smallheading"+languagesList.get(i).getLanguagesId()));
					cMSPageDescription.setPageDesc(request.getParameter("page_description1"+languagesList.get(i).getLanguagesId()));
					cMSPageDescription.setDateTransaction(sf.format(date));
					cMSPageDescriptionList.add(cMSPageDescription);
				}
				 
				//cMSPages.setAddedByUserId(UserDetail.getUserId());
				if(images.get(0).getOriginalFilename()==null || images.get(0).getOriginalFilename()=="") {
				  
				}else {
				 
					String imageName = new String(); 
					imageName =  dateTimeInGMT.format(date)+"_"+images.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedImge(images.get(0), Constant.gallryImageURL,imageName,Constant.values,0,0,0,0,0);
						 cMSPages.setFeaturedImage(imageName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					 
				}
				
				if(pagePdf.get(0).getOriginalFilename()==null || pagePdf.get(0).getOriginalFilename()=="") {
					  
				}else {
				 
					String pdfName = new String(); 
					pdfName =  dateTimeInGMT.format(date)+"_"+pagePdf.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf,pdfName);
						 cMSPages.setDownloadPdf(pdfName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					 
				}
			cMSPages.setPageId(pageId);
			cMSPages.setPageOrder(seqNo); 
			cMSPages.setIsActive(isActive);
			cMSPages.setDelStatus(1);
			cMSPages.setAddDate(sf.format(date));
			cMSPages.setFeaturedImageAlignment(aligment);
			cMSPages.setDetailList(cMSPageDescriptionList);
			
			System.out.println("category" + cMSPages); 
			CMSPages res = rest.postForObject(Constant.url + "/saveCMSPagesHeaderAndDetail", cMSPages, CMSPages.class);

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
			 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionTreeList";
	}
	
	@RequestMapping(value = "/cmsList", method = RequestMethod.GET)
	public ModelAndView cmsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/cmsList");
		try {
		 
			 
			GetPagesModule[] getPagesModule = rest.getForObject(Constant.url + "/getCmsPagesModuleList",
					GetPagesModule[].class);
			
			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			
			model.addObject("getPagesModuleList", getPagesModuleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/deleteCmsContent/{cmsPageId}", method = RequestMethod.GET)
	public String deleteCmsContent(@PathVariable("cmsPageId") int cmsPageId, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
		 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("cmsPageId", cmsPageId);
			 Info info = rest.postForObject(Constant.url + "/deleteCmsContent",map,
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

		return "redirect:/cmsList";
	}
	
	@RequestMapping(value = "/editCmsContent/{cmsPageId}", method = RequestMethod.GET)
	public ModelAndView editCmsContent(@PathVariable("cmsPageId") int cmsPageId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editCmsContent");
		try {
		 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("cmsPageId", cmsPageId);
			 
			 editCMSPages = rest.postForObject(Constant.url + "/getCmsPagebyId",map,
					CMSPages.class);
			 
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			 
			 map = new LinkedMultiValueMap<String, Object>();
				map.add("pageId", editCMSPages.getPageId());
				Page page = rest.postForObject(Constant.url + "/getPageByPageId",map,
						 Page.class);
			
			
			for(int i = 0 ; i< languagesList.size() ; i++) {
				
				int flag=0;
				
				for(int j = 0 ; j< editCMSPages.getDetailList().size() ; j++) {
					
					if(languagesList.get(i).getLanguagesId()==editCMSPages.getDetailList().get(j).getLanguageId()) {
						flag=1;
						break;
					}
				}
				
				if(flag==0) {
					CMSPageDescription cMSPageDescription  = new CMSPageDescription();
					cMSPageDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editCMSPages.getDetailList().add(cMSPageDescription);
				}
			}
			
			model.addObject("languagesList", languagesList);
			model.addObject("editCMSPages", editCMSPages);
			model.addObject("page", page);
			model.addObject("url", Constant.gallryImageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/submtEditCmsForm", method = RequestMethod.POST)
	public String submtEditCmsForm(@RequestParam("images") List<MultipartFile> images,@RequestParam("pagePdf") List<MultipartFile> pagePdf
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
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			 
			 
			VpsImageUpload upload = new VpsImageUpload();
			
				for(int i = 0 ; i<editCMSPages.getDetailList().size() ; i++) {
					
					 
					editCMSPages.getDetailList().get(i).setHeading(request.getParameter("heading1"+editCMSPages.getDetailList().get(i).getLanguageId()));
					editCMSPages.getDetailList().get(i).setSmallheading(request.getParameter("smallheading"+editCMSPages.getDetailList().get(i).getLanguageId()));
					editCMSPages.getDetailList().get(i).setPageDesc(request.getParameter("page_description1"+editCMSPages.getDetailList().get(i).getLanguageId()));
					 
				}
				 
				//editCMSPages.setEditByUserId(UserDetail.getUserId());
				if(images.get(0).getOriginalFilename()==null || images.get(0).getOriginalFilename()=="") {
				  
				}else {
				 
					String imageName = new String(); 
					imageName =  dateTimeInGMT.format(date)+"_"+images.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedImge(images.get(0), Constant.gallryImageURL,imageName,Constant.values,0,0,0,0,0);
						 editCMSPages.setFeaturedImage(imageName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					 
				}
				
				if(pagePdf.get(0).getOriginalFilename()==null || pagePdf.get(0).getOriginalFilename()=="") {
					  
				}else {
				 
					String pdfName = new String(); 
					pdfName =  dateTimeInGMT.format(date)+"_"+pagePdf.get(0).getOriginalFilename();
					 
					try {
						 upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf,pdfName);
						 editCMSPages.setDownloadPdf(pdfName);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					 
				}
			 
				editCMSPages.setPageOrder(seqNo); 
				editCMSPages.setIsActive(isActive); 
				editCMSPages.setEditDate(sf.format(date));
				editCMSPages.setFeaturedImageAlignment(aligment); 
			
			System.out.println("editCMSPages" + editCMSPages); 
			CMSPages res = rest.postForObject(Constant.url + "/saveCMSPagesHeaderAndDetail", editCMSPages, CMSPages.class);
 
				session.setAttribute("successMsg","Infomation Updated successfully!");
				 
			 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cmsList";
	}
	
	@RequestMapping(value = "/faqForm", method = RequestMethod.GET)
	public ModelAndView faqForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/faqForm");
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
			
			GetPagesModule[] getPagesModule = rest.postForObject(Constant.url + "/getFaqPagesModuleListByPageId",map,
					GetPagesModule[].class);
			
			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			
			model.addObject("getPagesModuleList", getPagesModuleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/insertFaqForm", method = RequestMethod.POST)
	public String insertFaqForm( HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
		 
			int isActive = Integer.parseInt(request.getParameter("status")); 
			int seqNo = Integer.parseInt(request.getParameter("sortNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			 
			FreqAskQue freqAskQue = new FreqAskQue();
			
			List<FreqAskQueDescription> freqAskQueDescriptionList = new ArrayList<FreqAskQueDescription>();

			 
			
				for(int i = 0 ; i<languagesList.size() ; i++) {
					
					FreqAskQueDescription freqAskQueDescription = new FreqAskQueDescription();
					freqAskQueDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					freqAskQueDescription.setFaqQue(request.getParameter("question"+languagesList.get(i).getLanguagesId()));
					freqAskQueDescription.setFaqAns(request.getParameter("ans"+languagesList.get(i).getLanguagesId())); 
					freqAskQueDescriptionList.add(freqAskQueDescription);
				}
				 
				//freqAskQue.setAddedByUserId(UserDetail.getUserId());
				 
				freqAskQue.setPageId(pageId);
				freqAskQue.setFaqSortNo(seqNo); 
				freqAskQue.setIsActive(isActive);
				freqAskQue.setDelStatus(1);
				freqAskQue.setAddDate(sf.format(date));
				 
				freqAskQue.setDescriptionList(freqAskQueDescriptionList);
			
			System.out.println("freqAskQue " + freqAskQue ); 
			FreqAskQue res = rest.postForObject(Constant.url + "/saveUpdateFreqAskQue", freqAskQue, FreqAskQue.class);

			if(res!=null && res.getDescriptionList()!=null) {
				
				PagesModule pagesModule = new PagesModule();
				
				pagesModule.setPageId(res.getPageId());
				pagesModule.setPrimaryKeyId(res.getFaqId());
				pagesModule.setModuleId(2);
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

		return "redirect:/sectionTreeList";
	}
	
	@RequestMapping(value = "/faqList", method = RequestMethod.GET)
	public ModelAndView faqList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/faqList");
		try {
		 
			 
			GetPagesModule[] getPagesModule = rest.getForObject(Constant.url + "/getFaqPagesModuleList",
					GetPagesModule[].class);
			
			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			
			model.addObject("getPagesModuleList", getPagesModuleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	
	@RequestMapping(value = "/deleteFaqContent/{faqId}", method = RequestMethod.GET)
	public String deleteFaqContent(@PathVariable("faqId") int faqId, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
		 
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 map.add("faqIdList", faqId);
			 map.add("delStatus", 0);
			 Info info = rest.postForObject(Constant.url + "/deleteFaq",map,
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

		return "redirect:/faqList";
	}
	
	@RequestMapping(value = "/editFaqContent/{faqId}", method = RequestMethod.GET)
	public ModelAndView editFaqContent(@PathVariable("faqId") int faqId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editFaqContent");
		try {
		 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("faqId", faqId);
			 
			editFreqAskQue = rest.postForObject(Constant.url + "/getFaqById",map,
					FreqAskQue.class);
			 
			Languages[] languages = rest.getForObject(Constant.url + "/getLanguageList", 
					 Languages[].class);
			 languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			 
			 map = new LinkedMultiValueMap<String, Object>();
				map.add("pageId", editFreqAskQue.getPageId());
				Page page = rest.postForObject(Constant.url + "/getPageByPageId",map,
						 Page.class);
			
			
			for(int i = 0 ; i< languagesList.size() ; i++) {
				
				int flag=0;
				
				for(int j = 0 ; j< editFreqAskQue.getDescriptionList().size() ; j++) {
					
					if(languagesList.get(i).getLanguagesId()==editFreqAskQue.getDescriptionList().get(j).getLanguageId()) {
						flag=1;
						break;
					}
				}
				
				if(flag==0) {
					FreqAskQueDescription freqAskQueDescription  = new FreqAskQueDescription();
					freqAskQueDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editFreqAskQue.getDescriptionList().add(freqAskQueDescription);
				}
			}
			
			model.addObject("languagesList", languagesList);
			model.addObject("freqAskQue", editFreqAskQue);
			model.addObject("page", page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/submitEditFaqForm", method = RequestMethod.POST)
	public String submitEditFaqForm( HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
		 
			int isActive = Integer.parseInt(request.getParameter("status")); 
			int seqNo = Integer.parseInt(request.getParameter("sortNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			  
				for(int i = 0 ; i<editFreqAskQue.getDescriptionList().size() ; i++) {
					
					 
					editFreqAskQue.getDescriptionList().get(i).setFaqQue(request.getParameter("question"+editFreqAskQue.getDescriptionList().get(i).getLanguageId()));
					editFreqAskQue.getDescriptionList().get(i).setFaqAns(request.getParameter("ans"+editFreqAskQue.getDescriptionList().get(i).getLanguageId())); 
				 
				}
				 
				//editFreqAskQue.setEditByUserId(UserDetail.getUserId());
				  
				editFreqAskQue.setFaqSortNo(seqNo); 
				editFreqAskQue.setIsActive(isActive);
				editFreqAskQue.setDelStatus(1);
				editFreqAskQue.setEditDate(sf.format(date));
				  
			
			System.out.println("editFreqAskQue " + editFreqAskQue ); 
			FreqAskQue res = rest.postForObject(Constant.url + "/saveUpdateFreqAskQue", editFreqAskQue, FreqAskQue.class);

			if(res!=null) {
				 
				session.setAttribute("successMsg","Infomation updated successfully!");
				 
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/faqList";
	}
	
	@RequestMapping(value = "/linkExternalUrl", method = RequestMethod.GET)
	public ModelAndView linkExternalUrl(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/linkExternalUrl");
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
	
	@RequestMapping(value = "/submitExtenalUrl", method = RequestMethod.POST)
	public String submitExtenalUrl( HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail =(User) session.getAttribute("UserDetail");
			
		 
			String externalUrl =  request.getParameter("externalUrl") ; 
			String newWindow =  request.getParameter("newWindow") ;
 
			page.setExternalUrl(externalUrl);
			page.setExternalUrlTarget(newWindow);
			
			System.out.println("page " + page ); 
			Page res = rest.postForObject(Constant.url + "/savePage", page, Page.class);

			if(res!=null) {
				 
				session.setAttribute("successMsg","Infomation updated successfully!");
				session.setAttribute("errorMsg",false);
			} else {
				session.setAttribute("successMsg","Error While Updating");
				session.setAttribute("errorMsg",true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionTreeList";
	}
	
}
