package pojomodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierCreationPojo {

    private String login;
    private String password;
    private String firstName;

    public CourierCreationPojo(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
