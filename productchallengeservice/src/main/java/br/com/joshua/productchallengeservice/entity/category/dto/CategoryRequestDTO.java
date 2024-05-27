package br.com.joshua.productchallengeservice.entity.category.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryRequestDTO {
	
	private Long id;
	private String name;
}
