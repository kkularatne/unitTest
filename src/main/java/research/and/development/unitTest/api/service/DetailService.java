package research.and.development.unitTest.api.service;

import org.springframework.stereotype.Service;
import research.and.development.unitTest.api.dto.DetailDTO;
import research.and.development.unitTest.api.exception.DetailsNotFoundException;

/**
 * Created by kkularatne on 17/10/2017.
 */
@Service
public class DetailService {
    public DetailDTO getDetails(String username) throws DetailsNotFoundException{
        return new DetailDTO("John", "Snow");
    }
}
