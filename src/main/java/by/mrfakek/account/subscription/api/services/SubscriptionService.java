package by.mrfakek.account.subscription.api.services;

import by.mrfakek.account.subscription.api.dtos.SubscriptionResponseDto;
import by.mrfakek.account.subscription.api.entities.SubscriptionEntity;
import by.mrfakek.account.subscription.api.entities.UserEntity;
import by.mrfakek.account.subscription.api.exceptions.BadRequestException;
import by.mrfakek.account.subscription.api.exceptions.NotFoundException;
import by.mrfakek.account.subscription.api.mappers.SubscriptionMapper;
import by.mrfakek.account.subscription.api.repositories.SubscriptionRepository;
import by.mrfakek.account.subscription.api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.userRepository = userRepository;
    }

public List<SubscriptionResponseDto> getUserSubscriptions(Long userId) {
        log.info("getUserSubscriptions from user {}", userId);
    UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
    if (user.getSubscriptions().isEmpty()) {
        log.info("User {} has no subscriptions", userId);
        return new ArrayList<>(); }
    log.info("User {} has {} subscriptions", userId, user.getSubscriptions().size());
    List <SubscriptionResponseDto> subscriptionResponseDtos = subscriptionMapper.toSubscriptionResponseDtoList(user.getSubscriptions());
    return subscriptionResponseDtos;
    }

public List<SubscriptionResponseDto> getTopSubscriptions() {
        log.info("getTopSubscriptions");
        List <SubscriptionEntity> subscriptionEntities = subscriptionRepository
                .findTopSubscriptions(PageRequest.of(0, 3));
        if (subscriptionEntities.isEmpty()) {
            throw new NotFoundException("No subscriptions found"); }
        log.info("top subscriptions received");
        return subscriptionMapper.toSubscriptionResponseDtoList(subscriptionEntities);
    }

    @Transactional
    public void userSubscriptionAdd(Long userId, Long subId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(subId).orElseThrow(() -> new NotFoundException("Subscription", subId));
        if (user.getSubscriptions().contains(subscriptionEntity)) {throw new BadRequestException("Subscription already exists");}
        user.getSubscriptions().add(subscriptionEntity);
        subscriptionEntity.getFollowers().add(user);
        // userRepository.save(user);
        subscriptionRepository.incrementFollowerCount(subId);
        log.info("User {} added subscription {}", userId, subId);
    }

    @Transactional
    public void userSubscriptionRemove(Long userId, Long subId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(subId).orElseThrow(() -> new NotFoundException("Subscription", subId));
        if (user.getSubscriptions().contains(subscriptionEntity)) {
            subscriptionEntity.getFollowers().remove(user);
            user.getSubscriptions().remove(subscriptionEntity);
            log.info("User {} removed subscription {}", userId, subId);
         //   userRepository.save(user);
        }
        else throw new BadRequestException("Subscription does not exist");
    }

}
