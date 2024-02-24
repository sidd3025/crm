package com.crm.portal.Register;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.models.BlobRequestConditions;
import com.azure.storage.blob.options.BlobParallelUploadOptions;
import com.crm.portal.EmployeeDeatils.EmployeeDetails;
import com.crm.portal.EmployeeDeatils.EmployeeService;
import com.crm.portal.Login.LoginUserAuthorityService;
import com.crm.portal.Login.LoginUserService;
import com.crm.portal.Login.Mainsidebarauthority;
import com.crm.portal.Login.MainsidebarauthorityService;

@Controller
public class ProductController {
	
	@Autowired
	private MainsidebarauthorityService mainsidebarauthorityService;
	@Autowired
	private LoginUserAuthorityService loginuserauthorityService;
	@Autowired
	private LoginUserService loginuserService;
	@Autowired
	private EmployeeService employeeserivce;
	@Autowired
	private ProductService productservice;
	@Autowired
	private DatasheetService dataservice;
	@Autowired
	private CaseStudiesService casestudiesservice;
	
	
//	@GetMapping("/resource/{id}")
//	public String LeadResourcelistadmin(@PathVariable(value="productid") int productid, Model model,HttpServletRequest request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
//		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
//		
//		model.addAttribute("productList", productservice.listAll());
//		model.addAttribute("DatasheetList", dataservice.listAll());
//		model.addAttribute("caseList", casestudiesservice.listAll());
//		model.addAttribute("ProductID", productid); 
//		
//	    EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		 
//		List<ProductResource> lc = new LinkedList<>();
//     	
////		 model.addAttribute("AccountList", accService.GetIDById(employee.getId()));
////		 model.addAttribute("ownername",employee.getFirstname());
////		
//		model.addAttribute("AllProductList", productservice.getproductfile("pdf"));
////    	model.addAttribute("AllCoursesList1", dataservice.FilterBySubtopic(course"mp4" ));
////    	model.addAttribute("Dummyproject", casestudiesservice.FilterBySubtopic(courseid, topicid, day));
//		
//		return "Product_Resource/ResourceFile";
//	}
	
	@GetMapping("/product/add")
	public String ContactAdd(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("productlist",productservice.listAll());
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("AccountList", accountserivce
        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	
        model.addAttribute("employee", employee);
		
        return "Product_Resource/ProductResourcefileupload";
	}

	
	
	@PostMapping("/SaveAllProduct")
	public String SaveAllProduct(@ModelAttribute("product") ProductResource product, @RequestParam("readingPdfFile1") MultipartFile[] readingPdfFile1, 
			@RequestParam("readingPdfFile2") MultipartFile[] readingPdfFile2,
//			@RequestParam("readingPdfFile3") MultipartFile[] readingPdfFile3, @RequestParam("readingPdfFile4") MultipartFile[] readingPdfFile4,
//			@RequestParam("readingPdfFile5") MultipartFile[] readingPdfFile5, @RequestParam("readingPdfFile6") MultipartFile[] readingPdfFile6,
			@RequestParam("filedescription1") String filedescription1,
			@RequestParam("filedescription2") String filedescription2, HttpServletRequest request) throws IOException{
		for (MultipartFile file : readingPdfFile1) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        product.setReadingmaterials1(fileName);
//	    	product.setReadingmaterials2(fileName2);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        ProductResource p= new ProductResource();
	        p.setReadingmaterials1(product.getReadingmaterials1());
	        p.setFiledescription1(product.getFiledescription1());
	        p.setType1("pdf");
	        p.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        productservice.save(p);
	    	System.out.println("End : " );
			}
		}
		for (MultipartFile file : readingPdfFile2) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        product.setReadingmaterials1(fileName);
//	    	product.setReadingmaterials2(fileName2);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        ProductResource p= new ProductResource();
	        p.setReadingmaterials1(product.getReadingmaterials1());
	        p.setFiledescription1(product.getFiledescription1());
	        p.setType1("pdf");
	        p.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        productservice.save(p);
	    	System.out.println("End : " );
			}
		}
          return "redirect:/product/add";
		
	}
//	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/datasheet/add")
	public String DatasheetAdd(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("datalist", dataservice.listAll());
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("AccountList", accountserivce
        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
        model.addAttribute("employee", employee);
		
    	return "Product_Resource/safetyDataSheet";
	}
	
	@PostMapping("/SaveAlldatasheet")
	public String SaveAlldatasheet(@ModelAttribute("datasheet") DataSheet datasheet, @RequestParam("dsreadingPdfFile1") MultipartFile[] dsreadingPdfFile1, 
			@RequestParam("dsreadingPdfFile2") MultipartFile[] dsreadingPdfFile2,
//			@RequestParam("readingPdfFile3") MultipartFile[] readingPdfFile3, @RequestParam("readingPdfFile4") MultipartFile[] readingPdfFile4,
//			@RequestParam("readingPdfFile5") MultipartFile[] readingPdfFile5, @RequestParam("readingPdfFile6") MultipartFile[] readingPdfFile6,
			@RequestParam("filedescription1") String dsfiledescription1,
			@RequestParam("filedescription2") String dsfiledescription2, HttpServletRequest request) throws IOException{
		for (MultipartFile file : dsreadingPdfFile1) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	       datasheet.setReadingmaterials1(fileName);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        DataSheet ds= new DataSheet();
	        ds.setReadingmaterials1(datasheet.getReadingmaterials1());
	        ds.setFiledescription1(datasheet.getFiledescription1());
	        ds.setType1("pdf");
	        ds.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        dataservice.save(ds);
	    	System.out.println("End : " );
			}
		}
		for (MultipartFile file : dsreadingPdfFile2) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        datasheet.setReadingmaterials1(fileName);
