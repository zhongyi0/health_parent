import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class PoitTest {
    //@Test
    public void test01() {
        XSSFWorkbook workbook;

        {
            try {
                //连接工作表
                workbook = new XSSFWorkbook("C:\\Users\\HH\\Desktop\\基础班01\\还行吧.xlsx");
                //确定那个sheet
                XSSFSheet sheet = workbook.getSheetAt(0);
                //遍历所有的行
                for (Row row : sheet) {
                    //遍历所有列
                    for (Cell cell : row) {
                        String value = cell.getStringCellValue();
                        System.out.println(value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //@Test
    public void test2() throws Exception{
        //加载指定文件，创建一个Excel对象（工作簿）
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\HH\\Desktop\\基础班01\\还行吧.xlsx")));
        //读取Excel文件中第一个Sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获得当前工作表中最后一个行号，需要注意：行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("lastRowNum = " + lastRowNum);
        for(int i=0;i<=lastRowNum;i++){
            XSSFRow row = sheet.getRow(i);//根据行号获取每一行
            //获得当前行最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            System.out.println("lastCellNum = " + lastCellNum);
            for(int j=0;j<lastCellNum;j++){
                XSSFCell cell = row.getCell(j);//根据单元格索引获得单元格对象
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }
   // @Test
    public void test03() throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("传智");

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("新号码");
        row1.createCell(2).setCellValue("还行");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("欧国");
        row2.createCell(1).setCellValue("欧欢");
        row2.createCell(2).setCellValue("偶像");

        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\HH\\Desktop\\基础班01\\一般般.xlsx"));

        workbook.write(out);

        out.flush();
        out.close();
        workbook.close();
    }


}
