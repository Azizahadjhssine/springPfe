package com.app.Dto;

import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombinedRequestDto {
	private DataPrice spvieRequest;
	private  RequestNeolianeDto dn;

}
