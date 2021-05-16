package com.zlenadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

@Component("lotDataExcel")
public class LotDataExport extends AbstractXlsView {
	
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
		// get data model which is passed by the Spring container
        List list_report = new ArrayList();// ;model.get("listRate");
        HashMap<Integer,String> mapLot = (HashMap<Integer,String>) model.get("lot_map");
        
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Lot Data");
        sheet.setDefaultColumnWidth(10);
        int rowNo=0;
        HSSFRow aRow = sheet.createRow(rowNo);
        aRow.createCell(1).setCellValue("Lot Id");
		aRow.createCell(2).setCellValue("Weight");
		aRow.createCell(3).setCellValue("Rate");			            
		aRow.createCell(4).setCellValue("Kg Cost");
		aRow.createCell(5).setCellValue("KG Expenses");
		aRow.createCell(6).setCellValue("KG GST");
		aRow.createCell(7).setCellValue("KG Rate");
		aRow.createCell(8).setCellValue("KG Tax");
		aRow.createCell(9).setCellValue("KG Total");
		aRow.createCell(10).setCellValue("Labour");
		aRow.createCell(11).setCellValue("Preference");
		aRow.createCell(12).setCellValue("Qty");
		aRow.createCell(13).setCellValue("SB Expenses");
		aRow.createCell(14).setCellValue("SB GST");
		aRow.createCell(15).setCellValue("SB Rate");
		aRow.createCell(16).setCellValue("SB TAX");
		aRow.createCell(17).setCellValue("SB Total");
		aRow.createCell(18).setCellValue("Gross VAlue");
		aRow.createCell(19).setCellValue("Weight");
		rowNo++;
		for (Object bean : list_report	){
			aRow = sheet.createRow(rowNo);
//			if (mapLot.containsKey(bean.getLot_id())){
//				aRow.createCell(1).setCellValue(mapLot.get(bean.getLot_id()));	
//			}
//			
//			aRow.createCell(2).setCellValue(bean.getWeight());
//			aRow.createCell(3).setCellValue(bean.getGst_rate());			            
//			aRow.createCell(4).setCellValue(bean.getKg_cost());
//			aRow.createCell(5).setCellValue(bean.getKg_expenses());
//			aRow.createCell(6).setCellValue(bean.getKg_gst());
//			aRow.createCell(7).setCellValue(bean.getKg_rate());
//			aRow.createCell(8).setCellValue(bean.getKg_tax());
//			aRow.createCell(9).setCellValue(bean.getKg_total());
//			aRow.createCell(10).setCellValue(bean.getLabour());
//			aRow.createCell(11).setCellValue(bean.getPreference());
//			aRow.createCell(12).setCellValue(bean.getQty());
//			aRow.createCell(13).setCellValue(bean.getSb_expenses());
//			aRow.createCell(14).setCellValue(bean.getSb_gst());
//			aRow.createCell(15).setCellValue(bean.getSb_rate());
//			aRow.createCell(16).setCellValue(bean.getSb_tax());
//			aRow.createCell(17).setCellValue(bean.getSb_total());
//			aRow.createCell(18).setCellValue(bean.getTotal_value());
//			aRow.createCell(19).setCellValue(bean.getWeight());
            rowNo++;
		}
    }
	
}
