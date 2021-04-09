package mp.procurement;


import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import mp.procurement.model.ReportBean;

@Component("tenderResult")
public class TenderResult extends AbstractXlsView {
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
		try{
        List<ReportBean> list_report = (List<ReportBean>) model.get("reportBean");
        String tenderName = (String)model.get("tenderName");
        // create a new Excel sheet
        response.setHeader("Content-Disposition", "attachment; filename=\"tender_result.xls\"");
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Party Wise");
        sheet.setDefaultColumnWidth(10);
        
        
        // create style for header cells
        CellStyle style_party = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style_party.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
        style_party.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style_party.setWrapText(true);
        style_party.setFont(font);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        
        Font bold_font = workbook.createFont();
        bold_font.setFontName("Arial");
        bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        CellStyle style_grey = workbook.createCellStyle();
        
        style_grey.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style_grey.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style_grey.setWrapText(true);
        style_grey.setFont(bold_font);
        
        
        CellStyle style_selected = workbook.createCellStyle();
        style_selected.setFillForegroundColor(HSSFColor.GREEN.index);
        style_selected.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        
        
        CellStyle style_bold = workbook.createCellStyle();
        style_bold.setFont(bold_font);
        
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        
        
        
        //XSSFColor header = new XSSFColor(new Color(138, 19, 25));
        Font infosane_font = workbook.createFont();
        infosane_font.setFontName("Arial");
        infosane_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        infosane_font.setFontHeightInPoints((short)24);  
        
        CellStyle style_infosane = workbook.createCellStyle();
        style_infosane.setFont(infosane_font);
        
        int partyId=-1;
		int cur_lotId=-1;
		int cur_rowNo=2;
		int columnCount=2;
		int srno=1;
		HSSFRow tableHeader=null;
		HSSFRow lotRow=null;
		int totalQty=0;
		int partyCount=0;
		double totalAmt =0.0;
		double totalRate =0.0;
		
		sheet.setMargin(sheet.TopMargin, 0);
		
		HSSFRow headerRow = sheet.createRow(cur_rowNo++);
		headerRow.createCell(6).setCellValue("Infosane");
		headerRow.getCell(6).setCellStyle(style_infosane);
		headerRow.setHeightInPoints(30);
		
		
		HSSFRow addressRow = sheet.createRow(cur_rowNo++);
		addressRow.createCell(6).setCellValue("Nagpur\nCell: 89890004000, 7000384248\nEmail: vivek@infosane.co.in");
		addressRow.setHeightInPoints(35);
		//addressRow.getCell(8).setCellStyle(style_infosane);
		
		CellStyle tenderStyle = workbook.createCellStyle();
		tenderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tenderStyle.setFont(bold_font);
		
		HSSFRow tenderRow = sheet.createRow(cur_rowNo);
		tenderRow.createCell(0).setCellValue(tenderName);
		tenderRow.getCell(0).setCellStyle(tenderStyle);
		sheet.addMergedRegion(new CellRangeAddress(cur_rowNo, cur_rowNo, 0, 8));
		
		cur_rowNo++;
		workbook.setRepeatingRowsAndColumns(0, 1, 8, 2, 3);
		
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
					cur_rowNo++;
					HSSFRow totalRow = sheet.createRow(cur_rowNo);
					totalRow.createCell(3).setCellValue("Total");
					totalRow.createCell(4).setCellValue(totalQty);
					totalRow.createCell(8).setCellValue(totalAmt);
					if (partyCount>0)
						totalRow.createCell(5).setCellValue(String.format("Avg Rate: %.2f", (totalRate/partyCount)));
					
					totalRow.createCell(0).setCellStyle(style_grey);
					totalRow.createCell(1).setCellStyle(style_grey);
					totalRow.createCell(2).setCellStyle(style_grey);
					
					totalRow.createCell(6).setCellStyle(style_grey);
					totalRow.createCell(7).setCellStyle(style_grey);
					
					totalRow.getCell(3).setCellStyle(style_grey);
					totalRow.getCell(4).setCellStyle(style_grey);
					totalRow.getCell(5).setCellStyle(style_grey);
					totalRow.getCell(8).setCellStyle(style_grey);
					sheet.addMergedRegion(new CellRangeAddress(cur_rowNo, cur_rowNo, 5, 6));
					
					totalQty=0;
					partyCount=0;
					totalRate=0.0;
					totalAmt=0.0;
				}
				//cur_rowNo+=1;
				partyId=bean.getParty_id();
				
