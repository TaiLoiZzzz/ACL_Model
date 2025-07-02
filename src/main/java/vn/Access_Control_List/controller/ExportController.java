package vn.Access_Control_List.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.Access_Control_List.services.ExcelExportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/export")
public class ExportController {

    private final ExcelExportService excelExportService;

    @GetMapping("/users")
    public void exportUsers(HttpServletResponse response) throws Exception {
        excelExportService.exportLargeExcel(response);
    }
}