//	    	product.setReadingmaterials2(fileName2);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        DataSheet ds= new DataSheet();
	        ds.setReadingmaterials1(datasheet.getReadingmaterials1());
	        ds.setFiledescription1(datasheet.getFiledescription1());
	        ds.setType1("pdf");
	        ds.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        dataservice.save(ds);
	    	System.out.println("End : " );
			}
		}
          return "redirect:/datasheet/add";
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/casestudies/add")
	public String casestudiesAdd(Model model,HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Mainsidebarauthority> listmainsidebarauthority = mainsidebarauthorityService.Mainsidebarauthoritybyauthid(loginuserauthorityService.GetAuthorityID(loginuserService.GetAuthorityID(auth.getName())));
		model.addAttribute("listMainsidebarAuthority", listmainsidebarauthority);
		model.addAttribute("caseList", casestudiesservice.listAll());
		
		
		EmployeeDetails employee= employeeserivce.GetEmployeeDetailsByEmail(request.getUserPrincipal().getName());
//		model.addAttribute("AccountList", accountserivce
        model.addAttribute("EmployeeDeatilsList", employeeserivce.GetEmployeeDetailsByEmail(employee.getCompanyid()));
      	
        model.addAttribute("employee", employee);
		
    	return "Product_Resource/CaseStudies";
	}
	
	
	@PostMapping("/SaveAllCase")
	public String SaveAllProduct(@ModelAttribute("casestudies") CaseStudies casestudies, @RequestParam("csreadingPdfFile1") MultipartFile[] csreadingPdfFile1, 
			@RequestParam("csreadingPdfFile2") MultipartFile[] csreadingPdfFile2,
//			@RequestParam("readingPdfFile3") MultipartFile[] readingPdfFile3, @RequestParam("readingPdfFile4") MultipartFile[] readingPdfFile4,
//			@RequestParam("readingPdfFile5") MultipartFile[] readingPdfFile5, @RequestParam("readingPdfFile6") MultipartFile[] readingPdfFile6,
			@RequestParam("filedescription1") String filedescription1,
			@RequestParam("filedescription2") String filedescription2, HttpServletRequest request) throws IOException{
		for (MultipartFile file : csreadingPdfFile1) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	       casestudies.setReadingmaterials1(fileName);
//	    	product.setReadingmaterials2(fileName2);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        CaseStudies cs= new CaseStudies();
	        cs.setReadingmaterials1(casestudies.getReadingmaterials1());
	        cs.setFiledescription1(casestudies.getFiledescription1());
	        cs.setType1("pdf");
	        cs.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        casestudiesservice.save(cs);
	    	System.out.println("End : " );
			}
		}
		for (MultipartFile file : csreadingPdfFile2) {
			if(file.getSize() != 0) {
//	        Path path=Paths.get(imagepathservice.getFilePath("Image Folder"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			String connectionString = "DefaultEndpointsProtocol=https;AccountName=actifylms;AccountKey=z/aotcWF5Mh0fwhth3f91zVA/PutSOMI1V1QcdlgIqlFPO0f+P1xY5EBmps1TLzJCVjtc7xSim+W+AStBZCbcw==;EndpointSuffix=core.windows.net";
			String containerName = "product-resources";
			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
			
			String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());
//			String fileName2 = FilenameUtils.getBaseName(file.getOriginalFilename())+"." + FilenameUtils.getExtension(file.getOriginalFilename());   
	        BlobClient blobClient = containerClient.getBlobClient(fileName);
	        
	        BlobHttpHeaders jsonHeaders = new BlobHttpHeaders()
	                .setContentType(MediaType.APPLICATION_PDF_VALUE);
	            BinaryData data = BinaryData.fromStream(file.getInputStream());   
	            BlobParallelUploadOptions options = new BlobParallelUploadOptions(data)
	                .setRequestConditions(new BlobRequestConditions()).setHeaders(jsonHeaders);
	            blobClient.uploadWithResponse(options, null, Context.NONE);
	       System.out.println(" ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	       casestudies.setReadingmaterials1(fileName);
//	    	product.setReadingmaterials2(fileName2);
	        System.out.println("Start : " );
	    	
//	    	productservice.insertDataIntoProduct(product.getReadingmaterials1(), product.getFiledescription1(),"pdf");
	        CaseStudies cs= new CaseStudies();
	        cs.setReadingmaterials1(casestudies.getReadingmaterials1());
	        cs.setFiledescription1(casestudies.getFiledescription1());
	        cs.setType1("pdf");
	        cs.setEmployee_id(employeeserivce.GetIDByEmail(request.getUserPrincipal().getName()));
	        casestudiesservice.save(cs);
	    	System.out.println("End : " );
			}
		}
          return "redirect:/casestudies/add";
		
	}
	
}
