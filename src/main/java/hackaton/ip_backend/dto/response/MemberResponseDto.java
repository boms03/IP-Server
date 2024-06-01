package hackaton.ip_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

public class MemberResponseDto {

    @Getter
    @Builder
    public static class SignIResponseDto
    {
        String id;
        String name;
        String email;
    }
}
