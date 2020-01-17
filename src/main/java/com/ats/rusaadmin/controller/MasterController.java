package com.ats.rusaadmin.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusaadmin.XssEscapeUtils;
import com.ats.rusaadmin.common.Constant;
import com.ats.rusaadmin.common.DateConvertor;
import com.ats.rusaadmin.common.FormValidation;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.Category;
import com.ats.rusaadmin.model.CategoryDescription;
import com.ats.rusaadmin.model.GalleryDetail;
import com.ats.rusaadmin.model.Galleryheader;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.GetGalleryHeaderByCatId;
import com.ats.rusaadmin.model.GetSubCategory;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.SectionTree;
import com.ats.rusaadmin.model.SubCategory;
import com.ats.rusaadmin.model.User;

@Controller
@Scope("session")
public class MasterController {

	RestTemplate rest = new RestTemplate();
	GetCategory editcat = new GetCategory();
	Section editSection = new Section();
	GetSubCategory editSubCategory = new GetSubCategory();
	Galleryheader editGalleryheader = new Galleryheader();
	List<Languages> languagesList = new ArrayList<Languages>();

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addCategory");
		try {
			editcat = new GetCategory();
			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/sendSms", method = RequestMethod.GET)
	public @ResponseBody String sendSms(HttpServletRequest request, HttpServletResponse response) {

		try {

			System.out.println("start");
			String genratedhashKey = hashGenerator("rusamah-wb", "MHRUSA", "HIII",
					"5f838260-4d10-4c4b-bf88-3350fbf26799");

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("username", "rusamah-wb");
			map.add("password", "Rus@@123456");
			map.add("senderid", "MHRUSA");
			map.add("mobileno", "7588519473");
			map.add("content", "HIII");
			map.add("key", "5f838260-4d10-4c4b-bf88-3350fbf26799");
			map.add("smsservicetype", "unicodeotpmsg");
			map.add("hashValue", genratedhashKey);
			 //
			// http://msdgweb.mgov.gov.in/esms/sendsmsrequest?username=rusamah-wb&password=Rus@@123456&senderid=MHRUSA&smsservicetype=singlemsg&mobileno=9422945125&content=hello

			String responses = restTemplate.postForObject("http://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
					String.class);

			System.out.println("end" + responses);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
		// TODO Auto-generated method stub
		StringBuffer finalString = new StringBuffer();
		finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
		String hashGen = finalString.toString();
		StringBuffer sb = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(hashGen.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public ModelAndView categoryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/categoryList");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GetCategory[] getCategory = Constant.getRestTemplate().postForObject(Constant.url + "/getAllCatList", map,
					GetCategory[].class);
			List<GetCategory> categoryList = new ArrayList<GetCategory>(Arrays.asList(getCategory));
			model.addObject("categoryList", categoryList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public String insertCategory(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String catId = request.getParameter("catId");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			int sectionId = Integer.parseInt(request.getParameter("sectionId"));
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));

			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("sectionId"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(request.getParameter("seqNo"), "") == true) {

				ret = true;
			}

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			List<CategoryDescription> categoryDescriptionList = new ArrayList<CategoryDescription>();

			if (catId == "" || catId == null) {
				editcat.setCatId(0);
				editcat.setCatAddDate(sf.format(date));

				for (int i = 0; i < languagesList.size(); i++) {

					if (FormValidation.Validaton(
							request.getParameter("catName" + languagesList.get(i).getLanguagesId()), "") == true) {

						ret = true;
						break;
					}

					CategoryDescription categoryDescription = new CategoryDescription();
					categoryDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					categoryDescription
							.setCatName(request.getParameter("catName" + languagesList.get(i).getLanguagesId()).trim()
									.replaceAll("[ ]{2,}", " "));
					categoryDescription
							.setCatDesc(request.getParameter("catDesc" + languagesList.get(i).getLanguagesId()).trim()
									.replaceAll("[ ]{2,}", " "));

					if (languagesList.get(i).getLanguagesId() == 1) {

						editcat.setCatName(request.getParameter("catName" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));
						editcat.setCatDesc(request.getParameter("catDesc" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						System.out.println(text);
						editcat.setSlugName(text);
					}
					categoryDescriptionList.add(categoryDescription);
				}
				editcat.setCategoryDescriptionList(categoryDescriptionList);
			} else {

				editcat.setCatId(Integer.parseInt(catId));
				editcat.setCatEditDate(sf.format(date));
				editcat.setCatAddDate(DateConvertor.convertToYMD(editcat.getCatAddDate()));
				for (int i = 0; i < editcat.getCategoryDescriptionList().size(); i++) {

					if (FormValidation.Validaton(
							request.getParameter(
									"catName" + editcat.getCategoryDescriptionList().get(i).getLanguageId()),
							"") == true) {

						ret = true;
						break;
					}

					editcat.getCategoryDescriptionList().get(i).setCatName(request
							.getParameter("catName" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
							.trim().replaceAll("[ ]{2,}", " "));
					editcat.getCategoryDescriptionList().get(i).setCatDesc(request
							.getParameter("catDesc" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
							.trim().replaceAll("[ ]{2,}", " "));

					if (editcat.getCategoryDescriptionList().get(i).getLanguageId() == 1) {

						editcat.setCatName(request
								.getParameter("catName" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
								.trim().replaceAll("[ ]{2,}", " "));
						editcat.setCatDesc(request
								.getParameter("catDesc" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
								.trim().replaceAll("[ ]{2,}", " "));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						// System.out.println(text);
						editcat.setSlugName(text);
					}

				}
			}

			editcat.setSectionId(sectionId);
			editcat.setCatSortNo(seqNo);
			editcat.setIsActive(isActive);
			editcat.setDelStatus(1);
			// System.out.println("category" + editcat);
			if (ret == false) {
				Category res = Constant.getRestTemplate().postForObject(Constant.url + "/saveUpdateCategory", editcat,
						Category.class);

				// System.out.println("res " + res);

				if (res.getCatId() != 0) {
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

		return "redirect:/categoryList";
	}

	@RequestMapping(value = "/editCategory/{catId}", method = RequestMethod.GET)
	public ModelAndView editCategory(@PathVariable int catId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addCategory");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", catId);

			editcat = Constant.getRestTemplate().postForObject(Constant.url + "/getCategoryByCatId", map,
					GetCategory.class);

			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editcat.getCategoryDescriptionList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editcat.getCategoryDescriptionList().get(j)
							.getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					CategoryDescription categoryDescription = new CategoryDescription();
					categoryDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editcat.getCategoryDescriptionList().add(categoryDescription);
				}
			}

			model.addObject("editCategory", editcat);

			model.addObject("isEdit", 1);

			System.out.println(editcat);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteCategory/{catId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int catId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catIdList", catId);
			map.add("delStatus", 0);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultiCategory", map, Info.class);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "Infomation deleted successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/categoryList";
	}

	@RequestMapping(value = "/addSubCategory", method = RequestMethod.GET)
	public ModelAndView addSubCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSubCategory");
		try {
			editcat = new GetCategory();

			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/subCategoryList", method = RequestMethod.GET)
	public ModelAndView subCategoryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/subCategoryList");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GetCategory[] getCategory = Constant.getRestTemplate().postForObject(Constant.url + "/getSubCatList", map,
					GetCategory[].class);
			List<GetCategory> subCategoryList = new ArrayList<GetCategory>(Arrays.asList(getCategory));
			model.addObject("subCategoryList", subCategoryList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertSubCategory", method = RequestMethod.POST)
	public String insertSubCategory(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {

			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String catId = request.getParameter("catId");
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			int sectionId = Integer.parseInt(request.getParameter("sectionId"));
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));

			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("categoryId"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(request.getParameter("categoryId"), "") == true
					|| FormValidation.Validaton(request.getParameter("seqNo"), "") == true) {

				ret = true;
			}

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			List<CategoryDescription> categoryDescriptionList = new ArrayList<CategoryDescription>();

			if (catId == "" || catId == null) {
				editcat.setCatId(0);
				editcat.setCatAddDate(sf.format(date));

				for (int i = 0; i < languagesList.size(); i++) {

					if (FormValidation.Validaton(
							request.getParameter("subCatName" + languagesList.get(i).getLanguagesId()), "") == true) {

						ret = true;
						break;
					}

					CategoryDescription categoryDescription = new CategoryDescription();
					categoryDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					categoryDescription
							.setCatName(request.getParameter("subCatName" + languagesList.get(i).getLanguagesId())
									.trim().replaceAll("[ ]{2,}", " "));
					categoryDescription
							.setCatDesc(request.getParameter("subCatDesc" + languagesList.get(i).getLanguagesId())
									.trim().replaceAll("[ ]{2,}", " "));

					if (languagesList.get(i).getLanguagesId() == 1) {

						editcat.setCatName(request.getParameter("subCatName" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));
						editcat.setCatDesc(request.getParameter("subCatDesc" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						System.out.println(text);
						editcat.setSlugName(text);
					}
					categoryDescriptionList.add(categoryDescription);
				}
				// editcat.setAddedByUserId(UserDetail.getUserId());
				editcat.setCategoryDescriptionList(categoryDescriptionList);
			} else {

				editcat.setCatId(Integer.parseInt(catId));
				editcat.setCatEditDate(sf.format(date));
				editcat.setCatAddDate(DateConvertor.convertToYMD(editcat.getCatAddDate()));
				for (int i = 0; i < editcat.getCategoryDescriptionList().size(); i++) {

					if (FormValidation.Validaton(
							request.getParameter(
									"subCatName" + editcat.getCategoryDescriptionList().get(i).getLanguageId()),
							"") == true) {

						ret = true;
						break;
					}
					editcat.getCategoryDescriptionList().get(i).setCatName(request
							.getParameter("subCatName" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
							.trim().replaceAll("[ ]{2,}", " "));
					editcat.getCategoryDescriptionList().get(i).setCatDesc(request
							.getParameter("subCatDesc" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
							.trim().replaceAll("[ ]{2,}", " "));

					if (editcat.getCategoryDescriptionList().get(i).getLanguageId() == 1) {

						editcat.setCatName(request
								.getParameter(
										"subCatName" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
								.trim().replaceAll("[ ]{2,}", " "));
						editcat.setCatDesc(request
								.getParameter(
										"subCatDesc" + editcat.getCategoryDescriptionList().get(i).getLanguageId())
								.trim().replaceAll("[ ]{2,}", " "));
						String text = editcat.getCatName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						// System.out.println(text);
						editcat.setSlugName(text);
					}

				}
				// editcat.setEditByUserId(UserDetail.getUserId());
			}
			editcat.setParentId(categoryId);

			editcat.setSectionId(sectionId);
			editcat.setCatSortNo(seqNo);
			editcat.setIsActive(isActive);
			editcat.setDelStatus(1);
			// System.out.println("category" + editcat);
			if (ret == false) {
				Category res = Constant.getRestTemplate().postForObject(Constant.url + "/saveUpdateCategory", editcat,
						Category.class);

				// System.out.println("res " + res);

				if (res.getCatId() != 0) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");

				} else {
					session.setAttribute("successMsg", "Failed to add Infomation !");
					session.setAttribute("errorMsg", "true");

				}
			} else {
				session.setAttribute("successMsg", "invalid Infomation !");
				session.setAttribute("errorMsg", "true");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/subCategoryList";
	}

	@RequestMapping(value = "/editSubCategory/{subCatId}", method = RequestMethod.GET)
	public ModelAndView editSubCategory(@PathVariable int subCatId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSubCategory");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", subCatId);

			editcat = Constant.getRestTemplate().postForObject(Constant.url + "/getCategoryByCatId", map,
					GetCategory.class);

			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editcat.getCategoryDescriptionList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editcat.getCategoryDescriptionList().get(j)
							.getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
					CategoryDescription categoryDescription = new CategoryDescription();
					categoryDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					editcat.getCategoryDescriptionList().add(categoryDescription);
				}
			}
			// System.out.println(editcat);
			model.addObject("editSubCat", editcat);

			model.addObject("isEdit", 1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getCategoryBySectionId", method = RequestMethod.GET)
	public @ResponseBody List<GetCategory> getCategoryBySectionId(HttpServletRequest request,
			HttpServletResponse response) {

		List<GetCategory> categoryList = new ArrayList<GetCategory>();
		try {
			// System.out.println("in method");

			String sectionId = request.getParameter("sectionId");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sectionId", sectionId);

			GetCategory[] category = Constant.getRestTemplate().postForObject(Constant.url + "/getAllCatIdBySectionId",
					map, GetCategory[].class);

			categoryList = new ArrayList<GetCategory>(Arrays.asList(category));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryList;
	}

	@RequestMapping(value = "/deleteSubCategory/{catId}", method = RequestMethod.GET)
	public String deleteSubCategory(@PathVariable int catId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catIdList", catId);
			map.add("delStatus", 0);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultiCategory", map, Info.class);
			// System.out.println(res);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "Infomation deleted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/subCategoryList";
	}

	@RequestMapping(value = "/addSection", method = RequestMethod.GET)
	public ModelAndView addSection(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			if (UserDetail.getRoles().equals("DA")) {

				editSection = new Section();
				model = new ModelAndView("master/addSection");
				Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
						Languages[].class);
				languagesList = new ArrayList<Languages>(Arrays.asList(languages));
				model.addObject("languagesList", languagesList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/sectionList", method = RequestMethod.GET)
	public ModelAndView sectionList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/sectionList");
		try {

			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertSection", method = RequestMethod.POST)
	public String insertSection(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String sectionId = request.getParameter("sectionId");
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));
			int isActive = Integer.parseInt(request.getParameter("isActive"));
			int type = Integer.parseInt(request.getParameter("type"));

			String secName = null;
			String secDesc = null;
			
			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("seqNo"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(request.getParameter("type"), "") == true) {

				ret = true;
			}

			List<SectionDescription> sectionDescriptionList = new ArrayList<SectionDescription>();

			if (sectionId == "" || sectionId == null) {
				editSection.setSectionId(0);
				editSection.setSectionAddDate(sf.format(date));
				for (int i = 0; i < languagesList.size(); i++) {

					secName = request.getParameter("sectionName" + languagesList.get(i).getLanguagesId()).trim().replaceAll("[ ]{2,}", " ");
					secDesc = request.getParameter("sectionDesc" + languagesList.get(i).getLanguagesId()).trim().replaceAll("[ ]{2,}", " ");
					
					if (FormValidation.Validaton(secName, "") == true || FormValidation.Validaton(secDesc, "") == true) {

						ret = true;
						break;
					}

					SectionDescription sectionDescription = new SectionDescription();
					sectionDescription.setLanguageId(languagesList.get(i).getLanguagesId());
					
					sectionDescription.setSectionName(XssEscapeUtils.jsoupParse(secName));
					sectionDescription.setSectionDesc(XssEscapeUtils.jsoupParse(secDesc));

					if (languagesList.get(i).getLanguagesId() == 1) {

						secName = request.getParameter("sectionName" + languagesList.get(i).getLanguagesId()).trim().replaceAll("[ ]{2,}", " ");
						secDesc = request.getParameter("sectionDesc" + languagesList.get(i).getLanguagesId()).trim().replaceAll("[ ]{2,}", " ");
						
						editSection.setSectionName(XssEscapeUtils.jsoupParse(secName));
						editSection.setSectionDesc(XssEscapeUtils.jsoupParse(secDesc));
						
						String text = editSection.getSectionName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						editSection.setSectionSlugname(text);
					}
					sectionDescriptionList.add(sectionDescription);

				}

			} else {
				editSection.setSectionId(Integer.parseInt(sectionId));
				editSection.setSectionEditDate(sf.format(date));
				sectionDescriptionList = editSection.getSectionDescriptionList();

				for (int i = 0; i < sectionDescriptionList.size(); i++) {
					
					secName = request.getParameter("sectionName" + sectionDescriptionList.get(i).getLanguageId()).trim().replaceAll("[ ]{2,}", " ");
					secDesc = request.getParameter("sectionDesc" + sectionDescriptionList.get(i).getLanguageId()).trim().replaceAll("[ ]{2,}", " ");
					
					if (FormValidation.Validaton(secName,"") == true || FormValidation.Validaton(secDesc,"") == true ) {

						ret = true;
						break;
					}

					sectionDescriptionList.get(i).setSectionName(XssEscapeUtils.jsoupParse(secName));					
					sectionDescriptionList.get(i).setSectionDesc(XssEscapeUtils.jsoupParse(secDesc));

					if (sectionDescriptionList.get(i).getLanguageId() == 1) {

						secName = request.getParameter("sectionName" + sectionDescriptionList.get(i).getLanguageId()).trim().replaceAll("[ ]{2,}", " ");
						secDesc = request.getParameter("sectionDesc" + sectionDescriptionList.get(i).getLanguageId()).trim().replaceAll("[ ]{2,}", " ");
						
						editSection.setSectionName(XssEscapeUtils.jsoupParse(secName));
						editSection.setSectionDesc(XssEscapeUtils.jsoupParse(secDesc));
						String text = editSection.getSectionName();
						text = text.replaceAll("[^a-zA-Z0-9]", "-").toLowerCase();
						editSection.setSectionSlugname(text);
					}
				}
			}

			editSection.setSectionSortNo(seqNo);
			editSection.setSectionDateTime(sf.format(date));
			editSection.setDelStatus(1);
			editSection.setIsActive(isActive);
			editSection.setExInt1(type);

			editSection.setSectionDescriptionList(sectionDescriptionList);
			if (ret == false) {
				Section res = Constant.getRestTemplate().postForObject(Constant.url + "/saveSection", editSection,
						Section.class);
				session = request.getSession();
				if (res.getSectionId() != 0) {
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

		return "redirect:/sectionList";
	}

	@RequestMapping(value = "/editSection/{sectionId}", method = RequestMethod.GET)
	public ModelAndView editSection(@PathVariable int sectionId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addSection");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sectionId", sectionId);

			editSection = Constant.getRestTemplate().postForObject(Constant.url + "/getSectionBySectionId", map,
					Section.class);

			// System.out.println(editSection);

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);
			model.addObject("isEdit", 1);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editSection.getSectionDescriptionList().size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editSection.getSectionDescriptionList().get(j)
							.getLanguageId()) {
						flag = 1;
						break;
					}
				}

				if (flag == 0) {
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
	}

	@RequestMapping(value = "/clearSessionAttribute", method = RequestMethod.GET)
	public @ResponseBody Info clearSessionAttribute(HttpServletRequest request, HttpServletResponse response) {

		Info Info = new Info();
		try {

			HttpSession session = request.getSession();
			session.removeAttribute("successMsg");
			session.removeAttribute("errorMsg");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Info;
	}

	@RequestMapping(value = "/deleteSection/{sectionId}", method = RequestMethod.GET)
	public String deleteSection(@PathVariable int sectionId, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("sectionId", sectionId);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteSection", map, Info.class);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "Infomation deleted successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sectionList";
	}

	@RequestMapping(value = "/addPhotoGallary", method = RequestMethod.GET)
	public ModelAndView addPhotoGallary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addPhotoGallary");
		try {

			GetGalleryHeaderByCatId[] galleryheader = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getGalleryHeaderList", GetGalleryHeaderByCatId[].class);
			List<GetGalleryHeaderByCatId> galleryheaderList = new ArrayList<GetGalleryHeaderByCatId>(
					Arrays.asList(galleryheader));
			model.addObject("galleryheaderList", galleryheaderList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GetCategory[] category = Constant.getRestTemplate().postForObject(Constant.url + "/getAllCatList", map,
					GetCategory[].class);
			List<GetCategory> categoryList = new ArrayList<GetCategory>(Arrays.asList(category));
			model.addObject("categoryList", categoryList);

			/*
			 * GetSubCategory[] getSubCategory =
			 * Constant.getRestTemplate().postForObject(Constant.url + "/getAllSubCatList",
			 * map, GetSubCategory[].class); List<GetSubCategory> subCategoryList = new
			 * ArrayList<GetSubCategory>(Arrays.asList(getSubCategory));
			 * model.addObject("subCategoryList", subCategoryList);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editPhotoGallary/{galleryHeaderId}", method = RequestMethod.GET)
	public ModelAndView editPhotoGallary(@PathVariable int galleryHeaderId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addPhotoGallary");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryHeadId", galleryHeaderId);

			editGalleryheader = Constant.getRestTemplate().postForObject(Constant.url + "/getGalleryHeaderById", map,
					Galleryheader.class);
			model.addObject("editGalleryheader", editGalleryheader);

			// System.out.println(editGalleryheader);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			GetCategory[] category = Constant.getRestTemplate().postForObject(Constant.url + "/getAllCatList", map,
					GetCategory[].class);
			List<GetCategory> categoryList = new ArrayList<GetCategory>(Arrays.asList(category));
			model.addObject("categoryList", categoryList);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", editGalleryheader.getCatId());
			map.add("delStatus", 1);
			GetSubCategory[] getSubCategory = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getAllSubCatByCatId", map, GetSubCategory[].class);
			List<GetSubCategory> list = new ArrayList<GetSubCategory>(Arrays.asList(getSubCategory));
			model.addObject("subCategoryList", list);

			GetGalleryHeaderByCatId[] galleryheader = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getGalleryHeaderList", GetGalleryHeaderByCatId[].class);
			List<GetGalleryHeaderByCatId> galleryheaderList = new ArrayList<GetGalleryHeaderByCatId>(
					Arrays.asList(galleryheader));
			model.addObject("galleryheaderList", galleryheaderList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/getSubCatListByCatId", method = RequestMethod.GET)
	public @ResponseBody List<GetSubCategory> getSubCatListByCatId(HttpServletRequest request,
			HttpServletResponse response) {

		List<GetSubCategory> list = new ArrayList<GetSubCategory>();
		try {

			int catId = Integer.parseInt(request.getParameter("catId"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("catId", catId);
			map.add("delStatus", 1);
			GetSubCategory[] getSubCategory = Constant.getRestTemplate()
					.postForObject(Constant.url + "/getAllSubCatByCatId", map, GetSubCategory[].class);
			list = new ArrayList<GetSubCategory>(Arrays.asList(getSubCategory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@RequestMapping(value = "/insertGalleryHeader", method = RequestMethod.POST)
	public String insertGalleryHeader(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {

			String galleryHeaderId = request.getParameter("galleryHeaderId");
			String titleName = request.getParameter("titleName");
			int catId = Integer.parseInt(request.getParameter("catId"));
			int subCatId = Integer.parseInt(request.getParameter("subCatId"));
			int seqNo = Integer.parseInt(request.getParameter("seqNo"));

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			if (galleryHeaderId == "" || galleryHeaderId == null) {
				editGalleryheader.setGalleryHeaderId(0);
				editGalleryheader.setGalleryAddDate(sf.format(date));
			} else {
				editGalleryheader.setGalleryHeaderId(Integer.parseInt(galleryHeaderId));
				editGalleryheader.setGalleryEditDate(sf.format(date));
			}
			editGalleryheader.setCatId(catId);
			editGalleryheader.setSubCatId(subCatId);
			editGalleryheader.setGalleryTitle(titleName);
			editGalleryheader.setGallerySortNo(String.valueOf(seqNo));
			editGalleryheader.setIsActive(1);
			editGalleryheader.setDelStatus(1);
			editGalleryheader.setUserId(1);

			// System.out.println("sub category" + editSubCategory);

			Galleryheader res = Constant.getRestTemplate().postForObject(Constant.url + "/saveGalleryHeader",
					editGalleryheader, Galleryheader.class);

			// System.out.println("res " + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addPhotoGallary";
	}

	@RequestMapping(value = "/deletePhotoGallary/{galleryHeaderId}", method = RequestMethod.GET)
	public String deletePhotoGallary(@PathVariable int galleryHeaderId, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryHeadId", galleryHeaderId);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteGalleryHeader", map, Info.class);
			// System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addPhotoGallary";
	}

	@RequestMapping(value = "/photoGalleryDetail/{galleryHeaderId}", method = RequestMethod.GET)
	public ModelAndView photoGalleryDetail(@PathVariable int galleryHeaderId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/photoGalleryDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryHeadId", galleryHeaderId);

			editGalleryheader = Constant.getRestTemplate().postForObject(Constant.url + "/getGalleryHeaderById", map,
					Galleryheader.class);
			model.addObject("editGalleryheader", editGalleryheader);
			model.addObject("url", Constant.gallryImageURL);
			// System.out.println(editGalleryheader);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertGalleryDetail", method = RequestMethod.POST)
	public String insertGalleryDetail(@RequestParam("files") List<MultipartFile> files, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String imgDesc = request.getParameter("imgDesc");
			List<GalleryDetail> detailList = new ArrayList<GalleryDetail>();

			VpsImageUpload upload = new VpsImageUpload();
			String docFile = null;

			Date date = new Date(); // your date
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			/*
			 * try {
			 * 
			 * for(int i = 0 ; i< files.size() ; i++) {
			 * 
			 * GalleryDetail galleryDetail = new GalleryDetail(); docFile =
			 * dateTimeInGMT.format(date)+"_"+files.get(i).getOriginalFilename();
			 * galleryDetail.setPhotoName(docFile);
			 * galleryDetail.setPhotoCaption(files.get(i).getOriginalFilename());
			 * galleryDetail.setPhotoDesc(imgDesc); galleryDetail.setDate(sf.format(date));
			 * galleryDetail.setIsActive(1); galleryDetail.setDelStatus(1);
			 * galleryDetail.setGalleryHeadId(editGalleryheader.getGalleryHeaderId());
			 * 
			 * upload.saveUploadedFiles(files.get(i), Constant.gallryImage, docFile);
			 * 
			 * System.out.println("upload method called for image Upload " +
			 * files.get(i).toString()); detailList.add(galleryDetail); }
			 * 
			 * 
			 * } catch (IOException e) {
			 * 
			 * System.out.println("Exce in File Upload In GATE ENTRY  Insert " +
			 * e.getMessage()); e.printStackTrace(); }
			 */

			GalleryDetail[] res = Constant.getRestTemplate().postForObject(Constant.url + "/saveGalleryDetail",
					detailList, GalleryDetail[].class);

			// System.out.println(editGalleryheader);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/photoGalleryDetail/" + editGalleryheader.getGalleryHeaderId();
	}

	@RequestMapping(value = "/deletePhotoGallaryDetail/{galleryDetailId}", method = RequestMethod.GET)
	public String deletePhotoGallaryDetail(@PathVariable int galleryDetailId, HttpServletRequest request,
			HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("galleryHeadId", galleryDetailId);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteGalleryHeader", map, Info.class);
			// System.out.println(res);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/photoGalleryDetail/" + editGalleryheader.getGalleryHeaderId();
	}

}
