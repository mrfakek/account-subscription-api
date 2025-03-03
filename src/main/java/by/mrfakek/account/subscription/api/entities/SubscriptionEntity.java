package by.mrfakek.account.subscription.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subscriptions")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String subscriptionName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEntity> followers = new ArrayList<>();
    private Long followersCount;
    @PrePersist
    public void setDefaultFollowersCount() {
        if (followersCount == null) {
            followersCount = 0L;
        }
    }
}
