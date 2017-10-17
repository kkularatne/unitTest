package research.and.development.unitTest.api.dto;

/**
 * Created by kkularatne on 17/10/2017.
 */
public class DetailDTO {
    String firstName;
    String lasName;

    public DetailDTO(String firstName, String lasName) {
        this.firstName = firstName;
        this.lasName = lasName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }
}
