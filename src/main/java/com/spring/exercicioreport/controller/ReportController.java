package com.spring.exercicioreport.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import java.sql.Connection;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;


@Controller
public class ReportController {

	@GetMapping("/relatorio")
	public ResponseEntity getRelatorio() {
		byte[] bytes = null;
		Map<String, Object> objetoRelatorio = null;
		InputStreamResource stream = null;
		HttpStatus status = null;
		HttpHeaders header = new HttpHeaders();
		Connection connection;
		try {
			connection = Connector.connect();
			File file = ResourceUtils.getFile("C:/Users/moise/JaspersoftWorkspace/MyReports/ExercicioRelatiorios2.jrxml");
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(file.getAbsolutePath());
			JasperRunManager.runReportToPdf(report, objetoRelatorio, connection);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		finally {
			InputStream inputStream = new ByteArrayInputStream(bytes);
			stream = new InputStreamResource(inputStream);
			header.setContentLength(bytes.length);
			header.setContentType(MediaType.APPLICATION_PDF);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity(stream, header, status);
	}
}
