package com.ats.rusaadmin.controller;

import java.io.File;
import java.nio.file.Files;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.XssEscapeUtils;
import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.FormValidation;
import com.ats.rusaadmin.common.SessionKeyGen;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.CMSPageDescription;
import com.ats.rusaadmin.model.CMSPages;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.ContentImages;
import com.ats.rusaadmin.model.FreqAskQue;
import com.ats.rusaadmin.model.FreqAskQueDescription;
import com.ats.rusaadmin.model.GetPagesModule;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.ModulesNames;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.SectionTree;
import com.ats.rusaadmin.model.TopMenuList;
import com.ats.rusaadmin.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("session")
public class AddContentController {

	RestTemplate rest = new RestTemplate();
	List<Languages> languagesList = new ArrayList<Languages>();
	int pageId;
	Page page = new Page();
	FreqAskQue editFreqAskQue = new FreqAskQue();
	CMSPages editCMSPages = new CMSPages();

	@RequestMapping(value = "/sectionTreeList", method = RequestMethod.GET)
	public ModelAndView getSectionTreeStructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sectionTreeList");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("langId", 1);
			map.add("type", "1,2");
			TopMenuList sectionTree = Constant.getRestTemplate().postForObject(Constant.url + "/getTopMenuList", map,
					TopMenuList.class);

			/*
			 * SectionTree[] sectionTree = rest.getForObject(Constant.url +
			 * "/getSectionTreeStructure", SectionTree[].class); List<SectionTree> list =
			 * new ArrayList<SectionTree>(Arrays.asList(sectionTree));
			 */
			model.addObject("list", sectionTree);

			ModulesNames[] mdulesNames = Constant.getRestTemplate().getForObject(Constant.url + "/getAllModuleNameList",
					ModulesNames[].class);
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

