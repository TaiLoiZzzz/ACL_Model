package vn.Access_Control_List.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

       private final DataSource dataSource;

    public void exportLargeExcel(HttpServletResponse response) throws Exception {
        // Tạo workbook dạng streaming
        SXSSFWorkbook workbook = new SXSSFWorkbook(100); // giữ 100 dòng trong RAM
        Sheet sheet = workbook.createSheet("Users");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Username");
        header.createCell(2).setCellValue("Email");

        // Kết nối DB trực tiếp
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, username, email FROM users",
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY
        );
        ps.setFetchSize(1000); // cực quan trọng để stream

        ResultSet rs = ps.executeQuery();
        int rowNum = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rs.getLong("id"));
            row.createCell(1).setCellValue(rs.getString("username"));
            row.createCell(2).setCellValue(rs.getString("email"));
        }

        rs.close();
        ps.close();
        conn.close();

        // Trả về file Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");

        workbook.write(response.getOutputStream());
        workbook.dispose(); // dọn bộ nhớ tạm
    }
}
