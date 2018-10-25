package com.bmw.boss.infos.exportExcle.bmw;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by qxr4383 on 2018/3/12.
 */
public class ExportExcelDemo {
    private HSSFWorkbook workbook = null;
    /**
     * 显示的导出表的标题
     */
    private String title;
    /**
     * 导出表的列名
     */
    private String[] rowName;

    private List<Object[]> dataList = new ArrayList<>();

    /**
     * 构造方法，传入要导出的数据
     *
     * @param title
     * @param rowName
     * @param dataList
     */
    public ExportExcelDemo(String title, String[] rowName, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }


    /**
     * 判断文件的sheet是否存在
     * @param filePath      文件路径
     * @param sheetName     表格索引名
     * @return
     */
    public boolean sheetExist(String filePath,String sheetName){
        boolean flag = false;
        File file = new File(filePath);
        if(file.exists()) {    //文件存在
            //创建workbook
            try {
                workbook = new HSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                HSSFSheet sheet = workbook.getSheet(sheetName);
                if(sheet!=null)
                     flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{  //文件不存在
            flag =false;
        }
        return flag;
    }

    /**
     *
     *(2003 xls后缀 导出)
     * @param
     * @return void 返回类型
     * @author xsw
     * @2016-12-7上午10:44:00
     */
    public static void read2003Excel(InputStream in,String exportFilePath,String time,String folderPath) throws IOException{
        try {
            HSSFWorkbook wb = new HSSFWorkbook(in);
            //读取了模板内所有sheet内容
            HSSFSheet sheet = wb.getSheetAt(0);

            //如果这行没有了，整个公式都不会有自动计算的效果的
            sheet.setForceFormulaRecalculation(true);
            List<Integer[]> list = new ArrayList<>();
            List<File> fileList = GetFileList.getFileList(folderPath);
            for (File file : fileList) {
                System.out.println("---" + file.getAbsolutePath());
                list.add(ImportByNIO.readTxtByNIO(file.getAbsolutePath()));
            }
            for (int i = 1; i <= list.size(); i++) {
                //HSSFCell在相应的单元格进行赋值
                HSSFCell cell = sheet.getRow(i + 2).getCell(0);//第i+2行 第0列
                cell.setCellValue(time + "/" + i);
                for (int k = 1; k <= 15; k++) {
                    sheet.getRow(i + 2).getCell(k).setCellValue(list.get(i - 1)[k-1]);//第i+2行 第k列
                }
            }
            //修改模板内容导出新模板
            FileOutputStream out = new FileOutputStream(exportFilePath);
            wb.write(out);
            out.close();
        }catch (Exception e) {
            System.out.println("文件读取错误!");
            e.printStackTrace();
        }

    }

    /**
     *
     *(2007 xlsx后缀 导出)
     * @param
     * @return void 返回类型
     * @author xsw
     * @2016-12-7上午10:44:30
     */
    public static void read2007Excel(InputStream in,String exportExcelPath,
                String time,String folderPath) throws IOException{
        //读取excel模板
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //读取了模板内所有sheet内容
        XSSFSheet sheet = wb.getSheetAt(0);

        //如果这行没有了，整个公式都不会有自动计算的效果的
        sheet.setForceFormulaRecalculation(true);

        List<Integer[]> list = new ArrayList<>();
        List<File> fileList = GetFileList.getFileList(folderPath);
        for (File file : fileList) {
            System.out.println("---" + file.getAbsolutePath());
            list.add(ImportByNIO.readTxtByNIO(file.getAbsolutePath()));
        }
        for (int i = 1; i <= list.size(); i++) {
            //XSSFCell在相应的单元格进行赋值
            XSSFCell cell = sheet.getRow(i + 2).getCell(0);//第i+2行 第0列
            cell.setCellValue(time + "/" + i);
            for (int k = 1; k <= 15; k++) {
                sheet.getRow(i + 2).getCell(k).setCellValue(list.get(i - 1)[k-1]);//第i+2行 第k列
            }
        }

        //在相应的单元格进行赋值
//        XSSFCell cell = sheet.getRow(11).getCell(6);//第12行 第7列
//        cell.setCellValue(1);
        //修改模板内容导出新模板
        FileOutputStream out = new FileOutputStream(exportExcelPath);
        wb.write(out);
        out.close();
    }


    /**
     * @param @param  file
     * @param @return
     * @param @throws IOException
     * @return List<String> (excel每行拼接成List中的String)
     * @throws
     * @Title: readExcel
     * @Description: TODO(对外提供读取excel 的方法)
     */
    public static synchronized void readExcel(String importFilePath,String exportFilePath,
                                              String time,String folderPath) throws IOException {
        File file=new File(importFilePath);
        String fileName = file.getName();
        //List<String> list = new ArrayList<String>();
        //根据其名称获取后缀
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            read2003Excel(new FileInputStream(file),exportFilePath,time,folderPath);
        } else if ("xlsx".equals(extension) || "xlsm".equals(extension)) {
            read2007Excel(new FileInputStream(file),exportFilePath,time,folderPath);
        } else if ("tmp".equals(extension)) {
            read2007Excel(new FileInputStream(file),exportFilePath,time,folderPath);
        } else {
            throw new IOException("不支持的文件类型");
        }
    }

    public static void main(String[] args) throws IOException{
        //excle 2007
        String importFilePath= "/Users/qxr4383/Documents/work/logger/dataDemo.xlsx";
        String exportFilePath= "/Users/qxr4383/Documents/work/logger/2018-09.xlsx";
        String folderPath = "/Users/qxr4383/Documents/work/logger/prod/access/2018/09";
        readExcel(importFilePath,exportFilePath,"2018/09",folderPath);
    }
}
