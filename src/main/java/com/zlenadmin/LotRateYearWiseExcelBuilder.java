package com.zlenadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;
//@Component("lotRateYearWiseExcel")
public class LotRateYearWiseExcelBuilder extends AbstractExcelView {
	
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
    		HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
		try{
			
		response.setHeader("Content-Disposition", "attachment; filename=\"lot_report.xls\"");
        List<HashMap<String,Object>> list_report = (List<HashMap<String,Object>>) model.get("data");
        
        
        // create a new Excel sheet
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Party Wise");
        sheet.setDefaultColumnWidth(10);
        
        // create style for header cells
        CellStyle style_party = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style_party.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style_party.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style_party.setWrapText(true);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style_party.setFont(font);
        
        CellStyle style_selected = workbook.createCellStyle();
        style_selected.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style_selected.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        
        CellStyle style_bold = workbook.createCellStyle();
        Font font_bold = workbook.createFont();
        font_bold.setFontName("Arial");
        font_bold.setColor(HSSFColor.BLACK.index);
        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //font.setColor(HSSFColor.WHITE.index);
        style_bold.setFont(font_bold);
        
        int partyId=-1;
		int cur_lotId=-1;
		int cur_rowNo=2;
		int columnCount=2;
		
		HSSFRow tableHeader=null;
		HSSFRow tableHeader_1=null;
		HSSFRow lotRow=null;
		HSSFRow aRow =null;
		HashMap<Integer,Double> roundRates = new HashMap<Integer, Double>();
		int baseYear = 1900 + new Date().getYear();
		for (HashMap<String,Object> bean : list_report	){
			
			boolean lot_change=false;
			
			if (cur_lotId!=(Integer)bean.get("lot_id")){
				lot_change=true;
				if (cur_lotId==-1){

					
					cur_rowNo++;
					//create a new Row for new Lot 
					tableHeader_1 = sheet.createRow(cur_rowNo);
					cur_rowNo++;
					tableHeader = sheet.createRow(cur_rowNo);
					
					partyId=-1;

				}
				cur_rowNo++;
				//cur_rowNo++;
				//cur_rowNo++;
				lotRow = sheet.createRow(cur_rowNo);
				lotRow.createCell(0).setCellValue(bean.get("lot_name").toString());
				lotRow.createCell(1).setCellValue(bean.get("lot_given_name").toString());
				lotRow.getCell(0).setCellStyle(style_bold);
				lotRow.getCell(1).setCellStyle(style_bold);
				//add lot name in column1
				cur_lotId=(Integer)bean.get("lot_id");
				roundRates =new HashMap<Integer, Double>();
			}
			
			
			int round = (Integer)bean.get("round");
			int year = (Integer)bean.get("year");
			double rate = (Double)bean.get("rate");
			String party = bean.get("party_name").toString();
			
			int tmp_columnno = (int)((((baseYear-year))*3) + 3);
			if (tableHeader.getCell(tmp_columnno)==null){
				//tableHeader.createCell(tmp_columnno).setCellValue(bean.getTender_name());
				
				sheet.addMergedRegion(new CellRangeAddress(tableHeader_1.getRowNum(),tableHeader_1.getRowNum(),tmp_columnno,tmp_columnno+2));
				tableHeader_1.createCell(tmp_columnno).setCellValue("Year-"+year);
				tableHeader_1.getCell(tmp_columnno).setCellStyle(style_party);
				tableHeader_1.setHeight((short)300);
				
				tableHeader.createCell(tmp_columnno).setCellValue("Rate");
				tableHeader.createCell(tmp_columnno+1).setCellValue("Diff");
				tableHeader.createCell(tmp_columnno+2).setCellValue("Party");
				//tableHeader.createCell(tmp_columnno+3).setCellValue("Capacity");
				tableHeader.getCell(tmp_columnno).setCellStyle(style_party);
				tableHeader.getCell(tmp_columnno+1).setCellStyle(style_party);
				tableHeader.getCell(tmp_columnno+2).setCellStyle(style_party);
				//tableHeader.getCell(tmp_columnno+3).setCellStyle(style_party);
				//sheet.setColumnWidth(tmp_columnno+2, 70);
				
				
			}
			
			
			lotRow.createCell(tmp_columnno).setCellValue(rate);
			roundRates.put(year, rate);
			Double prevRoundRate = roundRates.getOrDefault(year-1, null);
			if (prevRoundRate!=null){
				lotRow.createCell(tmp_columnno+1).setCellValue(((rate-prevRoundRate)/prevRoundRate)*100);
			}
			
			lotRow.createCell(tmp_columnno+2).setCellValue(party);
			//sheet.autoSizeColumn(tmp_columnno+2);
			//aRow.createCell(tmp_columnno+1).setCellValue(bean.getPriority());
			/*if (bean.getRank()!=null)
				aRow.createCell(tmp_columnno+2).setCellValue(bean.getRank());
			aRow.createCell(tmp_columnno+3).setCellValue(bean.getCapacity());*/
			
			/*if (bean.isIs_predicted()!=null && bean.isIs_predicted()){
				aRow.getCell(tmp_columnno).setCellStyle(style_selected);
				//aRow.getCell(tmp_columnno).setCellStyle(style_selected);
				aRow.getCell(tmp_columnno+1).setCellStyle(style_selected);
				aRow.getCell(tmp_columnno+2).setCellStyle(style_selected);
			}*/
			
		}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex);
		}
		
		autoSizeColumns(workbook);
    }
	
	public void autoSizeColumns(Workbook workbook) {
	    int numberOfSheets = workbook.getNumberOfSheets();
	    for (int i = 0; i < numberOfSheets; i++) {
	        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(i);
	        if (sheet.getPhysicalNumberOfRows() > 0) {
	            org.apache.poi.ss.usermodel.Row row = sheet.getRow(sheet.getFirstRowNum());
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                sheet.autoSizeColumn(columnIndex);
	            }
	        }
	    }
	}
}

