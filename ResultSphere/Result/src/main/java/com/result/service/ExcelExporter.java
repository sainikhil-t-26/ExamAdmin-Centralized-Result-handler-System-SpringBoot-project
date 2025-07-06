package com.result.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.result.entity.Student;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<Student> students;

    public ExcelExporter(List<Student> students) {
        this.students = students;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Students");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        String[] headers = {"Roll No", "Name", "DOB", "Mother Name", "Father Name", "Java Marks", "React Marks", "Oracle Marks"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    private void writeData() {
        int rowCount = 1;
        for (Student s : students) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(s.getRollno());
            row.createCell(1).setCellValue(s.getName());
            row.createCell(2).setCellValue(s.getDob());
            row.createCell(3).setCellValue(s.getMname());
            row.createCell(4).setCellValue(s.getFname());
            row.createCell(5).setCellValue(s.getJava_marks());
            row.createCell(6).setCellValue(s.getReact_marks());
            row.createCell(7).setCellValue(s.getOracle_marks());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeader();
        writeData();
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        workbook.close();
        out.close();
    }
}