				cur_rowNo+=2;
				HSSFRow aRow = sheet.createRow(cur_rowNo);
	            aRow.createCell(0).setCellValue(bean.getParty_name());
	            aRow.getCell(0).setCellStyle(style_bold);
	            
	            aRow.createCell(4).setCellValue("Pur Cap");
	            aRow.createCell(5).setCellValue(bean.getPurchase_capacity());
	            aRow.getCell(4).setCellStyle(style_bold);
	            aRow.getCell(5).setCellStyle(style_bold);
	            
	            aRow.createCell(7).setCellValue("Bal Cap");
	            aRow.createCell(8).setCellValue(bean.getPurchase_capacity()-bean.getConsumed_capacity());
	            aRow.getCell(7).setCellStyle(style_bold);
	            aRow.getCell(8).setCellStyle(style_bold);
				//cur_rowNo+=2;
				cur_lotId=-1;
			}
			
			if (cur_lotId!=bean.getLot_id()){

				if (cur_lotId==-1){
					
					cur_rowNo++;
					//create a new Row for new Lot 
					tableHeader = sheet.createRow(cur_rowNo);

				}
				cur_rowNo++;
				lotRow = sheet.createRow(cur_rowNo);
				lotRow.createCell(1).setCellValue(bean.getLot_given_name());
				lotRow.createCell(2).setCellValue(bean.getLot_name());
				lotRow.createCell(3).setCellValue(bean.getDivision());
				
				lotRow.getCell(1).setCellStyle(cellStyle);
				lotRow.getCell(2).setCellStyle(cellStyle);
				lotRow.getCell(3).setCellStyle(cellStyle);
				
				
				//add lot name in column1
				cur_lotId=bean.getLot_id();
			}
			
			int tmp_columnno = 2;
			if (tableHeader.getCell(3)==null){
				
				tableHeader.createCell(0).setCellValue("SrNo");
				tableHeader.getCell(0).setCellStyle(style_party);
				
				tableHeader.createCell(1).setCellValue("Lot Name");
				tableHeader.getCell(1).setCellStyle(style_party);
				
				tableHeader.createCell(2).setCellValue("Lot No");
				tableHeader.getCell(2).setCellStyle(style_party);
				
				tableHeader.createCell(3).setCellValue("Division");
				tableHeader.getCell(3).setCellStyle(style_party);
				
				tableHeader.createCell(4).setCellValue("Quantity");
				tableHeader.getCell(4).setCellStyle(style_party);
				
				tableHeader.createCell(5).setCellValue("Rate");
				tableHeader.getCell(5).setCellStyle(style_party);
				
				tableHeader.createCell(6).setCellValue("Rank");
				tableHeader.getCell(6).setCellStyle(style_party);
				
				tableHeader.createCell(7).setCellValue("Priority");
				tableHeader.getCell(7).setCellStyle(style_party);
				
				tableHeader.createCell(8).setCellValue("Amount");
				tableHeader.getCell(8).setCellStyle(style_party);
				
				
			}
			
			totalQty+=bean.getQty();
			totalAmt += bean.getAmount();
			totalRate += bean.getRate();
			partyCount++;
			lotRow.createCell(4).setCellValue(bean.getQty());
			lotRow.createCell(5).setCellValue(bean.getRate());
			lotRow.createCell(6).setCellValue(bean.getRank());
			lotRow.createCell(7).setCellValue(bean.getPriority());
			lotRow.createCell(8).setCellValue(bean.getAmount());
			lotRow.createCell(0).setCellValue(srno++);
			lotRow.getCell(0).setCellStyle(cellStyle);
			lotRow.getCell(4).setCellStyle(cellStyle);
			lotRow.getCell(5).setCellStyle(cellStyle);
			lotRow.getCell(6).setCellStyle(cellStyle);
			lotRow.getCell(7).setCellStyle(cellStyle);
			lotRow.getCell(8).setCellStyle(cellStyle);
		}
		}catch(Exception ex){
			System.out.println(ex);
		}
    }
}
