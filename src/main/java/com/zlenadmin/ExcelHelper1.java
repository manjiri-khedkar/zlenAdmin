package com.zlenadmin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.zlenadmin.api.entity.UserDetails;
import com.zlenadmin.dao.Accounts;
import com.zlenadmin.dto.InactiveDto;
import com.zlenadmin.dto.RegisterPendingDto;

public class ExcelHelper1 {

	@Autowired
	private static Accounts accountDao;

	public static ByteArrayInputStream InActiveToExcel(List<InactiveDto> inActive) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("Inactive");
			// creating the 0th row using the createRow() method
			sheet.setDefaultColumnWidth(30);
			sheet.setColumnWidth(0, 3000);

			CreationHelper creationHelper = workbook.getCreationHelper();
			HSSFRow rowhead = sheet.createRow((short) 1);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("S.No.");
			rowhead.createCell(1).setCellValue("Name");
			rowhead.createCell(2).setCellValue("Number");
			rowhead.createCell(3).setCellValue("Last Seen Date");
			for (int i = 0; i < inActive.size(); i++) {

				HSSFRow row = sheet.createRow((short) i + 3);
				// inserting data in the first row
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(inActive.get(i).getName());
				row.createCell(2).setCellValue(inActive.get(i).getNumber());
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				String Date = date.format(inActive.get(i).getCdate());
				row.createCell(3).setCellValue(Date);
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// FileOutputStream fileOut = new FileOutputStream();
			// workbook.write(fileOut);
			// closing the Stream
			// fileOut.close();
			// closing the workbook
			workbook.write(out);
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");

			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream PendingToExcel(List<RegisterPendingDto> registerPending) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("RegisterPending");
			// creating the 0th row using the createRow() method
			sheet.setDefaultColumnWidth(30);
			sheet.setColumnWidth(0, 3000);

			CreationHelper creationHelper = workbook.getCreationHelper();
			HSSFRow rowhead = sheet.createRow((short) 1);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("S.No.");
			rowhead.createCell(1).setCellValue("Name");
			rowhead.createCell(2).setCellValue("Number");
			rowhead.createCell(3).setCellValue("Date");
			for (int i = 0; i < registerPending.size(); i++) {

				HSSFRow row = sheet.createRow((short) i + 3);
				// inserting data in the first row
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(registerPending.get(i).getName());
				row.createCell(2).setCellValue(registerPending.get(i).getNumber());
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				String Date = date.format(registerPending.get(i).getDate());
				row.createCell(3).setCellValue(Date);
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// FileOutputStream fileOut = new FileOutputStream();
			// workbook.write(fileOut);
			// closing the Stream
			// fileOut.close();
			// closing the workbook
			workbook.write(out);
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");

			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
	
	public static ByteArrayInputStream userDetailsToExcel(List<UserDetails> userDetails) {

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("UserDetails");
			// creating the 0th row using the createRow() method
			sheet.setDefaultColumnWidth(30);
			sheet.setColumnWidth(0, 3000);

			CreationHelper creationHelper = workbook.getCreationHelper();
			HSSFRow rowhead = sheet.createRow((short) 1);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("S.No.");
			rowhead.createCell(1).setCellValue("Create Date");
			rowhead.createCell(2).setCellValue("User Name");
			rowhead.createCell(3).setCellValue("Mobile No.");
			rowhead.createCell(4).setCellValue("Zlen Code");
			rowhead.createCell(5).setCellValue("Device Type");
			for (int i = 0; i < userDetails.size(); i++) {

				HSSFRow row = sheet.createRow((short) i + 3);
				// inserting data in the first row
				row.createCell(0).setCellValue(i + 1);
				SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
				String Date = date.format(userDetails.get(i).getCreatedOn());
				row.createCell(1).setCellValue(Date);
				//row.createCell(1).setCellValue(userDetails.get(i).getCreatedOn());
				row.createCell(2).setCellValue(userDetails.get(i).getUserName());
				row.createCell(3).setCellValue(userDetails.get(i).getUserMobile());
				row.createCell(4).setCellValue(userDetails.get(i).getZlenCode());
				row.createCell(5).setCellValue(userDetails.get(i).getDeviceType());
				
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// FileOutputStream fileOut = new FileOutputStream();
			// workbook.write(fileOut);
			// closing the Stream
			// fileOut.close();
			// closing the workbook
			workbook.write(out);
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");

			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
