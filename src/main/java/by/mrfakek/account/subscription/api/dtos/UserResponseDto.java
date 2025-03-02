package by.mrfakek.account.subscription.api.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
}
