package hackaton.ip_backend.controller;

import java.util.List;

import hackaton.ip_backend.dto.response.SurveyResponseDto;
import hackaton.ip_backend.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import hackaton.ip_backend.common.response.BaseResponse;
import hackaton.ip_backend.dto.response.IpDto;
import hackaton.ip_backend.service.IfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ip")
public class IfController {

	private final IfService ifService;
	private final JWTUtil jwtUtil;

	//모든 Survey
	@Operation(summary = "모든 투표 리스트 조회 (페이징)")
	@ApiResponse(
			responseCode = "200",
			description = "OK",
			content =
			@Content(
					mediaType = "application/json",
					schema =
					@Schema(
							implementation =
									SurveyListResponse.class)))
	@GetMapping("/list")
	public BaseResponse<IpDto.PagingWrapper> getSurveyAll(
			@Schema(name = "pageNumber", description = "페이지 번호 (0번이 첫번째 페이지)", example = "0")
			@RequestParam(name = "pageNumber") int pageNumber
	) {
		return new BaseResponse<>(ifService.getSurveyAll(pageNumber));
	}

	// 카테고리 필터 Survey
	@Operation(summary = "특정 카테고리 투표 리스트 조회 (페이징)")
	@ApiResponse(
			responseCode = "200",
			description = "OK",
			content =
			@Content(
					mediaType = "application/json",
					schema =
					@Schema(
							implementation =
									SurveyListResponse.class)))
	@GetMapping("/category/list")
	public BaseResponse<IpDto.PagingWrapper> getSurveyCategory(
			@Schema(name = "category", description = "NONE(없음), ENTER(연예), ECONOMY(경제/주식), SPORT(스포츠), DAILY(일상)", example = "SPORT")
			@RequestParam(name = "category") String category,
			@Schema(name = "pageNumber", description = "페이지 번호 (0번이 첫번째 페이지)", example = "0")
			@RequestParam(name = "pageNumber") int pageNumber
	) {
		return new BaseResponse<>(ifService.getSurveyCategory(category, pageNumber));
	}

	// Survey 상세
	@Operation(summary = "특정 투표 상세조회")
	@ApiResponse(
			responseCode = "200",
			description = "OK",
			content =
			@Content(
					mediaType = "application/json",
					schema =
					@Schema(
							implementation =
									SurveyDetailResponse.class)))
	@GetMapping("/detail")
	public BaseResponse<IpDto.SurveyDetailDto> getSurveyDetail(
			@Schema(name = "surveyId", description = "투표 ID (투표 리스트 뷰 응답값에 포함되어있음)")
			@RequestParam("surveyId") Long surveyId
	) {
		return new BaseResponse<>(ifService.getSurveyDetail(surveyId));
	}


	@PostMapping()
	@Operation(summary = "투표하기")
	@ApiResponse(
			responseCode = "201",
			description = "CREATED",
			content =
			@Content(
					mediaType = "application/json",
					schema =
					@Schema(
							implementation =
									BaseResponse.class)))
	public BaseResponse<String> postVote(
			HttpServletRequest request,
			@Schema(name = "surveyId", description = "투표 아이디", example = "4")
			@RequestParam(name = "surveyId") Long surveyId,
			@RequestBody
			hackaton.ip_backend.dto.request.IpDto.PostIpDto postIpDto
	) {
		Long id = jwtUtil.getUserId(request);

		ifService.postVote(id,surveyId,postIpDto.getVote());
		return new BaseResponse<>("투표 완료");
	}


	//============================================ 스웨거 결과 문서 ==============================================

	private static class SurveyListResponse extends BaseResponse<IpDto.PagingWrapper> {}

	private static class SurveyDetailResponse extends BaseResponse<IpDto.SurveyDetailDto> {}

}
