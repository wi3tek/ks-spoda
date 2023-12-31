package pl.spoda.ks.database.service;

import org.springframework.stereotype.Service;
import pl.spoda.ks.database.entity.BaseEntity;

import java.time.LocalDateTime;

@Service
public class BaseServiceDB {

    public static final String DEFAULT_USER = "SYSTEM";

    public void updateEntity(BaseEntity entity) {
        LocalDateTime currentDate = LocalDateTime.now();
        entity.setLastModificationDate( currentDate );
    }

    public void createEntity(BaseEntity entity) {
        LocalDateTime currentDate = LocalDateTime.now();
        entity.setCreatedBy( DEFAULT_USER );
        entity.setLastModificationDate( currentDate );
        entity.setCreationDate( currentDate );
    }
}
