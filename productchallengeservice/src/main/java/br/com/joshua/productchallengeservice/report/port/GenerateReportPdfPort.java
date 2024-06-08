package br.com.joshua.productchallengeservice.report.port;

public interface GenerateReportPdfPort <IN, OUT> {
	
	public OUT execute(IN in);

}
