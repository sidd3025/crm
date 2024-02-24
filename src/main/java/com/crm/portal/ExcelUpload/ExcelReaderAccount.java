package com.crm.portal.ExcelUpload;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.crm.portal.Accounts.Account;
import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Contacts.Contact;

@Component
public class ExcelReaderAccount {

	
	@Autowired
	private AccountService accService;
	
	public String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//  String[] HEADERs = {"Course Name, Topic Name, Subtopic Name, Question , option1, option2, option3, option4, answer, level"};
//  String SHEET = "Quiz";

  public boolean hasExcelFormat(MultipartFile file) {
      if (!TYPE.equals(file.getContentType())) {
          return false;
      }
      return true;
  }

  public void excelToTutorials1(InputStream is, String c1,Long employee_id) {
  	try (Workbook workbook = new XSSFWorkbook(is)) {
          Sheet sheet = workbook.getSheetAt(0);
          Iterator<Row> iterator = sheet.iterator();

          for (Row row : sheet) {
              // Skip the header row
              if (row.getRowNum() == 0) {
                  continue;
              }

              // Extract values from Excel
  
              String accountname = getCellValueAsString(row.getCell(0));
              String industry = getCellValueAsString(row.getCell(1));
              String website = getCellValueAsString(row.getCell(2));
              String address = getCellValueAsString(row.getCell(3));
              String city = getCellValueAsString(row.getCell(4));
              String phonenumber1 = getCellValueAsString(row.getCell(5));
              String phonenumber2 = getCellValueAsString(row.getCell(6));
              String mobilenumber1 = getCellValueAsString(row.getCell(7));
              String mobilenumber2 = getCellValueAsString(row.getCell(8));
             
              String email = getCellValueAsString(row.getCell(9));   
              String email2 = getCellValueAsString(row.getCell(10));   
              String description = getCellValueAsString(row.getCell(11));
              String remarks = getCellValueAsString(row.getCell(12));
              LocalDateTime mydate = LocalDateTime.now();
    		  DateTimeFormatter myformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
              
    		  if(accService.GetIDCount(accountname)==0) {
              Account a= new Account();
           
              a.setAccountname(accountname);
              a.setIndustry(industry);
              a.setWebsite(website);
              a.setAddresslin1(address);
              a.setCity(city);
              a.setPhone(phonenumber1);
              a.setMobile(mobilenumber1);
              a.setMobile2(mobilenumber2);
              a.setPhone2(phonenumber2);
              a.setEmail(email);
              a.setEmail2(email2);
              a.setEmployee_id(employee_id);
              a.setAccoutowner(c1);
              a.setCurrentdate(mydate.format(myformatter)); 

              accService.save(a);

    		  }
    		  else {
    			  System.out.println("Account Name : " + accountname);
    		  }
          }
          
      }
      catch (Exception e1) {
          throw new RuntimeException("Failed to parse Excel file: " + e1.getMessage());
      }
    
  }
  
  private Integer getCellValueAsInteger(Cell cell) {
      switch (cell.getCellType()) {
          case NUMERIC:
              // Convert the numeric value to an integer
              return (int) cell.getNumericCellValue();
          case BLANK:
              // Handle blank cells as needed
              return null; // You can return null or handle it differently
          default:
              // Handle other data types or raise an error
              throw new RuntimeException("Unsupported cell type in the cell: " + cell.getCellType());
      }
  }

	public String getCellValueAsString(Cell cell) {
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

//	public void excelreaderacc(InputStream inputStream, String accountowner) {
//		// TODO Auto-generated method stub
//		
//	}
}




