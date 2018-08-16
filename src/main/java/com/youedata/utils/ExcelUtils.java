package com.youedata.utils;

import com.youedata.base.Common;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-19 15:34
 **/
public class ExcelUtils {

    Logger log  =  Logger.getLogger(this.getClass());

    private static int hedear = 0;
    private String filePath;
    private String caseSheet;


    public ExcelUtils(String filePath, String caseSheet){
        this.filePath = filePath;
        this.caseSheet = caseSheet;

    }
    public static interface TestcaseField{
        int caseID = 0;
        int TestSuite = 1;
        int TestCaseType = 2;
        int TestCaseName = 3;
        int Expected = 5;
    }


    private  String getExcelPath(String FileName) {
        String userdirPath = System.getProperty("user.dir");
        if(userdirPath.endsWith("target")){
            userdirPath = userdirPath.replace("target","");
        }
        return userdirPath + "/resources/" + FileName;
    }


    public  List<Integer> getRowNum(String targetContent, int targetColumn) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        String getContentTemp = null;
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(Common.getFilePath(filePath)));
        HSSFSheet sheet = wb.getSheet(caseSheet);
        HSSFRow row = null;
        int rowTotal = sheet.getLastRowNum();
        for (int i = 0; i <= rowTotal; i++) {
            row = sheet.getRow(i);
            row.getCell(targetColumn).setCellType(CellType.STRING);
            getContentTemp = row.getCell(targetColumn).getStringCellValue();

            if (getContentTemp.equalsIgnoreCase(targetContent)) {
                if(isMergedRegion(sheet,i,targetColumn)){

                    //获得一个 sheet 中合并单元格的数量
                    int sheetmergerCount = sheet.getNumMergedRegions();
                    //遍历所有的合并单元格
                    for(int j = 0; j<sheetmergerCount;j++) {
                        //获得合并单元格保存进list中
                        CellRangeAddress ca = sheet.getMergedRegion(j);
                        //获得合并单元格的起始行, 结束行, 起始列, 结束列
                        int firstC = ca.getFirstColumn();
                        int lastC = ca.getLastColumn();
                        int firstR = ca.getFirstRow();
                        int lastR = ca.getLastRow();
                        if(i >=  firstR && i <= lastR)
                        {
                            if(targetColumn >= firstC && targetColumn <= lastC) {
                                list.add(firstR);
                                list.add(lastR);
                                wb.close();
                                return list;
                            }
                        }
                    }
                }else {
                    wb.close();
                    list.add(i);
                }
            }

        }
        wb.close();
        return list;
    }

    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++) {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);

            list.add(ca);
        }
        return list;
    }

    private  int getRowNum(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }

    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public  String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet) throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        if(cell == null) return "";
        return cell.getStringCellValue();
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public  String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }



    public  static  String isCombineCell(List<CellRangeAddress> listCombineCell,int row,int coul,Sheet sheet){

        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(row >= firstR && row <= lastR)
            {
                if(coul >= firstC && coul <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

  public <T> List<List<T> >readExcel(List<Integer> list, Class<T> beanType){

      List<List<T>> readDataList = new ArrayList<List<T>>();
      List<T> readData = null;
        if(list.size() > 0){
            ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream(filePath),caseSheet);

            for(int i = 0; i < list.size(); i++){

                readData = reader.read(hedear, list.get(i), list.get(i), beanType);
                readDataList.add(readData);
            }
        }
        return readDataList;
  }

    public void writeExcelCell(int line,int column,String content)throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(Common.getFilePath(filePath)));
        HSSFSheet sheet = wb.getSheet(caseSheet);
        if(line <= sheet.getLastRowNum() && line>0){
            HSSFRow row = sheet.getRow(line);
            if(column <= row.getLastCellNum() && column >0){
                HSSFCell cell = row.getCell(column);
                if(null == cell){
                    row.createCell(column);
                }
                cell.setCellValue(content);
//                HSSFCellStyle cellStyle = cell.getCellStyle();
//                HSSFCellStyle temp = wb.createCellStyle();
                FileOutputStream out = new FileOutputStream(Common.getFilePath(filePath));
                wb.write(out);
                wb.close();
                out.close();
            }else {
                log.error("列索引越界");
            }
        }else {
            log.error("行索引越界");
        }

    }

    public static void main(String[] args){
        System.out.println(ResourceUtil.getStream("testdata\\xfplat.xls"));
    }
}
