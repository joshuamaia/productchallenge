package br.com.joshua.productchallengeservice.report.facade;

import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.report.port.GenerateReportPdfPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReportFacadeImpl implements ReportFacade {
	
	private final GenerateReportPdfPort<String, byte[]> generateReportPdfPort;
	
	@Override
	public byte[] generateReportPdf(String nameReport) {
		return generateReportPdfPort.execute(nameReport);
	}


}
