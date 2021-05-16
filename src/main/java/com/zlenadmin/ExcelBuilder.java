package com.zlenadmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.zlenadmin.model.ReportBean;

@Component("excelView")
public class ExcelBuilder extends AbstractXlsView {
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
		try{
			
			this.setContentType("application/msexcel");
			SimpleDateFormat sdf1 = new SimpleDateFormat("ddmmyyyyhhMMss");
			String header = String.format( "attachment; filename=\"Party_%s.xls\"",sdf1.format(new Date()));
	        response.setHeader("Content-Disposition",header);
        List<ReportBean> list_report = (List<ReportBean>) model.get("reportBean");
        
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Party Wise");
        sheet.setDefaultColumnWidth(10);
        
       
        
        
        Font party_font = workbook.createFont();
        party_font.setFontName("Arial");
        party_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        HSSFCellStyle style_partyname =(HSSFCellStyle) workbook.createCellStyle();
        
        
        style_partyname.setFont(party_font);
        style_partyname.setWrapText(true);
        
        // create style for header cells
        HSSFCellStyle style_party = (HSSFCellStyle)workbook.createCellStyle();
        HSSFCellStyle style_lot = (HSSFCellStyle) workbook.createCellStyle();
        style_lot.setWrapText(true);
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style_party.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style_party.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style_party.setWrapText(true);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style_party.setFont(font);
        
        sheet.setColumnWidth(1, 6000);
        
        HSSFCellStyle style_selected = (HSSFCellStyle) workbook.createCellStyle();
        style_selected.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style_selected.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        int partyId=-1;
		int cur_lotId=-1;
		int cur_rowNo=2;
		int columnCount=2;
		
		HSSFRow tableHeader=null;
		
		HSSFRow lotRow=null;
		tableHeader = (HSSFRow)sheet.createRow(cur_rowNo);
		tableHeader.setRowStyle(style_party);
		//tableHeader.setHeightInPoints((float)4.0);
		tableHeader.setHeightInPoints((8 * sheet.getDefaultRowHeightInPoints()));
		for (ReportBean bean : list_report	){
			
			if (partyId!=bean.getParty_id()){
				if (partyId!=-1){
					// format the previous party table
					/*int header_row = tableHeader.getRowNum();
					CellRangeAddress region =  CellRangeAddress.valueOf("A"+header_row+":C"+cur_rowNo);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, workbook);*/
				}
				
				partyId=bean.getParty_id();
				cur_rowNo+=2;
				HSSFRow aRow = (HSSFRow)sheet.createRow(cur_rowNo);
	            aRow.createCell(0).setCellValue(bean.getParty_name());
	            aRow.getCell(0).setCellStyle(style_partyname);
	            sheet.addMergedRegion(new CellRangeAddress(cur_rowNo,cur_rowNo,0,1));
				//cur_rowNo+=1;
				cur_lotId=-1;
			}
			
			if (cur_lotId!=bean.getLot_id()){

				if (cur_lotId==-1){
					
					//cur_rowNo++;
					//create a new Row for new Lot 
					//tableHeader = sheet.createRow(cur_rowNo);

				}
				
				cur_rowNo++;
				//cur_rowNo++;
				//cur_rowNo++;
				lotRow = (HSSFRow) sheet.createRow(cur_rowNo);
				lotRow.createCell(0).setCellValue(bean.getLot_name());
				lotRow.createCell(1).setCellValue(bean.getLot_given_name());
				lotRow.getCell(1).setCellStyle(style_lot);
				//add lot name in column1
				cur_lotId=bean.getLot_id();
				
			}
			
			int tmp_columnno = (int)(bean.getRnk()*3 + 1);
			if (tableHeader.getCell(tmp_columnno)==null){
				tableHeader.createCell(tmp_columnno).setCellValue(bean.getTender_name());
				
				tableHeader.getCell(tmp_columnno).setCellStyle(style_party);
				sheet.addMergedRegion(new CellRangeAddress(tableHeader.getRowNum(),tableHeader.getRowNum(),tmp_columnno,tmp_columnno+2));
			}
			lotRow.createCell(tmp_columnno).setCellValue(bean.getRate());
			lotRow.createCell(tmp_columnno+1).setCellValue(bean.getPriority());
			lotRow.createCell(tmp_columnno+2).setCellValue(bean.getRank());
			if (bean.isIs_predicted()!=null && bean.isIs_predicted()){
				lotRow.getCell(tmp_columnno).setCellStyle(style_selected);
				lotRow.getCell(tmp_columnno+1).setCellStyle(style_selected);
				lotRow.getCell(tmp_columnno+2).setCellStyle(style_selected);
			}
			
		}
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
}
