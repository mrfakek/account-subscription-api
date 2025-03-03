package by.mrfakek.account.subscription.api.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
      log.error(message);
    }
}
