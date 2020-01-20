package com.ats.rusaadmin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.ats.rusaadmin.XssEscapeUtils;
import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.FormValidation;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.GallaryCategory;
import com.ats.rusaadmin.model.GallaryDetail;
import com.ats.rusaadmin.model.GetPagesModule;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.NewsBlog;
import com.ats.rusaadmin.model.NewsBlogDescription;
import com.ats.rusaadmin.model.Page;
import com.ats.rusaadmin.model.PagesModule;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.TestImonial;
import com.ats.rusaadmin.model.TestimonialDetail;
import com.ats.rusaadmin.model.User;

@Controller
@Scope("session")
public class ContentModuleController {

	RestTemplate rest = new RestTemplate();
	List<Languages> languagesList = new ArrayList<Languages>();
	int pageId;
	Page page = new Page();
	TestImonial editTestImonial = new TestImonial();
	NewsBlog editNewsBlog = new NewsBlog();
	List<NewsBlog> newsBlogList = new ArrayList<NewsBlog>();
	GallaryDetail editGalleryDetail = new GallaryDetail();

	@RequestMapping(value = "textimonialForm/{pageId}/{moduleId}", method = RequestMethod.GET)
	public ModelAndView textimonialForm(@PathVariable int pageId, @PathVariable int moduleId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/textImonialForm");
		try {
			editTestImonial = new TestImonial();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);

			model.addObject("page", page);
			model.addObject("isEdit", 0);
			model.addObject("moduleId", moduleId);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			model.addObject("editTestImonial", editTestImonial);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertTestimonialForm", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		HttpSession session = request.getSession();
		try {
//1 time
			User UserDetail = (User) session.getAttribute("UserDetail");
			int remove = Integer.parseInt(request.getParameter("removeImg"));
			// String formName = request.getParameter("form_name");
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int id = Integer.parseInt(request.getParameter("id"));
			int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int formType = Integer.parseInt(request.getParameter("formType"));

			//
			boolean ret = false;
			/*
			 * if (FormValidation.Validaton(request.getParameter("sortNo"), "") == true ||
			 * FormValidation.Validaton(request.getParameter("status"), "") == true ||
			 * FormValidation.Validaton(request.getParameter("form_name"), "") == true) {
			 * 
			 * ret = true; }
			 */
			int isActive = Integer.parseInt(request.getParameter("status"));
			int sortNo = Integer.parseInt(request.getParameter("sortNo"));
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			List<TestimonialDetail> newsBlogDescriptionList = new ArrayList<TestimonialDetail>();

			String formName = null;
			String designation = null;
			String location = null;
			String message = null;
			for (int i = 0; i < languagesList.size(); i++) {

				int lanId = languagesList.get(i).getLanguagesId();
				formName = XssEscapeUtils
						.jsoupParse(request.getParameter("form_name" + languagesList.get(i).getLanguagesId()));
				designation = XssEscapeUtils
						.jsoupParse(request.getParameter("designation" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				location = XssEscapeUtils
						.jsoupParse(request.getParameter("location" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));

				if (lanId == 1) {
					editTestImonial.setDesignation(designation);
					editTestImonial.setLocation(location);
					editTestImonial.setFromName(formName);
					if (formType == 1) {
						message = XssEscapeUtils
								.jsoupParseClean(request.getParameter("message" + languagesList.get(i).getLanguagesId())
										.trim().replaceAll("[ ]{2,}", " "));
						editTestImonial.setMessage(message);
					} else {
						message = XssEscapeUtils.jsoupParseClean(
								request.getParameter("message" + languagesList.get(i).getLanguagesId()));
						editTestImonial.setMessage(message);
					}
				}

				TestimonialDetail newsBlogDescription = new TestimonialDetail();

				designation = XssEscapeUtils
						.jsoupParse(request.getParameter("designation" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				location = XssEscapeUtils
						.jsoupParse(request.getParameter("location" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));

				newsBlogDescription.setLangId(languagesList.get(i).getLanguagesId());
				newsBlogDescription.setDesignation(designation);
				newsBlogDescription.setFromName(formName);
				newsBlogDescription.setLocation(location);

				if (formType == 1) {
					message = XssEscapeUtils
							.jsoupParseClean(request.getParameter("message" + languagesList.get(i).getLanguagesId())
									.trim().replaceAll("[ ]{2,}", " "));
					newsBlogDescription.setMessage(message);
				} else {
					message = XssEscapeUtils
							.jsoupParseClean(request.getParameter("message" + languagesList.get(i).getLanguagesId()));
					newsBlogDescription.setMessage(message);
				}

				newsBlogDescription.setAddDate(sf.format(date));
				newsBlogDescription.setAddedByUserId(UserDetail.getUserId());
				newsBlogDescription.setDelStatus(1);
				newsBlogDescription.setExInt2(1);
				newsBlogDescription.setExInt1(formType);
				newsBlogDescription.setExVar1("NA");
				newsBlogDescription.setExVar2("NA");
				newsBlogDescription.setFromName(formName);
				newsBlogDescription.setIsActive(1);
				newsBlogDescription.setTestHeadId(0);
				newsBlogDescriptionList.add(newsBlogDescription);
			}

			// System.out.println("detail lis==="+newsBlogDescriptionList.toString());

			VpsImageUpload upload = new VpsImageUpload();
			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {
					// System.out.println("in image null");
					try {

						if (remove == 1) {
							File files = new File(Constant.gallryImageURL + editTestImonial.getImageName());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(
										" File deleted  " + Constant.gallryImageURL + editTestImonial.getImageName());
							} else
								System.out.println(
										"doesn't exists  " + Constant.gallryImageURL + editTestImonial.getImageName());

							// System.out.println("Remove :" + remove);
							editTestImonial.setImageName("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName, Constant.values, 0,
								0, 0, 0, 0);
						editTestImonial.setImageName(imageName);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				if (id == 0) {
					// System.out.println(" add Id :" + id);
					editTestImonial.setAddedByUserId(UserDetail.getUserId());
				} else {
					// System.out.println("edit Id :" + id);
					editTestImonial.setEditByUserId(UserDetail.getUserId());
				}

				editTestImonial.setSectionId(moduleId);
				editTestImonial.setPageId(pageId);
				editTestImonial.setIsActive(isActive);
				editTestImonial.setDelStatus(1);
				editTestImonial.setAddDate(sf.format(date));
				editTestImonial.setSortNo(sortNo);
				// editTestImonial.setFromName(formName);
				editTestImonial.setDetailList(newsBlogDescriptionList);
				editTestImonial.setExInt1(formType);

				// System.out.println("textImonial" + editTestImonial);
				TestImonial res = Constant.getRestTemplate().postForObject(
						Constant.url + "/saveTestinomialsHeaderAndDetail", editTestImonial, TestImonial.class);

				if (res != null && isEdit == 0) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(pageId);
					pagesModule.setPrimaryKeyId(res.getId());
					pagesModule.setModuleId(moduleId);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					// System.out.println("res " + res);
				}
				if (res != null) {

					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {

					session.setAttribute("successMsg", "Failed to add Information!");
					session.setAttribute("errorMsg", "false");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Infomation!");
				session.setAttribute("errorMsg", "false");
			}

		} catch (Exception e) {
			session.setAttribute("successMsg", "Error while updating!");
			session.setAttribute("errorMsg", "true");
			e.printStackTrace();
		}
		return "redirect:/sectionTreeList";

	}

	@RequestMapping(value = "/editTestImonial/{id}", method = RequestMethod.GET)
	public ModelAndView editTestImonial1(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editTestinomial");
		try {
			// System.out.println("id" + newsblogsId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			editTestImonial = Constant.getRestTemplate().postForObject(Constant.url + "/getTestinomialHeadDetById", map,
					TestImonial.class);
			// System.out.println("Page ID: " + editNewsBlog.getPageId());
			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editNewsBlog.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);
			model.addObject("detList", editTestImonial.getDetailList());
			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editTestImonial.getDetailList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editTestImonial.getDetailList().get(j).getLangId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					TestimonialDetail newsBlogDescription = new TestimonialDetail();
					newsBlogDescription.setLangId(languagesList.get(i).getLanguagesId());
					editTestImonial.getDetailList().add(newsBlogDescription);
				}
			}
			// ------------------------------------------------------------------------------------------------

			List<Integer> sectionIdList = new ArrayList<>();

			try {
				sectionIdList = Stream.of(editTestImonial.getExVar2().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());
			} catch (Exception e) {

			}

			List<Section> nonSelectedSection = new ArrayList<Section>();
			nonSelectedSection.addAll(sectionList);
			List<Section> selectedSection = new ArrayList<Section>();
			if (sectionIdList.size() > 0) {
				for (int i = 0; i < sectionIdList.size(); i++) {

					for (int j = 0; j < sectionList.size(); j++) {
						if (sectionList.get(j).getSectionId() == sectionIdList.get(i)) {
							selectedSection.add(sectionList.get(j));
							nonSelectedSection.remove(sectionList.get(j));
						}
					}

				}
			}
			model.addObject("nonSelectedSection", nonSelectedSection);
			model.addObject("selectedSection", selectedSection);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("isEdit", 1);
			model.addObject("moduleId", editTestImonial.getSectionId());
			// ------------------------------------------------------------------------------------------------------
			/// System.err.println("nonSelectedSection" + nonSelectedSection.toString());
			// System.err.println("selectedSection" + selectedSection.toString());

			model.addObject("languagesList", languagesList);
			model.addObject("editTestImonial", editTestImonial);
			model.addObject("page", page);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("pdfUrl", Constant.getCmsPdf);
			// model.addObject("isEdit", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertTestimonialEditForm", method = RequestMethod.POST)
	public String insertTestimonialEditForm(@RequestParam("images") List<MultipartFile> images,
			HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		HttpSession session = request.getSession();
		try {
//1 time
			User UserDetail = (User) session.getAttribute("UserDetail");
			int remove = Integer.parseInt(request.getParameter("removeImg"));
			// String formName = request.getParameter("form_name");
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int id = Integer.parseInt(request.getParameter("id"));
			int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int formType = Integer.parseInt(request.getParameter("formType"));

			String formName = null;
			String designation = null;
			String location = null;
			String message = null;

			//
			boolean ret = false;
			/*
			 * if (FormValidation.Validaton(request.getParameter("sortNo"), "") == true ||
			 * FormValidation.Validaton(request.getParameter("status"), "") == true ||
			 * FormValidation.Validaton(request.getParameter("form_name"), "") == true) {
			 * 
			 * ret = true; }
			 */
			int isActive = Integer.parseInt(request.getParameter("status"));
			int sortNo = Integer.parseInt(request.getParameter("sortNo"));
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			List<TestimonialDetail> newsBlogDescriptionList = new ArrayList<TestimonialDetail>();

			for (int i = 0; i < editTestImonial.getDetailList().size(); i++) {

				int lanId = editTestImonial.getDetailList().get(i).getLangId();
				formName = XssEscapeUtils
						.jsoupParse(request.getParameter("form_name" + languagesList.get(i).getLanguagesId()));

				if (lanId == 1) {
					designation = XssEscapeUtils
							.jsoupParse(request.getParameter("designation" + languagesList.get(i).getLanguagesId())
									.trim().replaceAll("[ ]{2,}", " "));
					location = XssEscapeUtils
							.jsoupParse(request.getParameter("location" + languagesList.get(i).getLanguagesId()).trim()
									.replaceAll("[ ]{2,}", " "));
					editTestImonial.setDesignation(designation);
					editTestImonial.setLocation(location);
					editTestImonial.setFromName(formName);

					if (formType == 1) {
						message = XssEscapeUtils
								.jsoupParse(request.getParameter("message" + languagesList.get(i).getLanguagesId())
										.trim().replaceAll("[ ]{2,}", " "));
						editTestImonial.setMessage(message);
					} else {
						message = XssEscapeUtils
								.jsoupParse(request.getParameter("message" + languagesList.get(i).getLanguagesId()));
						editTestImonial.setMessage(message);
					}
				}

				designation = XssEscapeUtils.jsoupParse(
						request.getParameter("designation" + editTestImonial.getDetailList().get(i).getLangId()).trim()
								.replaceAll("[ ]{2,}", " "));
				location = XssEscapeUtils.jsoupParse(
						request.getParameter("location" + editTestImonial.getDetailList().get(i).getLangId()).trim()
								.replaceAll("[ ]{2,}", " "));
				editTestImonial.getDetailList().get(i).setLangId(editTestImonial.getDetailList().get(i).getLangId());
				editTestImonial.getDetailList().get(i).setDesignation(designation);

				editTestImonial.getDetailList().get(i).setLocation(location);

				editTestImonial.getDetailList().get(i).setExInt1(formType);

				if (Integer.parseInt(request.getParameter("formType")) == 1) {
					message = XssEscapeUtils.jsoupParse(
							request.getParameter("message" + editTestImonial.getDetailList().get(i).getLangId()).trim()
									.replaceAll("[ ]{2,}", " "));
					editTestImonial.getDetailList().get(i).setMessage(message);
				} else {
					message = XssEscapeUtils.jsoupParse(
							request.getParameter("message" + editTestImonial.getDetailList().get(i).getLangId()));
					editTestImonial.getDetailList().get(i).setMessage(message);
				}

				editTestImonial.getDetailList().get(i).setAddDate(sf.format(date));
				editTestImonial.getDetailList().get(i).setAddedByUserId(UserDetail.getUserId());
				editTestImonial.getDetailList().get(i).setDelStatus(1);
				editTestImonial.getDetailList().get(i).setExInt2(1);
				editTestImonial.getDetailList().get(i).setExVar1("NA");
				editTestImonial.getDetailList().get(i).setExVar2("NA");
				editTestImonial.getDetailList().get(i).setFromName(formName);
				editTestImonial.getDetailList().get(i).setIsActive(1);
				editTestImonial.getDetailList().get(i).setTestHeadId(0);
				newsBlogDescriptionList.add(editTestImonial.getDetailList().get(i));
			}

			// System.out.println("detail lis==="+newsBlogDescriptionList.toString());

			VpsImageUpload upload = new VpsImageUpload();
			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {
					// System.out.println("in image null");
					try {

						if (remove == 1) {
							File files = new File(Constant.gallryImageURL + editTestImonial.getImageName());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(
										" File deleted  " + Constant.gallryImageURL + editTestImonial.getImageName());
							} else
								System.out.println(
										"doesn't exists  " + Constant.gallryImageURL + editTestImonial.getImageName());

							// System.out.println("Remove :" + remove);
							editTestImonial.setImageName("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName, Constant.values, 0,
								0, 0, 0, 0);
						editTestImonial.setImageName(imageName);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				if (id == 0) {
					// System.out.println(" add Id :" + id);
					editTestImonial.setAddedByUserId(UserDetail.getUserId());
				} else {
					// System.out.println("edit Id :" + id);
					editTestImonial.setEditByUserId(UserDetail.getUserId());
				}

				editTestImonial.setSectionId(moduleId);
				editTestImonial.setPageId(pageId);
				editTestImonial.setIsActive(isActive);
				editTestImonial.setDelStatus(1);
				editTestImonial.setAddDate(sf.format(date));
				editTestImonial.setSortNo(sortNo);
				// editTestImonial.setFromName(formName);
				editTestImonial.setDetailList(newsBlogDescriptionList);
				editTestImonial.setExInt1(formType);

				// System.out.println("textImonial" + editTestImonial);
				TestImonial res = Constant.getRestTemplate().postForObject(
						Constant.url + "/saveTestinomialsHeaderAndDetail", editTestImonial, TestImonial.class);

				if (res != null && isEdit == 0) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(pageId);
					pagesModule.setPrimaryKeyId(res.getId());
					pagesModule.setModuleId(moduleId);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					// System.out.println("res " + res);
				}
				if (res != null) {

					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {

					session.setAttribute("successMsg", "Failed to add Information!");
					session.setAttribute("errorMsg", "false");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Infomation!");
				session.setAttribute("errorMsg", "false");
			}

		} catch (Exception e) {
			session.setAttribute("successMsg", "Error while updating!");
			session.setAttribute("errorMsg", "true");
			e.printStackTrace();
		}
		return "redirect:/sectionTreeList";

	}

	@RequestMapping(value = "/editTestImonial1/{id}", method = RequestMethod.GET)
	public ModelAndView editTestImonial(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/textImonialForm");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			editTestImonial = Constant.getRestTemplate().postForObject(Constant.url + "/getTestImonialById", map,
					TestImonial.class);
			model.addObject("editTestImonial", editTestImonial);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("isEdit", 1);
			model.addObject("moduleId", editTestImonial.getSectionId());
			// model.addObject("url", Constant.bannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/successStoryList", method = RequestMethod.GET)
	public ModelAndView successStoryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/successStoryList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getSuccessStoryList", GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deletesuccessStory/{id}", method = RequestMethod.GET)
	public String deletesuccessStory(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteTestImonial", map, Info.class);

			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/successStoryList";
	}

	@RequestMapping(value = "/teamList", method = RequestMethod.GET)
	public ModelAndView teamList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/teamList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate().getForObject(Constant.url + "/getTeamList",
					GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteteamList/{id}", method = RequestMethod.GET)
	public String deleteteamList(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteTestImonial", map, Info.class);

			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/teamList";
	}

	@RequestMapping(value = "/testImonialList", method = RequestMethod.GET)
	public ModelAndView testImonialList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/testImonialList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getTestImonialList", GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteTestImonial/{id}", method = RequestMethod.GET)
	public String deleteTestImonial(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteTestImonial", map, Info.class);

			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/testImonialList";
	}

	@RequestMapping(value = "/NewsBlogForm/{pageId}", method = RequestMethod.GET)
	public ModelAndView NewsBlogForm(@PathVariable int pageId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/NewsBlogForm");
		try {

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);
			model.addObject("page", page);
			model.addObject("isEdit", 0);

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getNewsBlogsListByPageId", map, GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertNewsBlogForm", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String aligment = request.getParameter("header_top_alignment");
			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("page_order"));
			 String urlName = XssEscapeUtils.jsoupParse(request.getParameter("url_name").trim().replaceAll("[ ]{2,}", " "));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int onHomePage = Integer.parseInt(request.getParameter("onHomePage"));
			String[] itemShow = request.getParameterValues("sectionId");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < itemShow.length; i++) {
				sb = sb.append(itemShow[i] + ",");
			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);
			// System.out.println("items :" + items);
			Boolean ret = false;

			if (FormValidation.Validaton(urlName, "") == true
					|| FormValidation.Validaton(request.getParameter("status"), "") == true
					|| FormValidation.Validaton(request.getParameter("page_order"), "") == true || items.length() < 0) {

				ret = true;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			String text = page.getPageName();
			text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
			
			String heading1 = null;
            String pageDescription = null;
            String metaTitle = null;
            String metaDescription = null;
            String keyword = null;
            
			// System.out.println(text);

			// CMSPages cMSPages = new CMSPages();
			NewsBlog newsBlog = new NewsBlog();
			List<NewsBlogDescription> newsBlogDescriptionList = new ArrayList<NewsBlogDescription>();

			VpsImageUpload upload = new VpsImageUpload();

			for (int i = 0; i < languagesList.size(); i++) {
				heading1 = XssEscapeUtils.jsoupParse(request.getParameter("heading1" + languagesList.get(i).getLanguagesId())
                        .trim().replaceAll("[ ]{2,}", " "));
				 pageDescription = XssEscapeUtils.jsoupParseClean(
	                        request.getParameter("page_description1" + languagesList.get(i).getLanguagesId()).trim()
	                                .replaceAll("[ ]{2,}", " "));
	                metaTitle = XssEscapeUtils.jsoupParse(request.getParameter("meta_title1" + languagesList.get(i).getLanguagesId())
	                        .trim().replaceAll("[ ]{2,}", " "));
	                metaDescription = XssEscapeUtils.jsoupParse(
	                        request.getParameter("meta_description1" + languagesList.get(i).getLanguagesId()).trim()
	                        .replaceAll("[ ]{2,}", " "));
	                keyword = XssEscapeUtils.jsoupParse(
	                        request.getParameter("meta_keyword1" + languagesList.get(i).getLanguagesId()).trim()
	                        .replaceAll("[ ]{2,}", " "));
				
				if (FormValidation.Validaton(heading1,"") == true || FormValidation.Validaton(metaTitle,
								"") == true) {

					ret = true;
					break;
				}

                
                NewsBlogDescription newsBlogDescription = new NewsBlogDescription();
                newsBlogDescription.setLanguageId(languagesList.get(i).getLanguagesId());
                newsBlogDescription.setHeading(heading1);
                newsBlogDescription.setDescriptions(pageDescription);
                newsBlogDescription.setPageMetaTitle(metaTitle);
                newsBlogDescription.setPageMetaDescription(metaDescription);
                newsBlogDescription.setPageMetaKeyword(keyword);
                newsBlogDescription.setDateTransaction(sf.format(date));
                newsBlogDescription.setExInt1(onHomePage);
                newsBlogDescriptionList.add(newsBlogDescription);
			}

			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {

				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
								Constant.values, 0, 0, 0, 0, 0);
						if (info.isError() == false) {
							newsBlog.setFeaturedImage(imageName);
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
							newsBlog.setDownloadPdf(pdfName);
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				newsBlog.setAddedByUserId(UserDetail.getUserId());
				newsBlog.setPageId(pageId);
				newsBlog.setExVar1(text.trim().replaceAll("[ ]{2,}", " "));
				newsBlog.setNewsSourceUrlName(urlName.trim().replaceAll("[ ]{2,}", " "));
				newsBlog.setExInt1(9);

				newsBlog.setExVar2(items);
				newsBlog.setPageOrder(seqNo);
				newsBlog.setIsActive(isActive);
				newsBlog.setDelStatus(1);
				newsBlog.setAddDate(sf.format(date));
				newsBlog.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));
				newsBlog.setDetailList(newsBlogDescriptionList);

				// System.out.println("newsBlog" + newsBlog);
				NewsBlog res = Constant.getRestTemplate().postForObject(Constant.url + "/saveNewsBlogHeaderAndDetail",
						newsBlog, NewsBlog.class);

				if (res != null && res.getDetailList() != null && isEdit == 0) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(res.getPageId());
					pagesModule.setPrimaryKeyId(res.getNewsblogsId());
					pagesModule.setModuleId(9);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					// System.out.println("pagesModuleres " + pagesModuleres);
				}
				if (res != null) {

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/NewsBlogList";
	}

	@RequestMapping(value = "/submtEditNewsBlogForm", method = RequestMethod.POST)
	public String submtEditNewsBlogForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String aligment = request.getParameter("header_top_alignment");
			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("page_order"));
			String urlName = XssEscapeUtils.jsoupParse(request.getParameter("url_name").trim().replaceAll("[ ]{2,}", " "));
			int remove = Integer.parseInt(request.getParameter("removeImg"));
			int removePdf = Integer.parseInt(request.getParameter("removePdf"));
			// int pageId = Integer.parseInt(request.getParameter("pageId"));
			String[] itemShow = request.getParameterValues("sectionId");
			int onHomePage = Integer.parseInt(request.getParameter("onHomePage"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));

			  	String heading1 = null;
	            String pageDescription = null;
	            String metaTitle = null;
	            String metaDescription = null;
	            String keyword = null;

	            
			// System.out.println("onHomePage :" + onHomePage);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < itemShow.length; i++) {
				sb = sb.append(itemShow[i] + ",");
			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			Boolean ret = false;

			if (FormValidation.Validaton(urlName, "") == true
					|| FormValidation.Validaton(request.getParameter("status"), "") == true
					|| FormValidation.Validaton(request.getParameter("page_order"), "") == true || items.length() < 0) {

				ret = true;
			}

			String pageName = request.getParameter("page_name");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			/*
			 * String slug=sf.format(date); // String text = pageName; String text =
			 * pageName.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
			 * //System.out.println(text);
			 */

			String text = page.getPageName();
			text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
			VpsImageUpload upload = new VpsImageUpload();

			for (int i = 0; i < editNewsBlog.getDetailList().size(); i++) {
				
				  heading1 = XssEscapeUtils.jsoupParse(request.getParameter("heading1" + editNewsBlog.getDetailList().get(i).getLanguageId()).trim()
	                        .replaceAll("[ ]{2,}", " "));
	                metaTitle = XssEscapeUtils.jsoupParse(request.getParameter("meta_title1" + editNewsBlog.getDetailList().get(i).getLanguageId()).trim()
	                        .replaceAll("[ ]{2,}", " "));               
	                pageDescription = request.getParameter("page_description1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " ");
	                metaDescription = request.getParameter("meta_description1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " ");
	                keyword = request
	                        .getParameter("meta_keyword1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " ");

	                if (FormValidation.Validaton(heading1,"") == true || FormValidation.Validaton(metaTitle,"") == true) {
	                    ret = true;
	                    break;
	                }

	                editNewsBlog.getDetailList().get(i).setHeading(heading1);
	                editNewsBlog.getDetailList().get(i).setDescriptions(pageDescription);
	                editNewsBlog.getDetailList().get(i).setPageMetaTitle(metaTitle);
	                editNewsBlog.getDetailList().get(i).setPageMetaDescription(metaDescription);
	                editNewsBlog.getDetailList().get(i).setPageMetaKeyword(keyword);
	                editNewsBlog.getDetailList().get(i).setExInt1(onHomePage);
			}

			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {
					// System.out.println("in image null");
					try {

						if (remove == 1) {
							File files = new File(Constant.gallryImageURL + editNewsBlog.getFeaturedImage());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(
										" File deleted  " + Constant.gallryImageURL + editNewsBlog.getFeaturedImage());
							} else
								System.out.println(
										"doesn't exists  " + Constant.gallryImageURL + editNewsBlog.getFeaturedImage());

							// System.out.println("Remove :" + remove);
							editNewsBlog.setFeaturedImage("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
								Constant.values, 0, 0, 0, 0, 0);
						if (info.isError() == false) {
							editNewsBlog.setFeaturedImage(imageName);
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				if (pagePdf.get(0).getOriginalFilename() == null || pagePdf.get(0).getOriginalFilename() == "") {
					try {
						if (removePdf == 1) {
							File files = new File(Constant.cmsPdf + editNewsBlog.getDownloadPdf());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(" File deleted  " + Constant.cmsPdf + editNewsBlog.getDownloadPdf());
							} else
								System.out
										.println("doesn't exists  " + Constant.cmsPdf + editNewsBlog.getDownloadPdf());

							// System.out.println("Remove :" + removePdf);
							editNewsBlog.setDownloadPdf("");
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
							editNewsBlog.setDownloadPdf(pdfName);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}
				editNewsBlog.setExVar2(items);
				editNewsBlog.setEditByUserId(UserDetail.getUserId());
				editNewsBlog.setNewsSourceUrlName(urlName);
				editNewsBlog.setExInt1(9);
				editNewsBlog.setExInt2(onHomePage);
				editNewsBlog.setExVar1(text.trim().replaceAll("[ ]{2,}", " "));
				editNewsBlog.setPageOrder(seqNo);
				editNewsBlog.setIsActive(isActive);
				editNewsBlog.setEditDate(sf.format(date));
				editNewsBlog.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));

				// System.out.println("editNewsBlog" + editNewsBlog);
				NewsBlog res = Constant.getRestTemplate().postForObject(Constant.url + "/saveNewsBlogHeaderAndDetail",
						editNewsBlog, NewsBlog.class);
				if (res != null) {

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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/NewsBlogList";
	}

	@RequestMapping(value = "/NewsBlogList", method = RequestMethod.GET)
	public ModelAndView newsBlogList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/NewsBlogList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate().getForObject(Constant.url + "/getNewsBlogList",
					GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editNewsBlogContent/{newsblogsId}", method = RequestMethod.GET)
	public ModelAndView editNewsBlogContent(@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editNewsContent");
		try {
			// System.out.println("id" + newsblogsId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("newsblogsId", newsblogsId);

			editNewsBlog = Constant.getRestTemplate().postForObject(Constant.url + "/getNewsPagebyId", map,
					NewsBlog.class);
			// System.out.println("Page ID: " + editNewsBlog.getPageId());
			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editNewsBlog.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editNewsBlog.getDetailList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editNewsBlog.getDetailList().get(j).getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					NewsBlogDescription newsBlogDescription = new NewsBlogDescription();
					newsBlogDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editNewsBlog.getDetailList().add(newsBlogDescription);
				}
			}
			// ------------------------------------------------------------------------------------------------

			List<Integer> sectionIdList = new ArrayList<>();

			try {
				sectionIdList = Stream.of(editNewsBlog.getExVar2().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());
			} catch (Exception e) {

			}

			List<Section> nonSelectedSection = new ArrayList<Section>();
			nonSelectedSection.addAll(sectionList);
			List<Section> selectedSection = new ArrayList<Section>();
			if (sectionIdList.size() > 0) {
				for (int i = 0; i < sectionIdList.size(); i++) {

					for (int j = 0; j < sectionList.size(); j++) {
						if (sectionList.get(j).getSectionId() == sectionIdList.get(i)) {
							selectedSection.add(sectionList.get(j));
							nonSelectedSection.remove(sectionList.get(j));
						}
					}

				}
			}
			model.addObject("nonSelectedSection", nonSelectedSection);
			model.addObject("selectedSection", selectedSection);
			// ------------------------------------------------------------------------------------------------------
			/// System.err.println("nonSelectedSection" + nonSelectedSection.toString());
			// System.err.println("selectedSection" + selectedSection.toString());

			model.addObject("languagesList", languagesList);
			model.addObject("editNewsBlog", editNewsBlog);
			model.addObject("page", page);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("pdfUrl", Constant.getCmsPdf);
			// model.addObject("isEdit", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteNewsBlogContent/{newsblogsId}", method = RequestMethod.GET)
	public String deleteNewsBlogContent(@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("newsblogsId", newsblogsId);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteNewsBlogContent", map,
					Info.class);

			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/NewsBlogList";
	}

	@RequestMapping(value = "/EventForm/{pageId}", method = RequestMethod.GET)
	public ModelAndView EventForm(@PathVariable int pageId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/EventsForm");
		try {

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			model.addObject("page", page);
			model.addObject("isEdit", 0);

			GetPagesModule[] getPagesModule = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getEventListByNewsblogsId", map, GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));
			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertEventForm", method = RequestMethod.POST)
	public String insertEventForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String location = XssEscapeUtils.jsoupParse(request.getParameter("event_loc").trim().replaceAll("[ ]{2,}", " "));
			String eventDate = XssEscapeUtils.jsoupParse(request.getParameter("from_date"));
			String aligment = request.getParameter("header_top_alignment");
			String personName = XssEscapeUtils.jsoupParse(request.getParameter("per_name").trim().replaceAll("[ ]{2,}", " "));
			String eventNo = XssEscapeUtils.jsoupParse(request.getParameter("event_no").trim().replaceAll("[ ]{2,}", " "));
			String docType = XssEscapeUtils.jsoupParse(request.getParameter("docType"));

			int isActive = Integer.parseInt(request.getParameter("status"));
			int seqNo = Integer.parseInt(request.getParameter("page_order"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int doc = Integer.parseInt(request.getParameter("doc"));

			Boolean ret = false;

			if (FormValidation.Validaton(eventDate, "") == true
					|| FormValidation.Validaton(location, "") == true
					|| FormValidation.Validaton(personName, "") == true
					|| FormValidation.Validaton(eventNo, "") == true
					|| FormValidation.Validaton(request.getParameter("page_order"), "") == true
					|| FormValidation.Validaton(request.getParameter("status"), "") == true) {

				ret = true;
			}

			/*
			 * String individual = request.getParameter("individual"); String college =
			 * request.getParameter("college"); String university =
			 * request.getParameter("university");
			 */

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			NewsBlog newsBlog = new NewsBlog();
			List<NewsBlogDescription> newsBlogDescriptionList = new ArrayList<NewsBlogDescription>();

			VpsImageUpload upload = new VpsImageUpload();
			
			String heading1 = null;
			String content = null;
			String metaTitle = null;
			String metaDescription = null;
			String keyword = null;

			for (int i = 0; i < languagesList.size(); i++) {
				heading1 = XssEscapeUtils.jsoupParse(request.getParameter("heading1" + languagesList.get(i).getLanguagesId())
						.trim().replaceAll("[ ]{2,}", " "));
				content = XssEscapeUtils.jsoupParseClean(request.getParameter("page_description1" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				metaTitle = XssEscapeUtils.jsoupParse(request.getParameter("meta_title1" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));
				metaDescription = XssEscapeUtils.jsoupParse(
						request.getParameter("meta_description1" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				keyword = XssEscapeUtils.jsoupParse(
						request.getParameter("meta_keyword1" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				if (FormValidation.Validaton(heading1,"") == true) {

					ret = true;
					break;
				}

				NewsBlogDescription newsBlogDescription = new NewsBlogDescription();
				newsBlogDescription.setLanguageId(languagesList.get(i).getLanguagesId());
				newsBlogDescription.setHeading(heading1);
				newsBlogDescription.setDescriptions(content);
				newsBlogDescription.setPageMetaTitle(metaTitle);
				newsBlogDescription.setPageMetaDescription(metaDescription);
				newsBlogDescription.setPageMetaKeyword(keyword);
				newsBlogDescription.setDateTransaction(sf.format(date));
				newsBlogDescriptionList.add(newsBlogDescription);
			}

			// cMSPages.setAddedByUserId(UserDetail.getUserId());
			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {

				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();
					// System.out.println("date" + imageName);
					try {
						Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
								Constant.values, 0, 0, 0, 0, 0);
						if (info.isError() == false) {
							newsBlog.setFeaturedImage(imageName);
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
							newsBlog.setDownloadPdf(pdfName);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}
				String[] itemShow = request.getParameterValues("type");
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < itemShow.length; i++) {
					sb = sb.append(itemShow[i] + ",");
				}
				String items = sb.toString();
				items = items.substring(0, items.length() - 1);

				// System.out.println("type: " + items);
				newsBlog.setAddedByUserId(UserDetail.getUserId());
				newsBlog.setPageId(pageId);
				newsBlog.setEventContactNumber(eventNo);
				newsBlog.setEventContactPerson(personName);
				newsBlog.setEventDateFrom(DateConvertor.convertToYMD(eventDate));
				newsBlog.setEventLocation(location);
				newsBlog.setPageOrder(seqNo);
				newsBlog.setIsActive(isActive);
				newsBlog.setExInt1(11);
				newsBlog.setExInt2(doc);
				newsBlog.setExVar2(items);
				newsBlog.setExVar1(docType);
				newsBlog.setDelStatus(1);
				newsBlog.setAddDate(sf.format(date));
				newsBlog.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));
				newsBlog.setDetailList(newsBlogDescriptionList);

				// System.out.println("newsBlog" + newsBlog);
				NewsBlog res = Constant.getRestTemplate().postForObject(Constant.url + "/saveNewsBlogHeaderAndDetail",
						newsBlog, NewsBlog.class);

				if (res != null && res.getDetailList() != null && isEdit == 0) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(res.getPageId());
					pagesModule.setPrimaryKeyId(res.getNewsblogsId());
					pagesModule.setModuleId(11);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					// System.out.println("res " + res);
				}
				if (res != null) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {

					session.setAttribute("successMsg", "Failed To Insert information !");
					session.setAttribute("errorMsg", "true");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Information!");
				session.setAttribute("errorMsg", "true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionTreeList";
	}

	@RequestMapping(value = "/submtEditEventForm", method = RequestMethod.POST)
	public String submtEditEventForm(@RequestParam("images") List<MultipartFile> images,
			@RequestParam("pagePdf") List<MultipartFile> pagePdf, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			  String location = XssEscapeUtils.jsoupParse(request.getParameter("event_loc").trim().replaceAll("[ ]{2,}", " "));
	            String eventDate = XssEscapeUtils.jsoupParse(request.getParameter("from_date"));
	            String aligment = request.getParameter("header_top_alignment");
	            String personName = XssEscapeUtils.jsoupParse(request.getParameter("per_name").trim().replaceAll("[ ]{2,}", " "));
	            String eventNo = XssEscapeUtils.jsoupParse(request.getParameter("event_no").trim().replaceAll("[ ]{2,}", " "));
	            int isActive = Integer.parseInt(request.getParameter("status"));
	            int seqNo = Integer.parseInt(request.getParameter("page_order"));
	            String urlName = request.getParameter("url_name");
	            int doc = Integer.parseInt(request.getParameter("doc"));

			// int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));

			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("from_date"), "") == true
					|| FormValidation.Validaton(request.getParameter("event_loc"), "") == true
					|| FormValidation.Validaton(request.getParameter("per_name"), "") == true
					|| FormValidation.Validaton(request.getParameter("event_no"), "") == true
					|| FormValidation.Validaton(request.getParameter("page_order"), "") == true
					|| FormValidation.Validaton(request.getParameter("event_no"), "") == true
					|| FormValidation.Validaton(request.getParameter("status"), "") == true) {

				ret = true;
			}

			int remove = Integer.parseInt(request.getParameter("removeImg"));
			int removePdf = Integer.parseInt(request.getParameter("removePdf"));
			String docType = request.getParameter("docType");

			String pageName = request.getParameter("page_name");

			Date date = new Date();
			// SimpleDateFormat sf1 = new SimpleDateFormat(eventDate);
			SimpleDateFormat dateTimeInGMT2 = new SimpleDateFormat(eventDate + "_HH:mm:ss");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			VpsImageUpload upload = new VpsImageUpload();
			
			String heading1 = null;
            String content = null;
            String metaTitle = null;
            String metaDescription = null;
            String keyword = null;

			for (int i = 0; i < editNewsBlog.getDetailList().size(); i++) {
				heading1 = XssEscapeUtils.jsoupParse(request.getParameter("heading1" + editNewsBlog.getDetailList().get(i).getLanguageId()).trim()
						.replaceAll("[ ]{2,}", " "));
				content = XssEscapeUtils.jsoupParseClean(request.getParameter("page_description1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " "));
	            metaTitle = XssEscapeUtils.jsoupParse(request.getParameter("meta_title1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " "));
	            metaDescription = XssEscapeUtils.jsoupParse(request.getParameter("meta_description1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " "));
	            keyword = XssEscapeUtils.jsoupParse(request.getParameter("meta_keyword1" + editNewsBlog.getDetailList().get(i).getLanguageId())
	                        .trim().replaceAll("[ ]{2,}", " "));

				if (FormValidation.Validaton(heading1,"") == true) {

					ret = true;
					break;
				}
				editNewsBlog.getDetailList().get(i).setHeading(heading1);
                editNewsBlog.getDetailList().get(i).setDescriptions(content);
                editNewsBlog.getDetailList().get(i).setPageMetaTitle(metaTitle);
                editNewsBlog.getDetailList().get(i).setPageMetaDescription(metaDescription);
                editNewsBlog.getDetailList().get(i).setPageMetaKeyword(keyword);
                editNewsBlog.getDetailList().get(i).setDateTransaction(sf.format(date));
			}

			if (ret == false) {
				if (images.get(0).getOriginalFilename() == null || images.get(0).getOriginalFilename() == "") {
					// System.out.println("in image null");
					try {

						if (remove == 1) {
							File files = new File(Constant.gallryImageURL + editNewsBlog.getFeaturedImage());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(
										" File deleted  " + Constant.gallryImageURL + editNewsBlog.getFeaturedImage());
							} else
								System.out.println(
										"doesn't exists  " + Constant.gallryImageURL + editNewsBlog.getFeaturedImage());

							System.out.println("Remove :" + remove);
							editNewsBlog.setFeaturedImage("");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {

					String imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + images.get(0).getOriginalFilename();

					try {
						Info info = upload.saveUploadedImge(images.get(0), Constant.gallryImageURL, imageName,
								Constant.values, 0, 0, 0, 0, 0);
						if (info.isError() == false) {
							editNewsBlog.setFeaturedImage(imageName);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				if (pagePdf.get(0).getOriginalFilename() == null || pagePdf.get(0).getOriginalFilename() == "") {
					try {
						if (removePdf == 1) {
							File files = new File(Constant.cmsPdf + editNewsBlog.getDownloadPdf());
							// File files = new
							// File("/home/lenovo/Downloads/apache-tomcat-8.5.37/webapps/media/other/2019-02-16_17:08:37_download
							// (1).jpeg");

							if (files.delete()) {
								System.out.println(" File deleted  " + Constant.cmsPdf + editNewsBlog.getDownloadPdf());
							} else
								System.out
										.println("doesn't exists  " + Constant.cmsPdf + editNewsBlog.getDownloadPdf());

							System.out.println("Remove :" + removePdf);
							editNewsBlog.setDownloadPdf("");
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
							editNewsBlog.setDownloadPdf(pdfName);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}
				String[] itemShow = request.getParameterValues("type");
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < itemShow.length; i++) {
					sb = sb.append(itemShow[i] + ",");
				}
				String items = sb.toString();
				items = items.substring(0, items.length() - 1);

				editNewsBlog.setEditByUserId(UserDetail.getUserId());
				editNewsBlog.setEventContactNumber(eventNo);
				editNewsBlog.setEventContactPerson(personName);
				editNewsBlog.setEventDateFrom(DateConvertor.convertToYMD(eventDate));
				editNewsBlog.setEventLocation(location);
				// editNewsBlog.setNewsSourceUrlName(urlName.trim().replaceAll("[ ]{2,}", " "));
				editNewsBlog.setExInt1(11);
				editNewsBlog.setExInt2(doc);
				if (doc == 1) {
					editNewsBlog.setExVar1(docType);
				} else {
					editNewsBlog.setExVar1("");
				}
				editNewsBlog.setExVar2(items);
				editNewsBlog.setPageOrder(seqNo);
				editNewsBlog.setIsActive(isActive);
				editNewsBlog.setEditDate(sf.format(date));
				editNewsBlog.setFeaturedImageAlignment(aligment.trim().replaceAll("[ ]{2,}", " "));

				// System.out.println("editNewsBlog" + editNewsBlog);
				NewsBlog res = Constant.getRestTemplate().postForObject(Constant.url + "/saveNewsBlogHeaderAndDetail",
						editNewsBlog, NewsBlog.class);

				session.setAttribute("successMsg", "Infomation Updated successfully!");

				if (res.getNewsblogsId() != 0) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");

				} else {
					session.setAttribute("successMsg", "Failed to Add Information!");
					session.setAttribute("errorMsg", "true");

				}
			} else {
				session.setAttribute("successMsg", "Invalid Information!");
				session.setAttribute("errorMsg", "true");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/EventFormList";
	}

	@RequestMapping(value = "/EventFormList", method = RequestMethod.GET)
	public ModelAndView EventFormList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/EventsList");
		try {

			GetPagesModule[] getPagesModule = Constant.getRestTemplate().getForObject(Constant.url + "/getEventList",
					GetPagesModule[].class);

			List<GetPagesModule> getPagesModuleList = new ArrayList<GetPagesModule>(Arrays.asList(getPagesModule));

			model.addObject("getPagesModuleList", getPagesModuleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editEventContent/{newsblogsId}", method = RequestMethod.GET)
	public ModelAndView editEventContent(@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editEventContent");
		try {
			// System.out.println("id" + newsblogsId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("newsblogsId", newsblogsId);

			editNewsBlog = Constant.getRestTemplate().postForObject(Constant.url + "/getNewsPagebyId", map,
					NewsBlog.class);
			// System.out.println("Page ID: " + editNewsBlog.getPageId());
			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editNewsBlog.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			/*
			 * Section[] section = Constant.getRestTemplate().getForObject(Constant.url +
			 * "/getAllSectionList", Section[].class); List<Section> sectionList = new
			 * ArrayList<Section>(Arrays.asList(section)); model.addObject("sectionList",
			 * sectionList);
			 */

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editNewsBlog.getDetailList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editNewsBlog.getDetailList().get(j).getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					NewsBlogDescription newsBlogDescription = new NewsBlogDescription();
					newsBlogDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editNewsBlog.getDetailList().add(newsBlogDescription);
				}
			}

			/*
			 * List<Integer> sectionIdList=
			 * Stream.of(editNewsBlog.getExVar2().split(",")).map(Integer::parseInt)
			 * .collect(Collectors.toList());
			 */

			model.addObject("languagesList", languagesList);
			model.addObject("eventDate", DateConvertor.convertToDMY(editNewsBlog.getEventDateFrom()));
			model.addObject("editNewsBlog", editNewsBlog);
			model.addObject("page", page);
			model.addObject("url", Constant.getGallryImageURL);
			model.addObject("pdfUrl", Constant.getCmsPdf);
			model.addObject("isEdit", 1);

			String[] array = editNewsBlog.getExVar2().split(",");
			model.addObject("array", array);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteEventContent/{newsblogsId}", method = RequestMethod.GET)
	public String deleteEventContent(@PathVariable("newsblogsId") int newsblogsId, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("newsblogsId", newsblogsId);
			Info info = Constant.getRestTemplate().postForObject(Constant.url + "/deleteNewsBlogContent", map,
					Info.class);

			HttpSession session = request.getSession();

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Infomation Delete successfully!");
			} else {
				session.setAttribute("successMsg", "Error while Deleting !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/EventFormList";
	}

	@RequestMapping(value = "/videoForm/{pageId}", method = RequestMethod.GET)
	public ModelAndView videoForm(@PathVariable int pageId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/vedioForm");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", pageId);
			page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);
			// System.out.println("Page List: " + page.toString());
			model.addObject("page", page);
			model.addObject("isEdit", 0);
			map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GallaryCategory[] category = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getGalleryCategoryList", GallaryCategory[].class);
			List<GallaryCategory> categoryList = new ArrayList<GallaryCategory>(Arrays.asList(category));
			// System.out.println("" + categoryList.toString());
			model.addObject("categoryList", categoryList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertVedioForm", method = RequestMethod.POST)
	public String insertVedioForm(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String catId = request.getParameter("cateId");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			String titleName = XssEscapeUtils
					.jsoupParse(request.getParameter("titleName").trim().replaceAll("[ ]{2,}", " "));
			int vedio_url = Integer.parseInt(request.getParameter("vedio_url"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));

			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("cateId"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(titleName, "") == true) {

				ret = true;
			}

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			// CMSPages cMSPages = new CMSPages();
			GallaryDetail gallaryDetail = new GallaryDetail();

			if (vedio_url == 0) {
				String vedioUrl = request.getParameter("vedioUrl");
				gallaryDetail.setFileName(vedioUrl.trim().replaceAll("[ ]{2,}", " "));
			} else {
				String vedioCode = XssEscapeUtils
						.jsoupParse(request.getParameter("vedioCode").trim().replaceAll("[ ]{2,}", " "));
				gallaryDetail.setFileName(vedioCode);
			}
			gallaryDetail.setPageId(pageId);
			gallaryDetail.setTypeVideoImage("4");

			gallaryDetail.setTitle(titleName);
			gallaryDetail.setGalleryCatId(Integer.parseInt(catId));
			gallaryDetail.setIsActive(isActive);
			gallaryDetail.setDelStatus(1);
			gallaryDetail.setAddDate(sf.format(date));
			gallaryDetail.setAddedByUserId(UserDetail.getUserId());
			// gallaryDetail.setFeaturedImageAlignment(aligment);
			if (ret == false) {
				GallaryDetail res = Constant.getRestTemplate().postForObject(Constant.url + "/saveGalleryDetails",
						gallaryDetail, GallaryDetail.class);

				if (res != null && isEdit == 0) {

					PagesModule pagesModule = new PagesModule();

					pagesModule.setPageId(res.getPageId());
					pagesModule.setPrimaryKeyId(res.getGalleryDetailsId());
					pagesModule.setModuleId(4);
					PagesModule pagesModuleres = Constant.getRestTemplate()
							.postForObject(Constant.url + "/savePagesModules", pagesModule, PagesModule.class);
					/// System.out.println("pagesModuleres " + pagesModuleres);
				}
				if (res != null) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {

					session.setAttribute("successMsg", "Failed To Insert Information!");
					session.setAttribute("errorMsg", "true");
				}
			} else {
				session.setAttribute("successMsg", "Invalid Information !");
				session.setAttribute("errorMsg", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/VedioFormList";
	}

	@RequestMapping(value = "/submitVedioForm", method = RequestMethod.POST)
	public String submitVedioForm(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String catId = request.getParameter("cateId");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			String titleName = XssEscapeUtils.jsoupParse(request.getParameter("titleName").trim().replaceAll("[ ]{2,}", " "));
			int vedio_url = Integer.parseInt(request.getParameter("vedio_url"));
			// int
			// galleryDetailId=Integer.parseInt(request.getParameter("galleryDetailId"));

			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("cateId"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(titleName, "") == true) {

				ret = true;
			}
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			// CMSPages cMSPages = new CMSPages();
			// GallaryDetail gallaryDetail=new GallaryDetail();

			if (vedio_url == 0) {
				String vedioUrl = request.getParameter("vedioUrl");
				editGalleryDetail.setFileName(vedioUrl.trim().replaceAll("[ ]{2,}", " "));
			} else {
				String vedioCode = XssEscapeUtils.jsoupParse(request.getParameter("vedioCode").trim().replaceAll("[ ]{2,}", " "));
				editGalleryDetail.setFileName(vedioCode);
			}
			editGalleryDetail.setPageId(pageId);
			editGalleryDetail.setTypeVideoImage("4");

			editGalleryDetail.setTitle(titleName);
			editGalleryDetail.setGalleryCatId(Integer.parseInt(catId));
			editGalleryDetail.setIsActive(isActive);
			editGalleryDetail.setDelStatus(1);
			editGalleryDetail.setEditDate(sf.format(date));
			// gallaryDetail.setFeaturedImageAlignment(aligment);

			// System.out.println("gallaryDetail" + editGalleryDetail);
			if (ret == false) {
				GallaryDetail res = Constant.getRestTemplate().postForObject(Constant.url + "/saveGalleryDetails",
						editGalleryDetail, GallaryDetail.class);
				// System.out.println("List : " + res.toString());
				if (res.getGalleryDetailsId() != 0) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");
				} else {
					session.setAttribute("successMsg", "Failed To Add Infomation!");
					session.setAttribute("errorMsg", "true");
				}

			} else {
				session.setAttribute("successMsg", "Invalid Infomation!");
				session.setAttribute("errorMsg", "true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/VedioFormList";
	}

	@RequestMapping(value = "/VedioFormList", method = RequestMethod.GET)
	public ModelAndView VedioFormList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/vedioGalleryList");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("moduleId", 4);
			GallaryDetail[] gallaryDetail = Constant.getRestTemplate()
					.postForObject(Constant.url + "/galleryDetailList", map, GallaryDetail[].class);
			List<GallaryDetail> gallaryDetailList = new ArrayList<GallaryDetail>(Arrays.asList(gallaryDetail));
			model.addObject("gallaryDetailList", gallaryDetailList);
			model.addObject("imageUrl", Constant.getGallryImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editVideoContent/{galleryDetailsId}", method = RequestMethod.GET)
	public ModelAndView editVideoContent(@PathVariable("galleryDetailsId") int galleryDetailsId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("moduleForms/editVideoGallery");
		try {
			// System.out.println("id" + galleryDetailsId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryDetailsId", galleryDetailsId);

			editGalleryDetail = Constant.getRestTemplate().postForObject(Constant.url + "/getListByGalleryId", map,
					GallaryDetail.class);
			// System.out.println("Page ID: " + editNewsBlog.getPageId());

			map = new LinkedMultiValueMap<String, Object>();
			map.add("pageId", editGalleryDetail.getPageId());
			Page page = Constant.getRestTemplate().postForObject(Constant.url + "/getPageByPageId", map, Page.class);

			// System.out.println("List : " + editNewsBlog.toString());
			GallaryCategory[] category = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getGalleryCategoryList", GallaryCategory[].class);
			List<GallaryCategory> categoryList = new ArrayList<GallaryCategory>(Arrays.asList(category));
			// System.out.println("" + categoryList.toString());
			// editNewsBlog.setAddDate(DateConvertor.convertToDMY(editNewsBlog.getEventDateFrom()));
			model.addObject("editGalleryDetail", editGalleryDetail);
			model.addObject("page", page);
			model.addObject("categoryList", categoryList);
			model.addObject("isEdit", 1);
			// model.addObject("url", Constant.getGallryImageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
	/*
	 * @RequestMapping(value = "/updateTitleName/{id}/{title}", method =
	 * RequestMethod.GET) public String updateTitleName(@PathVariable("id") int
	 * id,@PathVariable("title") String title, HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * 
	 * try { System.out.println(id+""+title); MultiValueMap<String, Object> map =
	 * new LinkedMultiValueMap<String, Object>(); map.add("galleryDetailsId", id);
	 * map.add("title", title); Info info =
	 * Constant.getRestTemplate().postForObject(Constant.url + "/updateTitleName",
	 * map, Info.class);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return "redirect:/uploadedImageList";
	 * 
	 * 
	 * }
	 */

	@RequestMapping(value = "/deleteVideoGallery/{galleryDetailsId}", method = RequestMethod.GET)
	public String deleteVideoGallary(@PathVariable int galleryDetailsId, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryDetailsId", galleryDetailsId);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteGalleryDetails", map,
					Info.class);
			// System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/VedioFormList";
	}

}
