package priceobserver.dto;

import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birth;
}
