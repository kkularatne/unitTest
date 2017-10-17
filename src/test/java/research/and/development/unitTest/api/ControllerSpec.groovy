package research.and.development.unitTest.api

import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import research.and.development.unitTest.api.dto.DetailDTO
import research.and.development.unitTest.api.exception.DetailsNotFoundException
import research.and.development.unitTest.api.service.DetailService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by kkularatne on 17/10/2017.
 */
class ControllerSpec extends Specification {
    private Controller controller = new Controller();
    def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()


    void "Test controller without param"() {
        setup:
        def request = get("/api/greeting");

        when:
        def response = mockMvc.perform(request)

        then:
        response.andExpect(status().isOk())
        response.andExpect(MockMvcResultMatchers.content().string("Hello anonymous"))
    }

    void "Test controller with empty param"() {
        setup:
        def request = get("/api/greeting");
        request.param("name", "")

        when:
        def response = mockMvc.perform(request)

        then:
        response.andExpect(status().isOk())
        response.andExpect(MockMvcResultMatchers.content().string("Hello anonymous"))
    }

    void "Test controller with param"() {
        setup:
        def request = get("/api/greeting");
        request.param("name", "John")


        when:
        def response = mockMvc.perform(request)

        then:
        response.andExpect(status().isOk())
        response.andExpect(MockMvcResultMatchers.content().string("Hello John"))
    }

    void "Test getDetails for exception"() {
        setup:
        def request = get("/api/getDetails");
        request.param("username", "John")

        controller.detailService = Mock(DetailService) {
            getDetails(_) >> {
                throw new DetailsNotFoundException();
            }
        }

        when:
        def response = mockMvc.perform(request);

        then:
        response.andExpect(status().isBadRequest())
        response.andExpect(MockMvcResultMatchers.content().string("username not found"))
    }

    void "Test getDetails"() {
        setup:
        def request = get("/api/getDetails");
        request.param("username", "John")

        controller.detailService = Mock(DetailService) {
            getDetails(_) >> {
                return new DetailDTO("John", "Snow")
            }
        }

        when:
        def response = mockMvc.perform(request);

        then:
        response.andExpect(status().isOk())
        response.andExpect(MockMvcResultMatchers.content().string("{\"firstName\":\"John\",\"lasName\":\"Snow\"}"))
        response.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
    }
}
