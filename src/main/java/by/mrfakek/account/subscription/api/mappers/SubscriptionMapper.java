package by.mrfakek.account.subscription.api.mappers;
import by.mrfakek.account.subscription.api.dtos.SubscriptionResponseDto;
import by.mrfakek.account.subscription.api.entities.SubscriptionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponseDto toSubscriptionResponseDto(SubscriptionEntity entity);
    List<SubscriptionResponseDto> toSubscriptionResponseDtoList(List<SubscriptionEntity> subscriptionEntitys);

}
