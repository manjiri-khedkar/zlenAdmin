package mp.procurement;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import mp.procurement.model.ReportBean;

public class ExcelHelper {
	
	public void getLotReport(HSSFWorkbook workbook, List<ReportBean> list_report){
		if (list_report!=null){
			int count = list_report.size()/50000;
			for (int i=0; i<=count;i++){
				if (((i+1)*50000)>list_report.size()){
					List<ReportBean> s1 =  list_report.subList(i*50000, list_report.size());
					createSheet(workbook, s1,i+1);
				}else{
					List<ReportBean> s1 = list_report.subList(i*50000, (i+1)*50000);
					createSheet(workbook, s1,i+1);
				}
			}
		}
	}
	public void createSheet(HSSFWorkbook workbook, List<ReportBean> list_report, int idx){
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Party Wise_"+ idx) ;
        
        sheet.setDefaultColumnWidth(10);
        sheet.setColumnWidth(0, 3000);
        //eet.setcolumnw
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
        
        
        CellStyle style_sideHeader = workbook.createCellStyle();
        style_sideHeader.setFillForegroundColor(HSSFColor.YELLOW.index);
        style_sideHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font1 = workbook.createFont();
        font1.setFontName("Arial");
        font1.setFontHeightInPoints( (short) 14);
        style_sideHeader.setWrapText(true);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        style_sideHeader.setFont(font1);
        
        int partyId=-1;
        int tenderId=-1;
		int cur_lotId=-1;
		int cur_rowNo=2;
		int columnCount=2;
		
		HSSFRow tableHeader=null;
		HSSFRow tableHeader_1=null;
		HSSFRow lotRow=null;
		
		for (ReportBean bean : list_report	){
			
			if (cur_lotId!=bean.getLot_id()){
				if (cur_lotId!=-1){
					// format the previous party table
					/*int header_row = tableHeader.getRowNum();
					CellRangeAddress region =  CellRangeAddress.valueOf("A"+header_row+":C"+cur_rowNo);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, workbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, workbook);*/
				}
				
				cur_lotId=bean.getLot_id();
				//cur_rowNo+=2;
				//HSSFRow aRow = sheet.createRow(cur_rowNo);
	            //aRow.createCell(0).setCellValue("Lot No. " +bean.getLot_name());
	            //aRow.getCell(0).setCellStyle(style_sideHeader);

				//cur_rowNo+=2;
				//partyId=-1;
			}
			
			if (partyId!=bean.getParty_id()  ){

				if (partyId==-1){
					
					//cur_rowNo++;
					//create a new Row for new Lot 
					tableHeader = sheet.createRow(cur_rowNo);
					cur_rowNo++;
					tableHeader_1 = sheet.createRow(cur_rowNo);

				}
				cur_rowNo++;
				lotRow = sheet.createRow(cur_rowNo);
				lotRow.createCell(0).setCellValue(bean.getDivision());
				lotRow.createCell(1).setCellValue(bean.getLot_name());
				lotRow.createCell(2).setCellValue(bean.getLot_given_name());
				lotRow.createCell(3).setCellValue(bean.getParty_name());
				
				
				//add lot name in column1
				partyId=bean.getParty_id();
			}
			
			int tmp_columnno = (int)((bean.getRnk()*3) + 1);
			if (tableHeader.getCell(tmp_columnno)==null){
				sheet.addMergedRegion(new CellRangeAddress(tableHeader.getRowNum(),tableHeader.getRowNum(),tmp_columnno,tmp_columnno+2));
				tableHeader.createCell(tmp_columnno).setCellValue(bean.getTender_name());
				tableHeader.getCell(tmp_columnno).setCellStyle(style_party);
				tableHeader.setHeight((short)300);
				tableHeader_1.createCell(tmp_columnno).setCellValue("Rate");
				tableHeader_1.createCell(tmp_columnno+1).setCellValue("Priority");
				tableHeader_1.createCell(tmp_columnno+2).setCellValue("Rank");
				tableHeader_1.getCell(tmp_columnno).setCellStyle(style_party);
				tableHeader_1.getCell(tmp_columnno+1).setCellStyle(style_party);
				tableHeader_1.getCell(tmp_columnno+2).setCellStyle(style_party);
				sheet.setColumnWidth(tmp_columnno,1792);
				sheet.setColumnWidth(tmp_columnno+1,1024);
				sheet.setColumnWidth(tmp_columnno+2,1024);
				//sheet.addMergedRegion(new CellRangeAddress(tableHeader.getRowNum(),tableHeader.getRowNum(),tmp_columnno,tmp_columnno+2));
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
		/*int lastColumn = (int)tableHeader_1.getLastCellNum();
		for (int itr=4;itr<=lastColumn;itr++){
			if (tableHeader_1.getCell(itr)==null){
				sheet.setColumnHidden(itr, true);
			}
		}*/
	
		if (tableHeader_1!=null){
		tableHeader_1.createCell(0).setCellValue("Division");
		tableHeader_1.createCell(1).setCellValue("Lot No");
		tableHeader_1.createCell(2).setCellValue("Lot Name");
		tableHeader_1.createCell(3).setCellValue("Party Name");
		}
		sheet.setColumnWidth(3, 7200);
		
		
	}

}