			// System.out.println(pageId);
			if (moduleId == 1) {

				url = "redirect:/cmsForm";

			} else if (moduleId == 2) {

				url = "redirect:/faqForm";
			} else if (moduleId == 6 || moduleId == 8 || moduleId == 13) {

				url = "redirect:/textimonialForm/" + pageId + "/" + moduleId;
			} else if (moduleId == 10) {

				url = "redirect:/linkExternalUrl";
			} else if (moduleId == 7) {

				url = "redirect:/uploadDocForm/" + pageId;
			} else if (moduleId == 9) {

				url = "redirect:/NewsBlogForm/" + pageId;
			} else if (moduleId == 11) {

				url = "redirect:/EventForm/" + pageId;
			} else if (moduleId == 3) {

				url = "redirect:/gallaryForm/" + pageId + "/" + moduleId;

			} else if (moduleId == 4) {

				url = "redirect:/videoForm/" + pageId;

			} else if (moduleId == 14) {

				url = "redirect:/linkPageMetaData/" + pageId;

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

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			model.addObject("page", page);

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getCmsPagesModuleListByPageId", map, GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertCmsForm", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {
		String redirect = null;
		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {
				User UserDetail = (User) session.getAttribute("UserDetail");

				String aligment = request.getParameter("header_top_alignment");
				int isActive = Integer.parseInt(request.getParameter("status"));
				int seqNo = Integer.parseInt(request.getParameter("page_order"));

				Boolean ret = false;

				if (FormValidation.Validaton(request.getParameter("page_order"), "") == true
						|| FormValidation.Validaton(request.getParameter("status"), "") == true) {

					ret = true;
				}

				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
				CMSPages cMSPages = new CMSPages();

				List<CMSPageDescription> cMSPageDescriptionList = new ArrayList<CMSPageDescription>();

				VpsImageUpload upload = new VpsImageUpload();

				for (int i = 0; i < languagesList.size(); i++) {

					String head1 = request.getParameter("heading1" + languagesList.get(i).getLanguagesId()).trim()
							.replaceAll("[ ]{2,}", " ");

					String head2 = request.getParameter("smallheading" + languagesList.get(i).getLanguagesId()).trim()
							.replaceAll("[ ]{2,}", " ");

					String pageDesc = request.getParameter("page_description1" + languagesList.get(i).getLanguagesId())
							.trim().replaceAll("[ ]{2,}", " ");

					if (FormValidation.Validaton(head1, "") == true || FormValidation.Validaton(head2, "") == true
							|| FormValidation.Validaton(pageDesc, "") == true) {

						ret = true;
						break;
					}

					CMSPageDescription cMSPageDescription = new CMSPageDescription();
					cMSPageDescription.setLanguageId(languagesList.get(i).getLanguagesId());

					cMSPageDescription.setHeading(XssEscapeUtils.jsoupParse(head1));
					cMSPageDescription.setSmallheading(XssEscapeUtils.jsoupParse(head2));
					cMSPageDescription.setPageDesc(XssEscapeUtils.jsoupParseClean(pageDesc));

					cMSPageDescription.setDateTransaction(sf.format(date));
					cMSPageDescriptionList.add(cMSPageDescription);

				}
				if (ret == false) {
					cMSPages.setAddedByUserId(UserDetail.getUserId());
					if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {

					} else {

						String imageName = new String();
						imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

						try {

							Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
									Constant.values, 0, 0, 0, 0, 0);
							if (info.isError() == false) {
								cMSPages.setFeaturedImage(imageName);
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

					if (pagePdf.get(0).getOriginalFilename() == null || pagePdf.get(0).getOriginalFilename() == "") {

					} else {

						String pdfName = new String();
						pdfName = dateTimeInGMT.format(date) + "_" + pagePdf.get(0).getOriginalFilename();

						try {
							Info info = upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf, Constant.DocValues,
									pdfName);

							if (info.isError() == false) {
								cMSPages.setDownloadPdf(pdfName);
							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					cMSPages.setPageId(pageId);
					cMSPages.setPageOrder(seqNo);
					cMSPages.setIsActive(isActive);
					cMSPages.setDelStatus(1);
					cMSPages.setDelStatus(1);
					cMSPages.setAddDate(sf.format(date));
					cMSPages.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));
					cMSPages.setDetailList(cMSPageDescriptionList);

					CMSPages res = Constant.getRestTemplate()
							.postForObject(Constant.url + "/saveCMSPagesHeaderAndDetail", cMSPages, CMSPages.class);
					if (res != null && res.getDetailList() != null) {

						PagesModule pagesModule = new PagesModule();

						pagesModule.setPageId(res.getPageId());
						pagesModule.setPrimaryKeyId(res.getCmsPageId());
						pagesModule.setModuleId(1);
						PagesModule pagesModuleres = Constant.getRestTemplate()
								.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
						// System.out.println("res " + res);

						session.setAttribute("successMsg", "Infomation added successfully!");
						session.setAttribute("errorMsg", "false");
					} else {

						session.setAttribute("successMsg", "Error While Uploading Content !");
						session.setAttribute("errorMsg", "true");
					}

				} else {
					session.setAttribute("successMsg", "Invalid Information !");
					session.setAttribute("errorMsg", "true");
				}
				redirect = "redirect:/sectionTreeList";
				
			} else {
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirect;
	}

	@RequestMapping(value = "/submtEditCmsForm", method = RequestMethod.POST)
	public String submtEditCmsForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			User UserDetail = (User) session.getAttribute("UserDetail");
			String aligment = request.getParameter("header_top_alignment");
			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("page_order"));
			int onHomePage = Integer.parseInt(request.getParameter("onHomePage"));
			int remove = Integer.parseInt(request.getParameter("removeImg"));
			int removePdf = Integer.parseInt(request.getParameter("removePdf"));
			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("page_order"), "") == true
					|| FormValidation.Validaton(request.getParameter("status"), "") == true) {

				ret = true;
			}

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			VpsImageUpload upload = new VpsImageUpload();
			
			String heading = null;
			String smallHeading = null;
			String content = null;

			for (int i = 0; i < editCMSPages.getDetailList().size(); i++) {
				heading = XssEscapeUtils.jsoupParse(request.getParameter("heading1" + editCMSPages.getDetailList().get(i).getLanguageId()).trim()
								.replaceAll("[ ]{2,}", " "));
				
				smallHeading = XssEscapeUtils.jsoupParse(request.getParameter("smallheading" + editCMSPages.getDetailList().get(i).getLanguageId())
						.trim().replaceAll("[ ]{2,}", " "));
				
				content = XssEscapeUtils.jsoupParseClean(request.getParameter("page_description1" + editCMSPages.getDetailList().get(i).getLanguageId())
						.trim().replaceAll("[ ]{2,}", " "));
				if (FormValidation.Validaton(heading,"") == true) {

					ret = true;
					break;
				}

				editCMSPages.getDetailList().get(i).setHeading(heading);
				editCMSPages.getDetailList().get(i).setSmallheading(smallHeading);
				editCMSPages.getDetailList().get(i).setPageDesc(content);
				editCMSPages.getDetailList().get(i).setExInt1(onHomePage);
			}

			if (ret == false) {
				editCMSPages.setEditByUserId(UserDetail.getUserId());

				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {
					// System.out.println("in image null");
					try {

						if (remove == 1) {
							File files = new File(Constant.gallryImageURL + editCMSPages.getFeaturedImage());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(
										" File deleted  " + Constant.gallryImageURL + editCMSPages.getFeaturedImage());
							} else
								System.out.println(
										"doesn't exists  " + Constant.gallryImageURL + editCMSPages.getFeaturedImage());

							System.out.println("Remove :" + remove);
							editCMSPages.setFeaturedImage("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				} else {
					// System.out.println("in image not null");

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
								Constant.values, 0, 0, 0, 0, 0);
						if (info.isError() == false) {
							editCMSPages.setFeaturedImage(imageName);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				// System.out.println("in pdf not null");
				if (pagePdf.get(0).getOriginalFilename() == null || pagePdf.get(0).getOriginalFilename() == "") {

					try {
						if (removePdf == 1) {
							File files = new File(Constant.cmsPdf + editCMSPages.getDownloadPdf());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(" File deleted  " + Constant.cmsPdf + editCMSPages.getDownloadPdf());
							} else
								System.out
										.println("doesn't exists  " + Constant.cmsPdf + editCMSPages.getDownloadPdf());

							// System.out.println("Remove :" + removePdf);
							editCMSPages.setDownloadPdf("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {

					String pdfName = new String();
					pdfName = dateTimeInGMT.format(date) + "_" + pagePdf.get(0).getOriginalFilename();

					try {
						Info info = upload.saveUploadedFiles(pagePdf.get(0), Constant.cmsPdf, Constant.DocValues,
								pdfName);
						if (info.isError() == false) {
							editCMSPages.setDownloadPdf(pdfName);
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				editCMSPages.setPageOrder(seqNo);
				editCMSPages.setIsActive(isActive);
				editCMSPages.setEditDate(sf.format(date));
				editCMSPages.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));

				CMSPages res = Constant.getRestTemplate().postForObject(Constant.url + "/saveCMSPagesHeaderAndDetail",
						editCMSPages, CMSPages.class);

				if (res.getCmsPageId() != 0) {
					session.setAttribute("successMsg", "Infomation Updated successfully!");
					session.setAttribute("errorMsg", "false");
				} else {
					session.setAttribute("successMsg", "Error while updating Content!");
					session.setAttribute("errorMsg", "true");
				}

			} else {
				session.setAttribute("successMsg", "Invalid Information!");
				session.setAttribute("errorMsg", "true");
			}
			redirect = "redirect:/cmsList";
			}else {				
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirect;
	}

	@RequestMapping(value = "/cmsList", method = RequestMethod.GET)
	public ModelAndView cmsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/cmsList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getCmsPagesModuleList", GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteCmsContent/{cmsPageId}/{token}", method = RequestMethod.GET)
	public String deleteCmsContent(@PathVariable("cmsPageId") int cmsPageId, @PathVariable("token") String token, HttpServletRequest request,
			HttpServletResponse response) {
		
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("cmsPageId", cmsPageId);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteCmsContent", map, Info.class);

		

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}
			redirect = "redirect:/cmsList";
			}else {				
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirect;
	}

	@RequestMapping(value = "/editCmsContent/{cmsPageId}", method = RequestMethod.GET)
	public ModelAndView editCmsContent(@PathVariable("cmsPageId") int cmsPageId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editCmsContent");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("cmsPageId", cmsPageId);

			editCMSPages = Constant.getRestTemplate().postForObject(Constant.url + "/getCmsPagebyId", map,
					CMSPages.class);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editCMSPages.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editCMSPages.getDetailList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editCMSPages.getDetailList().get(j).getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					CMSPageDescription cMSPageDescription = new CMSPageDescription();
					cMSPageDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editCMSPages.getDetailList().add(cMSPageDescription);
				}
			}

			model.addObject("languagesList", languagesList);
			model.addObject("editCMSPages", editCMSPages);
			model.addObject("page", page);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("pdfUrl", Constant.getCmsPdf);
			model.addObject("isEdit", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/faqForm", method = RequestMethod.GET)
	public ModelAndView faqForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/faqForm");
		try {

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			model.addObject("page", page);

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getFaqPagesModuleListByPageId", map, GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertFaqForm", method = RequestMethod.POST)
	public String insertFaqForm(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("sortNo"));
			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("status"), "") == true
					|| FormValidation.Validaton(request.getParameter("sortNo"), "") == true) {

				ret = true;
			}
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			FreqAskQue freqAskQue = new FreqAskQue();

			List<FreqAskQueDescription> freqAskQueDescriptionList = new ArrayList<FreqAskQueDescription>();

			for (int i = 0; i < languagesList.size(); i++) {
				String resFaq = XssEscapeUtils
						.jsoupParse(request.getParameter("question" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				String resAns = XssEscapeUtils.jsoupParseClean(request
						.getParameter("ans" + languagesList.get(i).getLanguagesId()).trim().replaceAll("[ ]{2,}", " "));

				if (FormValidation.Validaton(resFaq, "") == true || FormValidation.Validaton(resAns, "") == true) {
					ret = true;
					break;
				}

				FreqAskQueDescription freqAskQueDescription = new FreqAskQueDescription();
				freqAskQueDescription.setLanguageId(languagesList.get(i).getLanguagesId());
				freqAskQueDescription.setFaqQue(XssEscapeUtils.jsoupParse(resFaq));
				freqAskQueDescription.setFaqAns(XssEscapeUtils.jsoupParseClean(resAns));
				freqAskQueDescriptionList.add(freqAskQueDescription);
			}
			if (ret == false) {
				freqAskQue.setAddedByUserId(UserDetail.getUserId());

				freqAskQue.setPageId(pageId);
				freqAskQue.setFaqSortNo(seqNo);
				freqAskQue.setIsActive(isActive);
				freqAskQue.setDelStatus(1);
				freqAskQue.setAddDate(sf.format(date));

				freqAskQue.setDescriptionList(freqAskQueDescriptionList);

				// System.out.println("freqAskQue " + freqAskQue);
				FreqAskQue res = Constant.getRestTemplate().postForObject(Constant.url + "/saveUpdateFreqAskQue",
						freqAskQue, FreqAskQue.class);

				if (res != null && res.getDescriptionList() != null) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(res.getPageId());
					pagesModule.setPrimaryKeyId(res.getFaqId());
					pagesModule.setModuleId(2);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					// System.out.println("res " + res);

					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {

					session.setAttribute("successMsg", "Error While Uploading Content !");
					session.setAttribute("errorMsg", "true");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Infomation !");
				session.setAttribute("errorMsg", "true");

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

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getFaqPagesModuleList", GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteFaqContent/{faqId}", method = RequestMethod.GET)
	public String deleteFaqContent(@PathVariable("faqId") int faqId, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("faqIdList", faqId);
			map.add("delStatus", 0);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteFaq", map, Info.class);
			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/faqList";
	}

	@RequestMapping(value = "/editFaqContent/{faqId}", method = RequestMethod.GET)
	public ModelAndView editFaqContent(@PathVariable("faqId") int faqId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editFaqContent");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("faqId", faqId);

			editFreqAskQue = Constant.getRestTemplate().postForObject(Constant.url + "/getFaqById", map,
					FreqAskQue.class);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editFreqAskQue.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editFreqAskQue.getDescriptionList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editFreqAskQue.getDescriptionList().get(j)
							.getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					FreqAskQueDescription freqAskQueDescription = new FreqAskQueDescription();
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
	public String submitEditFaqForm(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("sortNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("status"), "") == true
					|| FormValidation.Validaton(request.getParameter("sortNo"), "") == true) {

				ret = true;
			}

			
			for (int i = 0; i < editFreqAskQue.getDescriptionList().size(); i++) {

				String question = XssEscapeUtils.jsoupParse(request.getParameter("question" + editFreqAskQue.getDescriptionList().get(i).getLanguageId())
						.trim().replaceAll("[ ]{2,}", " "));
				String answer = XssEscapeUtils.jsoupParseClean(request.getParameter("ans" + editFreqAskQue.getDescriptionList().get(i).getLanguageId()).trim()
						.replaceAll("[ ]{2,}", " "));
				if (FormValidation.Validaton(question,	"") == true) {
					ret = true;
					break;
				}

				editFreqAskQue.getDescriptionList().get(i).setFaqQue(question);
				editFreqAskQue.getDescriptionList().get(i).setFaqAns(answer);

			}
			if (ret == false) {
				editFreqAskQue.setEditByUserId(UserDetail.getUserId());

				editFreqAskQue.setFaqSortNo(seqNo);
				editFreqAskQue.setIsActive(isActive);
				editFreqAskQue.setDelStatus(1);
				editFreqAskQue.setEditDate(sf.format(date));

				System.out.println("editFreqAskQue " + editFreqAskQue);
				FreqAskQue res = Constant.getRestTemplate().postForObject(Constant.url + "/saveUpdateFreqAskQue",
						editFreqAskQue, FreqAskQue.class);

				if (res != null) {

					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {
					session.setAttribute("successMsg", "Failed to add Infomation !");
					session.setAttribute("errorMsg", "true");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Infomation !");
				session.setAttribute("errorMsg", "true");
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
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			model.addObject("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/submitExtenalUrl", method = RequestMethod.POST)
	public String submitExtenalUrl(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String externalUrl = XssEscapeUtils.jsoupParse(request.getParameter("externalUrl").trim().replaceAll("[ ]{2,}", " "));
			String newWindow = request.getParameter("newWindow");

			page.setExternalUrl(externalUrl);
			page.setExternalUrlTarget(newWindow.trim().replaceAll("[ ]{2,}", " "));

			// System.out.println("page " + page);
			Page res = Constant.getRestTemplate().postForObject(Constant.url + "/savePage", page, Page.class);

			if (res != null) {

				session.setAttribute("successMsg", "Infomation Updated successfully!");
				session.setAttribute("errorMsg", false);
			} else {
				session.setAttribute("successMsg", "Failed to Insert Information! ");
				session.setAttribute("errorMsg", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionTreeList";
	}

	@RequestMapping(value = "/linkPageMetaData/{pageId}", method = RequestMethod.GET)
	public ModelAndView linkPageMetaData(@PathVariable("pageId") int pageId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/linkPageMetaData");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			model.addObject("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/submitPageMetaData", method = RequestMethod.POST)
	public String submitPageMetaData(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();

			String metaTitle = XssEscapeUtils.jsoupParse(request.getParameter("metaTitle").trim().replaceAll("[ ]{2,}", " "));
			String metaDesc = XssEscapeUtils.jsoupParse(request.getParameter("metaDesc").trim().replaceAll("[ ]{2,}", " "));
			String metaKeyword = XssEscapeUtils.jsoupParse(request.getParameter("metaKeyword").trim().replaceAll("[ ]{2,}", " "));

			page.setPageMetaTitle(metaTitle);
			page.setPageMetaDescription(metaDesc);
			page.setPageMetaKeyword(metaKeyword);

			System.out.println("page " + page);
			Page res = Constant.getRestTemplate().postForObject(Constant.url + "/savePage", page, Page.class);

			if (res != null) {

				session.setAttribute("successMsg", "Infomation updated successfully!");
				session.setAttribute("errorMsg", false);
			} else {
				session.setAttribute("successMsg", "Failrd to Insert Information!");
				session.setAttribute("errorMsg", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionTreeList";
	}

	@RequestMapping(value = "/sitebrowse", method = RequestMethod.GET)
	public @ResponseBody String sitebrowse(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("moduleForms/showFoldersFiles");
		String jsonString = new String();
		try {

			List<ContentImages> list = new ArrayList<ContentImages>();

			File folder = new File(Constant.otherDocURL);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					ContentImages contentImages = new ContentImages();
					contentImages.setImage(Constant.getOtherDocURL + listOfFiles[i].getName());
					contentImages.setThumb(Constant.getOtherDocURL + listOfFiles[i].getName());
					contentImages.setSize(String.valueOf(listOfFiles[i].length()));
					contentImages.setLastmod(String.valueOf(listOfFiles[i].lastModified()));
					// contentImages.setType(Files.probeContentType(listOfFiles[i].toPath()));
					list.add(contentImages);
				}
			}
			// System.out.println(list);
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@RequestMapping(value = "/languageList", method = RequestMethod.GET)
	public ModelAndView languageList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sectionTreeList");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("langId", 1);
			Languages[] Languages = Constant.getRestTemplate().postForObject(Constant.url + "/getLanguageList", map,
					Languages[].class);
			List<Languages> list = new ArrayList<Languages>(Arrays.asList(Languages));

			// System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}
