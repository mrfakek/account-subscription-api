package by.mrfakek.account.subscription.api.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

    private Long id;
    private String subscriptionName;

}
