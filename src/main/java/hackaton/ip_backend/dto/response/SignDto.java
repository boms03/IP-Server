package hackaton.ip_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

public class SignDto {

    @Getter
    @Builder
    public static class SignIResponseDto
    {
        String id;
        String name;
        String email;
    }
}
