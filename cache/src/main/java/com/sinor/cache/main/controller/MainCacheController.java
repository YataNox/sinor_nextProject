package com.sinor.cache.main.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinor.cache.common.BaseException;
import com.sinor.cache.common.BaseResponse;
import com.sinor.cache.common.BaseResponseStatus;
import com.sinor.cache.main.model.UserCacheResponse;
import com.sinor.cache.main.service.MainCacheService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MainCacheController implements IMainCacheControllerV1<UserCacheResponse, UserCacheResponse> {
	private final MainCacheService mainCacheService;

	/**
	 * 데이터 조회 및 캐시 조회
	 *
	 * @param path        요청에 전달된 path
	 * @param queryParams 요청에 전달된 queryString
	 * @apiNote <a href="https://www.baeldung.com/spring-request-response-body#@requestbody">reference</a>
	 */
	@Override
	@GetMapping("/{path}")
	@ResponseBody
	public BaseResponse<?> getDataReadCache(@PathVariable String path,
		@RequestParam(required = false) Map<String, String> queryParams) {
		try {
			String pathCache = mainCacheService.getDataInCache(path);
			if (pathCache == null) {
				return new BaseResponse<>(BaseResponseStatus.SUCCESS, mainCacheService.postInCache(path, queryParams.get(0)));
			}

			return new BaseResponse<>(BaseResponseStatus.SUCCESS, pathCache);
		} catch (BaseException e) {
			System.out.println(e.getMessage());
			return new BaseResponse<>(e.getStatus());
		}
	}

	/**
	 * 데이터 조회 또는 생성 및 캐시 조회
	 *
	 * @apiNote <a href="https://www.baeldung.com/spring-request-response-body#@requestbody">reference</a>
	 * @param path 요청에 전달된 path
	 * @param queryString 요청에 전달된 queryString
	 * @param body 요청에 전달된 RequestBody 내용에 매핑된 RequestBodyDto 객체
	 */
	@Override
	public UserCacheResponse postDataReadCache(String path, String queryString, UserCacheResponse body) {

		return null;
	}

	/**
	 * 데이터 삭제 및 캐시 갱신
	 *
	 * @apiNote <a href="https://www.baeldung.com/spring-request-response-body#@requestbody">reference</a>
	 * @param path 요청에 전달된 path
	 * @param queryString 요청에 전달된 queryString
	 * @param body 요청에 전달된 RequestBody 내용에 매핑된 RequestBodyDto 객체
	 */
	@Override
	public UserCacheResponse deleteDataRefreshCache(String path, String queryString, UserCacheResponse body) {
		return null;
	}

	/**
	 * 데이터 수정 및 캐시 갱신
	 * @apiNote <a href="https://www.baeldung.com/spring-request-response-body#@requestbody">reference</a>
	 * @param path 요청에 전달된 path
	 * @param queryString 요청에 전달된 queryString
	 * @param body 요청에 전달된 RequestBody 내용에 매핑된 RequestBodyDto 객체
	 */
	@Override
	public UserCacheResponse updateDataRefreshCache(String path, String queryString, UserCacheResponse body) {
		return null;
	}
}