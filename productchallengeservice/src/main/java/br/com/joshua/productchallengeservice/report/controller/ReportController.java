package br.com.joshua.productchallengeservice.report.controller;

import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.productchallengeservice.report.facade.ReportFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

	public static final String X_SUGGESTED_FILENAME_HEADER = "X-SUGGESTED-FILENAME";

	private final ReportFacade reportFacade;

	public ReportController(ReportFacade reportFacade) {
		this.reportFacade = reportFacade;
	}

	@Operation(summary = "Generate Report PDF")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Resport PDF", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Byte.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/pdf/{nameReport}")
	public ResponseEntity<byte[]> generatePersonReportPdf(@PathVariable String nameReport) {

		byte[] relatorio = reportFacade.generateReportPdf(nameReport);
		return ok().contentType(APPLICATION_PDF) //
				.header(X_SUGGESTED_FILENAME_HEADER, "person.pdf").body(relatorio);
	}

}
