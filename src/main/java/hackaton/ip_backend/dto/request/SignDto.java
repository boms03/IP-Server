package hackaton.ip_backend.dto.request;

import lombok.Builder;
import lombok.Getter;

public class SignDto {
    @Getter
    @Builder
    public static class SignUpDto {
        String name;

        String email;

        String password;
    }

    @Getter
    @Builder
    public static class SignInDto {
        String email;

        String password;
    }
}
