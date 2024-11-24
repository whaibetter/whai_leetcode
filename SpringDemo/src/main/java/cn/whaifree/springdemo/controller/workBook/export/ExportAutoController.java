package cn.whaifree.springdemo.controller.workBook.export;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.WorkbookUtil;
import cn.whaifree.springdemo.controller.workBook.WorkBookController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 16:35
 * @注释
 */
@RestController
@RequestMapping("/workBookAuto")
public class ExportAutoController {

    @GetMapping(value = "/exportUser1")
    public void export1(HttpServletResponse response) {
        try (OutputStream out = response.getOutputStream()) {
            List<User> userList = getUserList();
            Workbook query = WorkBookResponseUtils.getWorkBook(userList, User.class, response, "导出成员工时");
            query.write(out);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping(value = "/exportUser")
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        // WorkBook
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder
                .encode("导出成员工时.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try (
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/project_member_workHours.xlsx");
                OutputStream out = response.getOutputStream()
        ) {
            Workbook query = query(inputStream);
            query.write(out);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Workbook query(InputStream inputStream) {
        Workbook workbook = WorkbookUtil.createSXSSFBook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
        List<User> userList = getUserList();
        for (int i = 0; i < userList.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(userList.get(i).getNickName());

            cell = row.createCell(1);
            cell.setCellValue(userList.get(i).getFullName());

            cell = row.createCell(2);
            cell.setCellValue(userList.get(i).getDepartment());
        }
        return workbook;
    }

    public Workbook query2(InputStream inputStream) {
        Workbook workbook = WorkbookUtil.createSXSSFBook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
        List<User2> userList = getUser2List();
        for (int i = 0; i < userList.size(); i++) {
            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(userList.get(i).getNickName());

            cell = row.createCell(1);
            cell.setCellValue(userList.get(i).getFullName());

            cell = row.createCell(2);
            cell.setCellValue(userList.get(i).getDepartment());
        }
        return workbook;
    }

    @Data
    @Setter
    static class User {
        private String nickName;
        private String fullName;
        private String department;

    }

    @Data
    @Setter
    static class User2 {
        private String nickName;
        private String fullName;
        private String department;
    }


    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setNickName("张三" + i);
            user.setDepartment("研发部");
            user.setFullName("张三" + i);
            userList.add(user);
        }
        return userList;
    }

    public static List<User2> getUser2List() {
        List<User2> userList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User2 user = new User2();
            user.setNickName("张三" + i);
            user.setDepartment("研发部");
            user.setFullName("张三" + i);
            userList.add(user);
        }
        return userList;
    }

}


