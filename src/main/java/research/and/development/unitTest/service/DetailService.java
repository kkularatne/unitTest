package research.and.development.unitTest.service;

import org.springframework.stereotype.Service;
import research.and.development.unitTest.dto.DetailDTO;
import research.and.development.unitTest.exception.DetailsNotFoundException;

/**
 * Created by kkularatne on 17/10/2017.
 */
@Service
public class DetailService {
    public DetailDTO getDetails(String username) throws DetailsNotFoundException {
        if ("test".equals(username)) {
            throw new DetailsNotFoundException();
        }
        return new DetailDTO("John", "Snow");
    }
}
