package br.com.joshua.productchallengeservice.report.adapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.report.port.GenerateReportPdfPort;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
@AllArgsConstructor
public class GenerateReportPdfAdapter implements GenerateReportPdfPort<String, byte[]> {

	private final DataSource dataSource;

	@Override
	public byte[] execute(String nameReport) {
		try {
			String fileReport = String.format("/report/src/%s.jasper" ,nameReport);
			JasperReport compile = (JasperReport) JRLoader
					.loadObject(this.getClass().getResourceAsStream(fileReport));
			try (Connection connection = dataSource.getConnection()) {
				Map<String, Object> parameters = new LinkedHashMap<>();
				JasperPrint jasperPrint = JasperFillManager.fillReport(compile, parameters, connection);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} catch (SQLException sqle) {
				throw new RuntimeException("Report SQL Error", sqle);
			}
		} catch (JRException jrpe) {
			throw new RuntimeException("Report Error", jrpe);
		}
	}
	
}
