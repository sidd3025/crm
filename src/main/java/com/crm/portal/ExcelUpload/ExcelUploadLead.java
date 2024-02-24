package com.crm.portal.ExcelUpload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.crm.portal.Accounts.AccountService;
import com.crm.portal.Contacts.Contact;
import com.crm.portal.Lead.Lead;
import com.crm.portal.Lead.LeadService;

public class ExcelUploadLead {

	@Autowired
	private LeadService leadService;
	
	public String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//  String[] HEADERs = {"Course Name, Topic Name, Subtopic Name, Question , option1, option2, option3, option4, answer, level"};
//  String SHEET = "Quiz";

  public boolean hasExcelFormat(MultipartFile file) {
      if (!TYPE.equals(file.getContentType())) {
          return false;
      }
      return true;
  }

  public void excelToTutorials(InputStream is) {
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
              String accountname = getCellValueAsString(row.getCell(1));
              String email = getCellValueAsString(row.getCell(2));
              String phone = getCellValueAsString(row.getCell(3));
              String leadowner = getCellValueAsString(row.getCell(4));
              
              Lead l= new Lead();
//              l.setFirstname(fisrtName);
//              l.setAccountname(accountname );
//              l.setEmail(email );
//              l.setPhone(phone);
              l.setLeadowner(leadowner);
              
//              l.setPhone_number(phonenumber);
//              l.setContactowner(Contactowner);
//              person.setName(currentRow.getCell(0).getStringCellValue());
//              person.setEmail(currentRow.getCell(1).getStringCellValue());
              leadService.save(l);

          }
          
      }
      catch (IOException e1) {
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
}


