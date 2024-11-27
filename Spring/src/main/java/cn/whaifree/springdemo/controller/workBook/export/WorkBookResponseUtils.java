package cn.whaifree.springdemo.controller.workBook.export;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 16:56
 * @注释
 */
public class WorkBookResponseUtils {

    public static void setHead(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        // WorkBook
        fileName = Optional.ofNullable(fileName).orElse("workBook.xlsx");

        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder
                .encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
    }

    public static Workbook getWorkBook(List<?> list, Class<?> clazz, HttpServletResponse response, String fileName) {
        try {
            setHead(response, fileName);
            return getWorkBook(list, clazz);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Workbook getWorkBook(List<?> list, Class<?> clazz) {
        try (InputStream inputStream = ExportAutoController.class.getClassLoader().getResourceAsStream("template/empty.xlsx")) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheetAt = workbook.getSheetAt(0);
            Field[] fields = ReflectUtil.getFields(clazz);
            Row row = sheetAt.getRow(0);
            if (row == null) {
                row = sheetAt.createRow(0);
            }
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(fields[i].getName());
            }
            int length = fields.length; // 列数
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                row = sheetAt.createRow(i + 1);
                for (int j = 0; j < length; j++) {
                    Cell cell = row.createCell(j);
                    Field field = fields[j];
                    field.setAccessible(true);
                    try {
                        Object value = field.get(o); // 获取字段值
                        if (value != null) {
                            cell.setCellValue(value.toString()); // 将字段值设置到单元格中
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
