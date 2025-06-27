package vn.tayjava.controller.Request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PostUserRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String status;
    private String Type;
}
