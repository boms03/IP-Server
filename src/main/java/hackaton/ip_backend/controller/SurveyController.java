package hackaton.ip_backend.controller;

import hackaton.ip_backend.common.response.BaseResponse;
import hackaton.ip_backend.dto.request.SurveyRequestDto;
import hackaton.ip_backend.dto.response.IpDto;
import hackaton.ip_backend.dto.response.SurveyResponseDto;
import hackaton.ip_backend.service.SurveyService;
import hackaton.ip_backend.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final JWTUtil jwtUtil;


    @PostMapping()
    @Operation(summary = "투표 생성", description = "String 반환")
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    public BaseResponse<String> createSurvey(
            HttpServletRequest request,
            @RequestBody
            SurveyRequestDto.PostSurveyDto postSurveyDto
    ){

        Long id = jwtUtil.getUserId(request);

        surveyService.createSurvey(id,postSurveyDto);

        return new BaseResponse<>("투표 생성 완료");

    }

    @Operation(summary = "투표 생성자 정보 가져오기")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
            @Content(
                    mediaType = "application/json",
                    schema =
                    @Schema(
                            implementation =
                                    GetSurveyDto.class)))
    @GetMapping()
    public BaseResponse<SurveyResponseDto.GetSurveyDto> getSurvey(
            HttpServletRequest request
    ){

        Long id = jwtUtil.getUserId(request);

        SurveyResponseDto.GetSurveyDto getSurveyDto = surveyService.getSurvey(id);

        return new BaseResponse<>(getSurveyDto);

    }

    private static class GetSurveyDto extends BaseResponse<SurveyResponseDto.GetSurveyDto> {}

}
