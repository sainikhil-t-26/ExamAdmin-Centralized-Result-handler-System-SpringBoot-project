package com.result.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.result.entity.Student;
import com.result.repo.StudentRepo;
import com.result.service.ExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @Autowired
    private StudentRepo sr;


    @GetMapping("/studentsJSON")
    public ResponseEntity<Page<Student>> printStu(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> pages = sr.findAll(pageable);

        return ResponseEntity.ok(pages);
    }

    @GetMapping("/showStudents")
    public String show(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "sortedField", defaultValue = "name") String sortedField,
        @RequestParam(name = "order", defaultValue = "desc") String order,
        @RequestParam(name = "keyword", defaultValue = "") String keyword,
        Model model) {

        Sort sort = order.equalsIgnoreCase("asc")
                ? Sort.by(sortedField).ascending()
                : Sort.by(sortedField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> pages = keyword.isEmpty()
                ? sr.findAll(pageable)
                : sr.findByNameContainingIgnoreCase(keyword, pageable);

        model.addAttribute("students", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalItems", pages.getTotalElements());
        model.addAttribute("sortedField", sortedField);
        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);

        return "show-students";
    }// Must match `show-students.html` under `templates`
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Student> listStudents = sr.findAll();

        ExcelExporter excelExporter = new ExcelExporter(listStudents);

        excelExporter.export(response);
    }

    @PostMapping("/importCSV")
    public String importCSV(
            @RequestParam("file") MultipartFile file, 
            @RequestParam("teacher_name") String teacherName,
            Model model) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 8) continue; // skip incomplete lines

                Student s = new Student();
                s.setRollno(fields[0].trim());
                s.setName(fields[1].trim());
                s.setDob(fields[2].trim());
                s.setMname(fields[3].trim());
                s.setFname(fields[4].trim());
                s.setJava_marks(Integer.parseInt(fields[5].trim()));
                s.setReact_marks(Integer.parseInt(fields[6].trim()));
                s.setOracle_marks(Integer.parseInt(fields[7].trim()));
                sr.save(s);
            }
            model.addAttribute("message", "✅ CSV imported successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "❌ Failed to import CSV: " + e.getMessage());
        }

        // send back to teacher welcome page with message
        model.addAttribute("teacher_name", teacherName);
        return "welcome-teacher";
    }
    @GetMapping("/welcome-admin")
    public String welcomeAdmin() {
        return "welcome-admin"; 
	}

}

