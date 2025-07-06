package com.result.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.result.entity.Student;
import com.result.repo.StudentRepo;

@Controller
public class AdminBasedStudentController {
	
	@Autowired
	private StudentRepo sr;
	
	@PostMapping("/addStu")
	public String addStudent(@RequestParam("stu_id") String rollno, @RequestParam("name") String name, @RequestParam("m_name") String m_name, @RequestParam("f_name") String f_name, @RequestParam("dob") String dob) {
		
		Student s = new Student();
		s.setRollno(rollno);
		s.setFname(f_name);
		s.setMname(m_name);
		s.setName(name);
		s.setDob(dob);
		sr.save(s);
		return "success";
	}
	
	@PostMapping("/editStu")
	public String editStudent(@RequestParam("stu_id") String rollno, @RequestParam("name") String name, @RequestParam("m_name") String m_name, @RequestParam("f_name") String f_name, @RequestParam("dob") String dob) {
		
		Student s = new Student();
		s.setRollno(rollno);
		s.setFname(f_name);
		s.setMname(m_name);
		s.setName(name);
		s.setDob(dob);
		sr.save(s);
		return "success";
	}
	
	@PostMapping("/deleteStu")
	public String deleteStudent(@RequestParam("stu_id") String rollno) {
		
		sr.deleteById(rollno);
		
		return "success";
	}
	
	@PostMapping("/importExcel")
	public String importExcel(@RequestParam("file") MultipartFile file) {
	    try {
	        Workbook workbook = new XSSFWorkbook(file.getInputStream());
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // skip header

	            Student s = new Student();
	            s.setRollno(getCellValueAsString(row.getCell(0))); // Roll No.
	            s.setName(getCellValueAsString(row.getCell(1)));   // Name
	            s.setDob(getCellValueAsString(row.getCell(2)));    // DOB
	            s.setMname(getCellValueAsString(row.getCell(3)));  // Mother's Name
	            s.setFname(getCellValueAsString(row.getCell(4)));  // Father's Name

	            sr.save(s);
	        }
	        workbook.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "success";
	}
	private String getCellValueAsString(Cell cell) {
	    if (cell == null) return "";
	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                // format date as yyyy-MM-dd
	                java.util.Date date = cell.getDateCellValue();
	                return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
	            } else {
	                return String.valueOf((long) cell.getNumericCellValue());
	            }
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case FORMULA:
	            return cell.getCellFormula();
	        default:
	            return "";
	    }
	}


	}




	
	


















