package com.ats.rusaadmin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
import com.ats.rusaadmin.common.FormValidation;
import com.ats.rusaadmin.common.VpsImageUpload;
import com.ats.rusaadmin.model.BannerImages;
import com.ats.rusaadmin.model.ContactUs;
import com.ats.rusaadmin.model.GetCategory;
import com.ats.rusaadmin.model.ImageLink;
import com.ats.rusaadmin.model.Info;
import com.ats.rusaadmin.model.Languages;
import com.ats.rusaadmin.model.Logo;
import com.ats.rusaadmin.model.Maintainance;
import com.ats.rusaadmin.model.MetaData;
import com.ats.rusaadmin.model.Section;
import com.ats.rusaadmin.model.SectionDescription;
import com.ats.rusaadmin.model.SiteMaitenance;
import com.ats.rusaadmin.model.SocialChannels;
import com.ats.rusaadmin.model.User;
import com.ats.rusaadmin.util.ItextPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class NewController {
	RestTemplate rest = new RestTemplate();
	MetaData editMetaData = new MetaData();
	List<Languages> languagesList = new ArrayList<Languages>();
	ImageLink editImageLink = new ImageLink();
	List<MetaData> editMeta = new ArrayList<MetaData>();
	ContactUs contactUs = new ContactUs();
	SocialChannels socialChannel = new SocialChannels();
	Maintainance editSite = new Maintainance();
	List<SiteMaitenance> editsiteMain = new ArrayList<SiteMaitenance>();

	@RequestMapping(value = "/addMetaNew", method = RequestMethod.GET)
	public ModelAndView addMetaData(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addMetaData");
		try {
			editMetaData = new MetaData();

			Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url + "/getLanguageList",
					Languages[].class);
			languagesList = new ArrayList<Languages>(Arrays.asList(languages));
			model.addObject("languagesList", languagesList);

			MetaData[] editMet = Constant.getRestTemplate().getForObject(Constant.url + "/getAllMetaDataList",
					MetaData[].class);
			editMeta = new ArrayList<MetaData>(Arrays.asList(editMet));

			model.addObject("editMetaData", editMeta);

			for (int i = 0; i < languagesList.size(); i++) {

				int flag = 0;

				for (int j = 0; j < editMeta.size(); j++) {

					if (languagesList.get(i).getLanguagesId() == editMeta.get(j).getLanguageId()) {
						flag = 1;
						break;

					}
				}

				if (flag == 0) {
					MetaData MetaData = new MetaData();
					MetaData.setLanguageId(languagesList.get(i).getLanguagesId());
					editMeta.add(MetaData);

				}
			}
			System.out.println(editMeta);
			model.addObject("editMeta", editMeta);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	/*
	 * @RequestMapping(value = "/sectionList", method = RequestMethod.GET) public
	 * ModelAndView sectionList(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = new ModelAndView("master/sectionList"); try {
	 * 
	 * 
	 * Section[] section = Constant.getRestTemplate().getForObject(Constant.url +
	 * "/getAllSectionList", Section[].class); List<Section> sectionList = new
	 * ArrayList<Section>(Arrays.asList(section)); model.addObject("sectionList",
	 * sectionList);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return model; }
	 */

	@RequestMapping(value = "/insertMetaData", method = RequestMethod.POST)
	public String insertMetaData(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/addEmployee");
		try {
			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");
			Boolean ret = false;
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			List<MetaData> metaDescriptionList = new ArrayList<MetaData>();

			for (int i = 0; i < editMeta.size(); i++) {
				if (FormValidation.Validaton(request.getParameter("siteName" + languagesList.get(i).getLanguagesId()),
						"") == true
						|| FormValidation.Validaton(
								request.getParameter("metaDescription" + languagesList.get(i).getLanguagesId()),
								"") == true
						|| FormValidation.Validaton(
								request.getParameter("metaAuthor" + languagesList.get(i).getLanguagesId()), "") == true
						|| FormValidation.Validaton(
								request.getParameter("metaKeywords" + languagesList.get(i).getLanguagesId()),
								"") == true) {

					ret = true;
					break;
				}

				editMeta.get(i).setSiteTitle(request.getParameter("siteName" + languagesList.get(i).getLanguagesId())
						.trim().replaceAll("[ ]{2,}", " "));
				editMeta.get(i).setMetaDescription(
						request.getParameter("metaDescription" + languagesList.get(i).getLanguagesId()).trim()
								.replaceAll("[ ]{2,}", " "));
				editMeta.get(i).setMetaAuthor(request.getParameter("metaAuthor" + languagesList.get(i).getLanguagesId())
						.trim().replaceAll("[ ]{2,}", " "));
				editMeta.get(i)
						.setMetaKeywords(request.getParameter("metaKeywords" + languagesList.get(i).getLanguagesId())
								.trim().replaceAll("[ ]{2,}", " "));

			}
			// editSection.setAddedByUserId(UserDetail.getUserId());
			// System.out.println("siteTitle" + editMeta);

			if (ret == false) {
				List<MetaData> res = Constant.getRestTemplate().postForObject(Constant.url + "/saveMetaData", editMeta,
						List.class);

				// System.out.println("res " + res);
				session = request.getSession();
				if (res.isEmpty() == false) {
					session.setAttribute("successMsg", "Infomation added successfully!");
					session.setAttribute("errorMsg", "false");

				} else {
					session.setAttribute("successMsg", "Failed To Add Infomation!");
					session.setAttribute("errorMsg", "false");

				}
			} else {
				session.setAttribute("successMsg", "Invalid Infomation!");
				session.setAttribute("errorMsg", "true");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/addMetaNew";
	}

	/*
	 * @RequestMapping(value = "/editSection/{sectionId}", method =
	 * RequestMethod.GET) public ModelAndView editSection(@PathVariable int
	 * sectionId, HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * ModelAndView model = new ModelAndView("master/addSection"); try {
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>(); map.add("sectionId", sectionId);
	 * 
	 * editSection = Constant.getRestTemplate().postForObject(Constant.url +
	 * "/getSectionBySectionId", map, Section.class);
	 * 
	 * 
	 * System.out.println(editSection);
	 * 
	 * Languages[] languages = Constant.getRestTemplate().getForObject(Constant.url
	 * + "/getLanguageList", Languages[].class); languagesList = new
	 * ArrayList<Languages>(Arrays.asList(languages));
	 * model.addObject("languagesList", languagesList); model.addObject("isEdit",
	 * 1);
	 * 
	 * 
	 * for(int i=0 ; i<languagesList.size() ; i++) {
	 * 
	 * int flag=0;
	 * 
	 * for(int j=0 ; j<editSection.getSectionDescriptionList().size() ; j++) {
	 * 
	 * if(languagesList.get(i).getLanguagesId()==editSection.
	 * getSectionDescriptionList().get(j).getLanguageId()) { flag=1; break; } }
	 * 
	 * if(flag==0) { SectionDescription sectionDescription = new
	 * SectionDescription();
	 * sectionDescription.setLanguageId(languagesList.get(i).getLanguagesId());
	 * editSection.getSectionDescriptionList().add(sectionDescription); } }
	 * 
	 * model.addObject("editSection", editSection);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return model; }
	 */

	@RequestMapping(value = "/addImageLink", method = RequestMethod.GET)
	public ModelAndView addSliderPic(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addImageLink");
		try {

			editImageLink = new ImageLink();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/insertImageLink", method = RequestMethod.POST)
	public String insertBannerImage(@RequestParam("docfile") List<MultipartFile> docfile, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			User UserDetail = (User) session.getAttribute("UserDetail");

			String id = request.getParameter("id");
			String urlLink = request.getParameter("urlLink");
			String titleName = request.getParameter("title_name");

			int sortOrder = Integer.parseInt(request.getParameter("sortNo"));
			int isActive = Integer.parseInt(request.getParameter("isActive"));

			String imageName = request.getParameter("imageName");
			VpsImageUpload upload = new VpsImageUpload();
			String docFile = null;

			Boolean ret = false;

			if (FormValidation.Validaton(request.getParameter("title_name"), "") == true
					|| FormValidation.Validaton(request.getParameter("isActive"), "") == true
					|| FormValidation.Validaton(request.getParameter("sortNo"), "") == true) {

				ret = true;
			}

			Date date = new Date(); // your date
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if(ret==false) {
			if (id.equalsIgnoreCase(null) || id.equalsIgnoreCase("")) {
				
				

				docFile = dateTimeInGMT.format(date) + "_" + docfile.get(0).getOriginalFilename();
				// editImageLink.setSliderImage("re"+docFile);
				editImageLink.setSliderImage(docFile);
				try {
					Info info = upload.saveUploadedImgeWithResize(docfile.get(0), Constant.bannerImageURL, docFile,
							Constant.values, 0, 140, 60, 0, 0);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				editImageLink.setAddDate(sf.format(date));
				editImageLink.setIsActive(isActive);
				// editbanner.setAddedByUserId(UserDetail.getUserId());
			} else {

				if (docfile.get(0).getOriginalFilename() == null || docfile.get(0).getOriginalFilename() == "") {
					editImageLink.setSliderImage(imageName.trim().replaceAll("[ ]{2,}", " "));
				} else {
					docFile = dateTimeInGMT.format(date) + "_" + docfile.get(0).getOriginalFilename();
					// editImageLink.setSliderImage("re"+docFile);
					editImageLink.setSliderImage(docFile.trim().replaceAll("[ ]{2,}", " "));
					try {
						Info info = upload.saveUploadedImgeWithResize(docfile.get(0), Constant.bannerImageURL, docFile,
								Constant.values, 0, 140, 60, 0, 0);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				editImageLink.setEditDate(sf.format(date));
				editImageLink.setIsActive(isActive);
				// editbanner.setEditByUserId(UserDetail.getUserId());
			}

			editImageLink.setUrlLink(urlLink.trim().replaceAll("[ ]{2,}", " "));
			editImageLink.setTitleName(titleName.trim().replaceAll("[ ]{2,}", " "));
			editImageLink.setDelStatus(1);
			// editImageLink.setIsActive(1);
			editImageLink.setSortOrder(sortOrder);

			//System.out.println("editImageLink" + editImageLink);

			ImageLink res = Constant.getRestTemplate().postForObject(Constant.url + "/saveImageLink", editImageLink,
					ImageLink.class);

			if (res.getId() != 0) {
				session.setAttribute("successMsg", "Infomation added successfully!");
				session.setAttribute("errorMsg", "false");
			} else {
				session.setAttribute("successMsg", "Failed to Add Infomation !");
				session.setAttribute("errorMsg", "true");
			}
			}
			else {
				session.setAttribute("successMsg", "Invalid Infomation!");
				session.setAttribute("errorMsg", "true");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/imageLinkList";
	}

	@RequestMapping(value = "/deleteImageLink/{id}", method = RequestMethod.GET)
	public String deleteImageLink(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteImagesLink", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			session.setAttribute("successMsg", "Infomation deleted successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/imageLinkList";
	}

	@RequestMapping(value = "/imageLinkList", method = RequestMethod.GET)
	public ModelAndView sliderPicList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/imagesLinkList");
		try {

			ImageLink[] imagesLink = Constant.getRestTemplate().getForObject(Constant.url + "/getAllImageLinkList",
					ImageLink[].class);
			List<ImageLink> imagesLinkList = new ArrayList<ImageLink>(Arrays.asList(imagesLink));

			for (int i = 0; i < imagesLinkList.size(); i++) {
				imagesLinkList.get(i).setAddDate(DateConvertor.convertToDMY(imagesLinkList.get(i).getAddDate()));
			}

			model.addObject("imagesLinkList", imagesLinkList);
			model.addObject("url", Constant.getBannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editImagesLink/{id}", method = RequestMethod.GET)
	public ModelAndView editImagesLink(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addImageLink");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			editImageLink = Constant.getRestTemplate().postForObject(Constant.url + "/getImageLinksById", map,
					ImageLink.class);
			model.addObject("editImageLink", editImageLink);
			model.addObject("isEdit", 1);
			model.addObject("url", Constant.getBannerImageURL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editContactUs", method = RequestMethod.POST)
	public String editContactUs(HttpServletRequest request, HttpServletResponse response) {

		try {

			String remark = request.getParameter("remark");

			String id = request.getParameter("id");
			System.out.println("remark: " + remark);
			if (id != null) {

				contactUs.setId(Integer.parseInt(id));
				contactUs.setStatusByAdmin(0);
				contactUs.setRemark(remark);

			}
			ContactUs res = Constant.getRestTemplate().postForObject(Constant.url + "/saveContactUs", contactUs,
					ContactUs.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/ContactList";
	}

	List<ContactUs> contactList = new ArrayList<ContactUs>();

	@RequestMapping(value = "/ContactList", method = RequestMethod.GET)
	public ModelAndView ContactList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/contactList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			ContactUs[] ContactUs = Constant.getRestTemplate().getForObject(Constant.url + "/getAllContactList",
					ContactUs[].class);
			contactList = new ArrayList<ContactUs>(Arrays.asList(ContactUs));
			model.addObject("contactList", contactList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/ContactListPdf", method = RequestMethod.GET)
	public void ContactListPdf(HttpServletRequest request, HttpServletResponse response) {

		try {

			Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
			// 50, 45, 50, 60
			document.setMargins(Constant.marginLeft, Constant.marginRight, Constant.marginTop, Constant.marginBottom);
			document.setMarginMirroring(false);
			/*
			 * document.left(100f); document.top(150f);
			 */

			String FILE_PATH = Constant.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");

			ItextPageEvent event = new ItextPageEvent(header, title, "", "Contact List");

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.0f, 6.0f, 3.0f, 3.0f, 4.0f, 3.0f });

				Font headFontData = Constant.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constant.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constant.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(Constant.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Email ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Mobile No", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Message", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Feedback Type", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constant.baseColorTableHeader);
				hcell.setPadding(5);
				table.addCell(hcell);

				int index = 0;

				for (int i = 0; i < contactList.size(); i++) {
					// System.err.println("I " + i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + contactList.get(i).getContactName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + contactList.get(i).getEmailId(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + contactList.get(i).getMobileNo(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + contactList.get(i).getMessage(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPadding(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + contactList.get(i).getExVar1(), headFontData));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(5);
					table.addCell(cell);

				}

				document.open();
				Font reportNameFont = Constant.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph("Contact List", reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();

				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}

	@RequestMapping(value = "/editContact/{id}", method = RequestMethod.GET)
	public ModelAndView editContact(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/editContact");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);

			contactUs = Constant.getRestTemplate().postForObject(Constant.url + "/getContactById", map,
					ContactUs.class);
			/*
			 * ContactUs[] contact = Constant.getRestTemplate().getForObject(Constant.url +
			 * "/getContactById", ContactUs[].class); List<ContactUs> editcontact = new
			 * ArrayList<ContactUs>(Arrays.asList(contact));
			 */

			model.addObject("editcontact", contactUs);
			model.addObject("isEdit", 1);

			System.out.println(contactUs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			// map.add("delStatus", 0);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteContact", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (id == 0) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/ContactList";
	}

	@RequestMapping(value = "/multipleContactDelete", method = RequestMethod.GET)
	public String multipleContactDelete(HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];
			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleContact", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/ContactList";
	}

	@RequestMapping(value = "/deletedContactList", method = RequestMethod.GET)
	public ModelAndView deletedContactList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/deletedContactList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 0);
			ContactUs[] ContactUs = Constant.getRestTemplate().getForObject(Constant.url + "/getAllContactListDeleted",
					ContactUs[].class);
			contactList = new ArrayList<ContactUs>(Arrays.asList(ContactUs));
			model.addObject("contactList", contactList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/retriveContact/{id}", method = RequestMethod.GET)
	public String retriveContact(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			// map.add("delStatus", 0);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/retriveContact", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (id == 0) {
				session.setAttribute("successMsg", "Sorry, Can't Retrieve!");
			} else {
				session.setAttribute("successMsg", "Infomation Retrieve successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/deletedContactList";
	}

	@RequestMapping(value = "/editChannel", method = RequestMethod.POST)
	public String editChannel(HttpServletRequest request, HttpServletResponse response) {

		try {

			// String remark = request.getParameter("remark");
			String sortNo = request.getParameter("seqNo");
			String isActive = request.getParameter("isActive");
			String urlLink = request.getParameter("urlLink");
			String id = request.getParameter("id");

			if (id != null) {

				socialChannel.setId(Integer.parseInt(id));
				socialChannel.setUrllinks(urlLink);
				socialChannel.setIsActive(Integer.parseInt(isActive));
				socialChannel.setSortNo(Integer.parseInt(sortNo));

			}
			SocialChannels res = Constant.getRestTemplate().postForObject(Constant.url + "/saveSocialChannel",
					socialChannel, SocialChannels.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/socialChannelList";
	}

	@RequestMapping(value = "/socialChannelList", method = RequestMethod.GET)
	public ModelAndView socialChannelList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/socialChannelList");
		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("delStatus", 1);
			List<SocialChannels> socialList = Constant.getRestTemplate()
					.getForObject(Constant.url + "/getAllSocialList", List.class);
			// List<User> userList = new ArrayList<User>(Arrays.asList(getUser));
			model.addObject("channelList", socialList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/editChannel/{id}", method = RequestMethod.GET)
	public ModelAndView editChannel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/editSocialChannel");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);

			socialChannel = Constant.getRestTemplate().postForObject(Constant.url + "/getSocialChannelById", map,
					SocialChannels.class);
			/*
			 * ContactUs[] contact = Constant.getRestTemplate().getForObject(Constant.url +
			 * "/getContactById", ContactUs[].class); List<ContactUs> editcontact = new
			 * ArrayList<ContactUs>(Arrays.asList(contact));
			 */

			model.addObject("editChannel", socialChannel);
			model.addObject("isEdit", 1);

			System.out.println(contactUs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/deleteChannel/{id}", method = RequestMethod.GET)
	public String deleteChannel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {

		// ModelAndView model = new ModelAndView("masters/empDetail");
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			// map.add("delStatus", 0);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteSocialChannel", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (id == 0) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/socialChannelList";
	}

	@RequestMapping(value = "/siteMaintenances", method = RequestMethod.GET)
	public ModelAndView addSiteMaintenance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/siteMaintenances");
		try {

			editSite = Constant.getRestTemplate().getForObject(Constant.url + "/getMaintananceRecord",
					Maintainance.class);
			model.addObject("editsiteMain", editSite);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/siteMaintenance", method = RequestMethod.POST)
	public String siteMaintenance(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();

			String status = request.getParameter("status");
			String message = request.getParameter("message");
			Date date = new Date(); // your date
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			editSite.setMaintenanceStatus(Integer.parseInt(status));

			editSite.setEditDate(sf.format(date));
			editSite.setMessage(message);

			Maintainance res = Constant.getRestTemplate().postForObject(Constant.url + "/saveSiteMaintenance", editSite,
					Maintainance.class);

			if (res.getId() == 0) {
				session.setAttribute("successMsg", "Error !");
			} else {
				session.setAttribute("successMsg", "Infomation Updated successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/siteMaintenances";
	}

	@RequestMapping(value = "/siteMapList", method = RequestMethod.GET)
	public ModelAndView siteMapList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/siteMapList");
		try {
			/*
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>(); map.add("delStatus", 1); List<SocialChannels> socialList =
			 * Constant.getRestTemplate().getForObject(Constant.url + "/getAllSocialList",
			 * List.class); //List<User> userList = new
			 * ArrayList<User>(Arrays.asList(getUser)); model.addObject("channelList",
			 * socialList);
			 */

			Section[] section = Constant.getRestTemplate().getForObject(Constant.url + "/getAllSectionList",
					Section[].class);
			List<Section> sectionList = new ArrayList<Section>(Arrays.asList(section));
			model.addObject("sectionList", sectionList);

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

	// *********************Multiple Delete****************************************

	@RequestMapping(value = "/multipleCMSDelete", method = RequestMethod.GET)
	public String multipleCMSDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleCMSContentList", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cmsList";
	}

	@RequestMapping(value = "/multipleFAQDelete", method = RequestMethod.GET)
	public String multipleFAQDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleFAQList", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/faqList";
	}

	@RequestMapping(value = "/multipleGalleryImageDelete", method = RequestMethod.GET)
	public String multipleGalleryImageDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleGalleryImageVideoList",
					map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/uploadedImageList";
	}

	@RequestMapping(value = "/multipleVideoDelete", method = RequestMethod.GET)
	public String multipleVideoDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleGalleryImageVideoList",
					map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/VedioFormList";
	}

	@RequestMapping(value = "/multipleTestimonialDelete", method = RequestMethod.GET)
	public String multipleTestimonialDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate()
					.postForObject(Constant.url + "/deleteMultipleTestimonialSuccessTeamList", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/testImonialList";
	}

	@RequestMapping(value = "/multipleSuccessStoryDelete", method = RequestMethod.GET)
	public String multipleSuccessStoryDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate()
					.postForObject(Constant.url + "/deleteMultipleTestimonialSuccessTeamList", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/successStoryList";
	}

	@RequestMapping(value = "/multipleTeamListDelete", method = RequestMethod.GET)
	public String multipleTeamListDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate()
					.postForObject(Constant.url + "/deleteMultipleTestimonialSuccessTeamList", map, Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/teamList";
	}

	@RequestMapping(value = "/multipleDocumentListDelete", method = RequestMethod.GET)
	public String multipleDocumentListDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleDocumentList", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/documentUploadList";
	}

	@RequestMapping(value = "/multipleNewsListDelete", method = RequestMethod.GET)
	public String multipleNewsListDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleNewsBlogEventsList", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/NewsBlogList";
	}

	@RequestMapping(value = "/multipleEventListDelete", method = RequestMethod.GET)
	public String multipleEventListDelete(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] ids = request.getParameterValues("ids");
			String id = "0";

			System.err.println("in mul del ");

			for (int i = 0; i < ids.length; i++) {
				id = id + "," + ids[i];

			}
			System.err.println("in mul del " + id);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("id", id);
			Info res = Constant.getRestTemplate().postForObject(Constant.url + "/deleteMultipleNewsBlogEventsList", map,
					Info.class);
			System.out.println(res);

			HttpSession session = request.getSession();
			if (res.isError() == true) {
				session.setAttribute("successMsg", "Sorry, Can't deleted!");
			} else {
				session.setAttribute("successMsg", "Infomation deleted successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/EventFormList";
	}

}
