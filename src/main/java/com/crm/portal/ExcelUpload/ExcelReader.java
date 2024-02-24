package com.crm.portal.ExcelUpload;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.crm.portal.Contacts.Contact;
import com.crm.portal.Contacts.ContactService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelReader {
	
	@Autowired
	private ContactService contactservice;
	
	

	public String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    String[] HEADERs = {"Course Name, Topic Name, Subtopic Name, Question , option1, option2, option3, option4, answer, level"};
//    String SHEET = "Quiz";

    public boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public void excelToTutorials(InputStream is,String c1,Long employee_id) {
    	try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            for (Row row : sheet) {
                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Extract values from Excel
                String fisrtName = getCellValueAsString(row.getCell(0));
                String lastname = getCellValueAsString(row.getCell(1));
                String email = getCellValueAsString(row.getCell(2));
                String title = getCellValueAsString(row.getCell(3));
                String accountname = getCellValueAsString(row.getCell(4));
                String mobile1 = getCellValueAsString(row.getCell(5));
                String mobile2= getCellValueAsString(row.getCell(6));
                String phonenumber1 = getCellValueAsString(row.getCell(7));
                String phonenumber2 = getCellValueAsString(row.getCell(8));
                String industry= getCellValueAsString(row.getCell(9));
                String source = getCellValueAsString(row.getCell(10));
                String executive_level = getCellValueAsString(row.getCell(11));
                String department = getCellValueAsString(row.getCell(12));
                String linked =getCellValueAsString(row.getCell(13));
                String twitter = getCellValueAsString(row.getCell(14));
                String add1 = getCellValueAsString(row.getCell(15));
                String city = getCellValueAsString(row.getCell(16));
                String state = getCellValueAsString(row.getCell(17));
                String country = getCellValueAsString(row.getCell(18));
                String pincode = getCellValueAsString(row.getCell(19));
                String email2 = getCellValueAsString(row.getCell(20));
                
                LocalDateTime mydate = LocalDateTime.now();
    		    DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
             
    		    if(contactservice.GetContactIDCount(email)==0) {
    		    
    		    Contact c= new Contact();
                c.setFirst_name(fisrtName);
                c.setLast_name(lastname);
                c.setEmail(email);
                c.setContactowner(c1);
//                c.setContact_owner(Contact_owner);
                c.setTitle(title);
                c.setAccountname(accountname);
                c.setMobile_number(mobile1);
                c.setMobile_number2(mobile2);
                c.setPhone_number2(phonenumber2);
                c.setPhone_number(phonenumber1);
                c.setIndustry(industry);
                c.setContactsource(source);
                c.setExecutive_level(executive_level);
                c.setDepartment(department);
                c.setLinkedin_bio(linked);
                c.setTwitter(twitter);
                c.setAddresslin1(add1);
                c.setCity(city);
                c.setState(state);
                c.setCountry(country);
                c.setPincode(pincode);
                c.setCurrentdate_time(mydate.format(myformatter));                
                c.setEmployee_id(employee_id); 
                c.setSecondaryemail(email2);
                
                //                c.setAccountname(accountnumber );
//                c.setEmail(email );
//                c.setPhone_number(phonenumber);
//                c.setContactowner(Contactowner);
//                person.setName(currentRow.getCell(0).getStringCellValue());
//                person.setEmail(currentRow.getCell(1).getStringCellValue());
                contactservice.save(c);

    		    }
    		    else {
      			  System.out.println("Contact Email : " + email);
      		  }
            }
            
        }
        catch (IOException e1) {
            throw new RuntimeException("Failed to parse Excel file: " + e1.getMessage());
        }

        
    }
//    private Integer getCellValueAsInteger(Cell cell) {
//        switch (cell.getCellType()) {
//            case NUMERIC:
//                // Convert the numeric value to an integer
//                return (int) cell.getNumericCellValue();
//            case BLANK:
//                // Handle blank cells as needed
//                return null; // You can return null or handle it differently
//            default:
//                // Handle other data types or raise an error
//                throw new RuntimeException("Unsupported cell type in the cell: " + cell.getCellType());
//        }
//    }

    public String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // or handle it in a way that makes sense for your application
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Handle numeric values (e.g., convert to string)
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                // Handle boolean values (e.g., convert to string)
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                // Handle blank cells as needed
                return "";
            default:
                // Handle other data types or raise an error
                throw new RuntimeException("Unsupported cell type in the cell: " + cell.getCellType());
        }
    }
}


	
//	 public static List<Contact> readExcel(InputStream is,String owner) throws IOException {
//	        List<Contact> persons = new ArrayList<>();
//
//	        try (Workbook workbook = new XSSFWorkbook(is)) {
//	            Sheet sheet = workbook.getSheetAt(0);
//	            Iterator<Row> iterator = sheet.iterator();
//
//	            while (iterator.hasNext()) {
//	                Row currentRow = iterator.next();
//	                if (currentRow.getRowNum() == 0) {
//	                    // Skip header row
//	                    continue;
//	                }
//
//	                Contact c= new Contact();
//	                c.setFirstname( currentRow.getCell(0).getStringCellValue());
//	                c.setAccountname( currentRow.getCell(1).getStringCellValue());
//	                c.setEmail( currentRow.getCell(2).getStringCellValue());
//	                c.setPhone_number( currentRow.getCell(3).getStringCellValue());
//	                c.setContactowner(owner);
////	                person.setName(currentRow.getCell(0).getStringCellValue());
////	                person.setEmail(currentRow.getCell(1).getStringCellValue());
//	                persons.add(c);
//	            }
//	        }
//	        catch(Exception e) {
//	        	
//	        }
//
//	        return persons;
//	    }
//	
//	
//}
