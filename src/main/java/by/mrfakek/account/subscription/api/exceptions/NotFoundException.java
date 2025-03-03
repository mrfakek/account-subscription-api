package by.mrfakek.account.subscription.api.exceptions;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity, Long id) {
        super(String.format("%s with id %s not found", entity, id));
        log.error("{} with id {} not found", entity, id);
    }
    public NotFoundException(String message){super(message);
    log.error(message);}
}
