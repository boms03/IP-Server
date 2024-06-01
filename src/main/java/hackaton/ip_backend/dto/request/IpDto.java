package hackaton.ip_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class IpDto {
    @Getter
    @Builder
    public static class PostIpDto {

        @Schema(name = "vote", description = "선택한 항목 (1 or 2)", example = "1")
        int vote;

    }
}
