package by.mrfakek.account.subscription.api.repositories;

import by.mrfakek.account.subscription.api.entities.SubscriptionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {


    @Modifying
    @Query("UPDATE SubscriptionEntity subs SET subs.followersCount = subs.followersCount + 1 WHERE subs.id = :subscriptionId")
    void incrementFollowerCount(@Param("subscriptionId") Long subscriptionId);

    @Query("SELECT subs FROM SubscriptionEntity subs ORDER BY subs.followersCount DESC")
    List<SubscriptionEntity> findTopSubscriptions(Pageable pageable);


}
