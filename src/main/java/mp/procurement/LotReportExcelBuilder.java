package mp.procurement;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import mp.procurement.model.ReportBean;

@Component("lotReportExcel")
public class LotReportExcelBuilder extends AbstractXlsView {
	
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
		try{
			
		response.setHeader("Content-Disposition", "attachment; filename=\"lot_report.xls\"");
        List<ReportBean> list_report = (List<ReportBean>) model.get("reportBean");
        
        
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
        style_party.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_party.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_party.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_party.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        CellStyle style_selected = workbook.createCellStyle();
        style_selected.setFillForegroundColor(HSSFColor.YELLOW.index);
        style_selected.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style_selected.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_selected.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_selected.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_selected.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        
        Font font_normal = workbook.createFont();
        font_normal.setFontName("Arial");
        font_normal.setColor(HSSFColor.BLACK.index);
        CellStyle style_border = workbook.createCellStyle();
        style_border.setFont(font_normal);
        style_border.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        
        CellStyle style_bold = workbook.createCellStyle();
        Font font_bold = workbook.createFont();
        font_bold.setFontName("Arial");
        font_bold.setColor(HSSFColor.BLACK.index);
        font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //font.setColor(HSSFColor.WHITE.index);
        style_bold.setFont(font_bold);
        
        int partyId=-1;
		String cur_name=null;
		int cur_rowNo=2;
		int columnCount=2;
		
		Font infosane_font = workbook.createFont();
        infosane_font.setFontName("Arial");
        infosane_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        infosane_font.setFontHeightInPoints((short)24);  
        
        CellStyle style_infosane = workbook.createCellStyle();
        style_infosane.setFont(infosane_font);
        
        Font bold_font = workbook.createFont();
        bold_font.setFontName("Arial");
        bold_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
		HSSFRow tableHeader=null;
		HSSFRow tableHeader_1=null;
		HSSFRow lotRow=null;
		HSSFRow aRow =null;
		
		sheet.setMargin(sheet.TopMargin, 0);
		
		HSSFRow headerRow = sheet.createRow(cur_rowNo++);
		headerRow.createCell(3).setCellValue("Infosane");
		headerRow.getCell(3).setCellStyle(style_infosane);
		headerRow.setHeightInPoints(30);
		
		
		HSSFRow addressRow = sheet.createRow(cur_rowNo++);
		addressRow.createCell(3).setCellValue("Nagpur\nCell: 89890004000, 7000384248\nEmail: vivek@infosane.co.in");
		addressRow.setHeightInPoints(35);
		//addressRow.getCell(8).setCellStyle(style_infosane);
		
		CellStyle tenderStyle = workbook.createCellStyle();
		tenderStyle.setAlignment(CellStyle.ALIGN_CENTER);
		tenderStyle.setFont(bold_font);
		
//		HSSFRow tenderRow = sheet.createRow(cur_rowNo);
//		tenderRow.createCell(0).setCellValue(tenderName);
//		tenderRow.getCell(0).setCellStyle(tenderStyle);
//		sheet.addMergedRegion(new CellRangeAddress(cur_rowNo, cur_rowNo, 0, 8));
		
		cur_rowNo++;
		workbook.setRepeatingRowsAndColumns(0, 1, 5, 2, 3);
		boolean createHeading = false;
		for (ReportBean bean : list_report	){
			
			boolean lot_change=false;
			int tmp_columnno = (int)(((bean.getRnk()-1)*4) + 1);
			if (!bean.getLot_name().equals(cur_name)){
				lot_change=true;
				if (cur_name==null){
					
					cur_rowNo++;
					//create a new Row for new Lot 
					tableHeader_1 = sheet.createRow(cur_rowNo);
					cur_rowNo++;
					tableHeader = sheet.createRow(cur_rowNo);
					
					partyId=-1;

				}
				cur_rowNo++;
				cur_rowNo++;
				cur_rowNo++;
				lotRow = sheet.createRow(cur_rowNo);
				sheet.addMergedRegion(new CellRangeAddress(cur_rowNo,cur_rowNo,0,4));
				lotRow.createCell(0).setCellValue(String.format("Lot No: %s,    Name: %s,    Division: %s " , bean.getLot_name(),bean.getLot_given_name(), bean.getDivision()));
				//lotRow.createCell(1).setCellValue(bean.getLot_given_name());
				lotRow.getCell(0).setCellStyle(style_bold);
				//lotRow.getCell(1).setCellStyle(style_bold);
				//add lot name in column1
				cur_name = bean.getLot_name();
				
				cur_rowNo++;
				HSSFRow headingRow = sheet.createRow(cur_rowNo);
				headingRow.createCell(tmp_columnno).setCellValue("Rate");
				headingRow.createCell(tmp_columnno+1).setCellValue("Priority");
				headingRow.createCell(tmp_columnno+2).setCellValue("Rank");
				headingRow.createCell(tmp_columnno+3).setCellValue("Capacity");
				
				headingRow.getCell(tmp_columnno).setCellStyle(style_party);
				headingRow.getCell(tmp_columnno+1).setCellStyle(style_party);
				headingRow.getCell(tmp_columnno+2).setCellStyle(style_party);
				headingRow.getCell(tmp_columnno+3).setCellStyle(style_party);
				
				
			}
			
			if (partyId!=bean.getParty_id() || lot_change){
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
				cur_rowNo+=1;
				 aRow = sheet.createRow(cur_rowNo);
	            aRow.createCell(0).setCellValue(bean.getParty_name());
	            aRow.getCell(0).setCellStyle(style_border);
				/*cur_rowNo+=2;
				cur_lotId=-1;*/
			}
			
			
			if (tableHeader.getCell(tmp_columnno)==null){
				//tableHeader.createCell(tmp_columnno).setCellValue(bean.getTender_name());
				
				sheet.addMergedRegion(new CellRangeAddress(tableHeader_1.getRowNum(),tableHeader_1.getRowNum(),tmp_columnno,tmp_columnno+2));
				tableHeader_1.createCell(tmp_columnno).setCellValue(bean.getTender_name());
				tableHeader_1.getCell(tmp_columnno).setCellStyle(style_party);
				tableHeader_1.setHeight((short)300);
				
				
			}
			
			
			aRow.createCell(tmp_columnno).setCellValue(bean.getRate());
			aRow.createCell(tmp_columnno+1).setCellValue(bean.getPriority());
			if (bean.getRank()!=null)
				aRow.createCell(tmp_columnno+2).setCellValue(bean.getRank());
			aRow.createCell(tmp_columnno+3).setCellValue(bean.getCapacity());
			
			if (bean.isIs_predicted()!=null && bean.isIs_predicted()){
				
				aRow.getCell(tmp_columnno).setCellStyle(style_selected);
				//aRow.getCell(tmp_columnno).setCellStyle(style_selected);
				aRow.getCell(tmp_columnno+1).setCellStyle(style_selected);
				aRow.getCell(tmp_columnno+2).setCellStyle(style_selected);
				aRow.getCell(tmp_columnno+3).setCellStyle(style_border);
			}else{
				aRow.getCell(tmp_columnno).setCellStyle(style_border);
				//aRow.getCell(tmp_columnno).setCellStyle(style_border);
				aRow.getCell(tmp_columnno+1).setCellStyle(style_border);
				aRow.getCell(tmp_columnno+2).setCellStyle(style_border);
				aRow.getCell(tmp_columnno+3).setCellStyle(style_border);
			}
			
		}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex);
		}
    }
}

