/**
 * 通用的excel导出
 */
package com.zhx.modules.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CommonExcelExport {


    public static void excelExport(HttpServletResponse response,Map<String, String> cellName,
    		List<Map<String, Object>> cellValues,String workBookName) {
    	//创建excel
    	HSSFWorkbook wb = new HSSFWorkbook();
    	//创建sheet
        HSSFSheet sheet = wb.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow((int) 0);
        //单元格
        HSSFCell cell;
        int i = 0;
        //设置表头
        for (Map.Entry<String, String> entry : cellName.entrySet()) {
            cell = row.createCell(i);
            cell.setCellValue(entry.getValue());
            i++;
        }
        //单元格值填充
        for (int j = 0; j < cellValues.size(); j++) {                                            
            row = sheet.createRow(j + 1);//创建新行
            Map<String, Object> ret = cellValues.get(j);
            int icell = 0;
            //循环赋值
            for (Map.Entry<String, String> entry : cellName.entrySet()) {
                Object value = ret.get(entry.getKey());
                String cellValue = value != null?value.toString():"";
                row.createCell(icell).setCellValue(cellValue);
                icell++;
            }
        }
        OutputStream out = null;
        //response输出流导出excel
        try {
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	wb.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

