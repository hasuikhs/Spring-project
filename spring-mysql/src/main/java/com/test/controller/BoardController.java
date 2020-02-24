package com.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.service.BoardService;
import com.test.service.NewsService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	@Autowired
	private BoardService service;

	@Autowired
	private NewsService newsservice;

	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", service.getList());
		newsservice.insert();
	}

	@GetMapping("/stat")
	public void stat(Model model, @RequestParam(value = "date", required = false) String dateType) {
		List<Map<String, Object>> mapList = newsservice.getStatistics(dateType);
		
		long sum = 0;
		List<Object> cntList = new ArrayList<Object>();
		List<Object> xList = new ArrayList<Object>();
		xList.add("'x'");
		cntList.add("'����'");
		for(Map<String, Object> map : mapList) {
			xList.add(map.get("stat"));
			cntList.add(map.get("cnt"));
			sum += Long.parseLong(map.get("cnt").toString());
		}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("sum", sum);
		map.put("avg", sum/mapList.size());
		
		model.addAttribute("stat", mapList);
		model.addAttribute("calstat", map);
		model.addAttribute("x", xList);
		model.addAttribute("chartdata", cntList);
	}

	@GetMapping("/excelnews")
	public void toExcelNews(HttpServletResponse response) {
		List<Map<String, Object>> newsList = newsservice.getList();

		try {
			// ��ũ�� ����
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("�Խ���");
			Row row = null;
			Cell cell = null;
			int rowNo = 0;

			// ���̺� ����� ��Ÿ��
			CellStyle headStyle = wb.createCellStyle();

			// ��� ��輱
			headStyle.setBorderTop(BorderStyle.THIN);
			headStyle.setBorderBottom(BorderStyle.THIN);
			headStyle.setBorderLeft(BorderStyle.THIN);
			headStyle.setBorderRight(BorderStyle.THIN);

			// ����
			headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// ����
			headStyle.setAlignment(HorizontalAlignment.CENTER);

			// �ٵ� ��輱
			CellStyle bodyStyle = wb.createCellStyle();
			bodyStyle.setBorderTop(BorderStyle.THIN);
			bodyStyle.setBorderBottom(BorderStyle.THIN);
			bodyStyle.setBorderLeft(BorderStyle.THIN);
			bodyStyle.setBorderRight(BorderStyle.THIN);

			// ��� ����
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(headStyle);
			cell.setCellValue("�۾� �ð�");
			cell = row.createCell(1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("����");
			cell = row.createCell(2);
			cell.setCellStyle(headStyle);
			cell.setCellValue("�̸�");

			// ������ ����
			for (Map<String, Object> map : newsList) {
				row = sheet.createRow(rowNo++);
				cell = row.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(map.get("date").toString());
				cell = row.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(map.get("title").toString());
				cell = row.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(map.get("name").toString());
			}

			// ������ Ÿ�԰� ���ϸ� ����
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment;filename=test.xls");

			// ���� ���
			wb.write(response.getOutputStream());
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/excelstat")
	public void toExcel(HttpServletResponse response, @RequestParam("date") String dateType) {
		List<Map<String, Object>> mapList = newsservice.getStatistics(dateType);
		
		long sum = 0;
		for(Map<String, Object> map : mapList) {
			sum += Long.parseLong(map.get("cnt").toString());
		}
		Map<String, Long> statMap = new HashMap<String, Long>();
		statMap.put("sum", sum);
		statMap.put("avg", sum/mapList.size());
		
		try {
			// ��ũ�� ����
			Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("�Խ���");
			Row row = null;
			Cell cell = null;
			int rowNo = 0;

			// ���̺� ����� ��Ÿ��
			CellStyle headStyle = wb.createCellStyle();

			// ��� ��輱
			headStyle.setBorderTop(BorderStyle.THIN);
			headStyle.setBorderBottom(BorderStyle.THIN);
			headStyle.setBorderLeft(BorderStyle.THIN);
			headStyle.setBorderRight(BorderStyle.THIN);

			// ����
			headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// ����
			headStyle.setAlignment(HorizontalAlignment.CENTER);

			// �ٵ� ��輱
			CellStyle bodyStyle = wb.createCellStyle();
			bodyStyle.setBorderTop(BorderStyle.THIN);
			bodyStyle.setBorderBottom(BorderStyle.THIN);
			bodyStyle.setBorderLeft(BorderStyle.THIN);
			bodyStyle.setBorderRight(BorderStyle.THIN);

			// ��� ����
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(headStyle);
			cell.setCellValue("�ð���");
			cell = row.createCell(1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("����");

			// ������ ����
			for (Map<String, Object> map : mapList) {
				row = sheet.createRow(rowNo++);
				cell = row.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(Double.parseDouble(map.get("stat").toString()));
				cell = row.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(Double.parseDouble(map.get("cnt").toString()));
			}
			
			Iterator<String> mapIt = statMap.keySet().iterator();
			
			while (mapIt.hasNext()) {
				String str = mapIt.next().toString();
				row = sheet.createRow(rowNo++);
				cell = row.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(str);
				cell = row.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellValue(statMap.get(str));
			}
			
			// ������ Ÿ�԰� ���ϸ� ����
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment;filename=test.xls");

			// ���� ���
			wb.write(response.getOutputStream());
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/list";
	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list";
	}
}
