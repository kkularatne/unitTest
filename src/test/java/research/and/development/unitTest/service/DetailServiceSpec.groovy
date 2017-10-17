package research.and.development.unitTest.service

import research.and.development.unitTest.dto.DetailDTO
import research.and.development.unitTest.exception.DetailsNotFoundException
import spock.lang.Specification

/**
 * Created by kkularatne on 17/10/2017.
 */
class DetailServiceSpec extends Specification {
    private DetailService detailService = new DetailService();

    void "Test detail service for exception"() {
        given:
        String username = "test"

        when:
        detailService.getDetails(username)

        then:
        thrown DetailsNotFoundException

    }

    void "Test detail service"() {
        given:
        String username = "jsnow"

        when:
        DetailDTO response = detailService.getDetails(username)

        then:
        response.firstName == "John"
        response.lasName == "Snow"
    }
}
