package com.bmw.boss.common.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by qxr4383 on 2018/3/12.
 */
public class ExportExcel {
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
    public ExportExcel(String title, String[] rowName, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }

    /**
     * 无样式优化导出
     *
     * @param //response
     * @throws IOException
     */
    public void exportNoStyle(OutputStream out) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(title);
        HSSFRow row = hssfSheet.createRow((short) 0);
        HSSFCell cell;
        for (int i = 0; i < rowName.length; i++) {
            cell = row.createCell(i);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(rowName[i]);
        }
        int iRow = 1;
        for (int i = 0, len = dataList.size(); i < len; i++) {
            row = hssfSheet.createRow((short) iRow);
            Object[] objs = dataList.get(i);
            for (int j = 0, len2 = objs.length; j < len2; j++) {
                cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(objs[j].toString());
            }
            iRow++;
        }
        write(out, hssfWorkbook);
    }

    /**
     * 导出数据（已优化样式）
     *
     * @throws Exception
     */
    public void export(OutputStream out) throws Exception {
        try {
            /**
             * 创建工作簿对象
             */
            HSSFWorkbook workbook = new HSSFWorkbook();
            /**
             * 创建工作表
             */
            HSSFSheet sheet = workbook.createSheet(title);

            // 产生表格标题行
            HSSFCell cellTitle = sheet.createRow(0).createCell(0);

            /**
             * sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
             * 获取列头样式对象
             */
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);
            //单元格样式对象
            HSSFCellStyle style = this.getStyle(workbook);

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTitle.setCellStyle(columnTopStyle);
            cellTitle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            // 在索引2的位置创建行(最顶端的行开始的第二行)
            HSSFRow rowRowName = sheet.createRow(2);

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                //创建列头对应个数的单元格
                HSSFCell cellRowName = rowRowName.createCell(n);
                //设置列头单元格的数据类型
                cellRowName.setCellType(Cell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                //设置列头单元格的值
                cellRowName.setCellValue(text);
                //设置列头单元格样式
                cellRowName.setCellStyle(columnTopStyle);
            }

            //将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                //遍历每个对象
                Object[] obj = dataList.get(i);
                //创建所需的行数
                HSSFRow row = sheet.createRow(i + 3);

                for (int j = 0; j < obj.length; j++) {
                    //设置单元格的数据类型
                    HSSFCell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        //设置单元格的值
                        cell.setCellValue(obj[j].toString());
                    } else {
                        cell.setCellValue("");
                    }
                    //设置单元格样式
                    cell.setCellStyle(style);
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (null != currentCell.getRichStringCellValue()) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            write(out, workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void write(OutputStream out, HSSFWorkbook workbook) throws IOException {
        //OutputStream out = null;
        try {
            String fileName = title + "-" + String.valueOf(System.currentTimeMillis()).substring(4, 13);
            System.out.println("-----------------------导出excel fileName：{}--------------------"+ fileName);
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename="
//                    + new String(fileName.getBytes(), "iso-8859-1") + ".xls");
//            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }



    /**
     * 列头单元格样式
     *
     * @param workbook
     * @return
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;

    }


    /**
     * 列数据信息单元格样式
     *
     * @param workbook
     * @return
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;

    }
}
