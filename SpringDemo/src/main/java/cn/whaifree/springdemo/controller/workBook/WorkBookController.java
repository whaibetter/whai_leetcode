package cn.whaifree.springdemo.controller.workBook;

import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.WorkbookUtil;
import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 16:35
 * @注释
 */
@Controller
@RequestMapping("/workBook")
public class WorkBookController {
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

    public List<User> getUserList() {
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

    @Data
    @Setter
    static class User {
        private String nickName;
        private String fullName;
        private String department;
    }

}


