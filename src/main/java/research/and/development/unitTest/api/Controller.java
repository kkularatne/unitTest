package research.and.development.unitTest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import research.and.development.unitTest.api.dto.DetailDTO;
import research.and.development.unitTest.api.exception.DetailsNotFoundException;
import research.and.development.unitTest.api.service.DetailService;

/**
 * Created by kkularatne on 17/10/2017.
 */

@RestController
public class Controller {

    @Autowired
    DetailService detailService;

    @RequestMapping("api/greeting")
    public ResponseEntity greetingWithParams(@RequestParam(value = "name", required = false) String name) {
        if (null == name || name.isEmpty()) {
            name = "anonymous";
        }
        return ResponseEntity.ok(String.format("Hello %s", name));
    }

    @RequestMapping("api/getDetails")
    public ResponseEntity getDetails(@RequestParam(value = "username", required = false) String username) {

        if (null == username || username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("parameter username either null or empty");

        }

        try {
            DetailDTO dto = detailService.getDetails(username);
            return ResponseEntity.ok(dto);
        } catch (DetailsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username not found");
        }
    }
}
