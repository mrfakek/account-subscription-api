package by.mrfakek.account.subscription.api.controllers;

import by.mrfakek.account.subscription.api.dtos.SubscriptionResponseDto;
import by.mrfakek.account.subscription.api.repositories.SubscriptionRepository;
import by.mrfakek.account.subscription.api.services.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<SubscriptionResponseDto>> getTopSubscriptions() {
       List<SubscriptionResponseDto> topSubscriptionsList = subscriptionService.getTopSubscriptions();
       return new ResponseEntity<>(topSubscriptionsList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscriptions(@PathVariable Long id) {
       List<SubscriptionResponseDto> subscriptionResponseDtoList = subscriptionService.getUserSubscriptions(id);
        return new ResponseEntity<>(subscriptionResponseDtoList, HttpStatus.OK);
    }
    @PostMapping("/users/{id}/subscriptions/{sub_id}")
    public ResponseEntity<Void> addUserSubscription(@PathVariable Long id, @PathVariable Long sub_id) {
    subscriptionService.userSubscriptionAdd(id,sub_id);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id, @PathVariable Long sub_id) {
        subscriptionService.userSubscriptionRemove(id,sub_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

