package by.mrfakek.account.subscription.api.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponseDto {

    private Long id;
    private String subscriptionName;
    private Long followerCount;


}
