package by.mrfakek.account.subscription.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false, length = 10)
    private String level;

    @Column(nullable = false, length = 255)
    private String logger;

    @Column(nullable = false, length = 255)
    private String threadName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(columnDefinition = "TEXT")
    private String exception;

    @Column(columnDefinition = "TEXT")
    private String mdc;

}
