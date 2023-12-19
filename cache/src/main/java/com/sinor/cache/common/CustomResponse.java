package com.sinor.cache.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Main에서 받아온 ResponseEntity를 Status, Headers, Body로 나누기 위한 객체
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private JsonNode body;
	private Map<String, String> headers;
	private int statusCodeValue;
	public static CustomResponse from(ResponseEntity<JsonNode> entity){
		Map<String, String> map = new HashMap<>();

		// 헤더의 대문자 제거 후 적용
		for(String key : entity.getHeaders().keySet()){
			String s = Objects.requireNonNull(entity.getHeaders().get(key)).toString()
				.replace("]", "")
				.replace("[", "");
			System.out.println(s);
			map.put(key, s);
		}
		return CustomResponse.builder()
			.body(Objects.requireNonNull(entity.getBody()))
			.headers(map)
			.statusCodeValue(entity.getStatusCode().value())
			.build();
	}
}
