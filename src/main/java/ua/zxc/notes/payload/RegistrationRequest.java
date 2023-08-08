package ua.zxc.notes.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank(message = "The username can't be blank.")
    @Size(min = 2, max = 50, message = "The length of username must be from 2 to 50 characters.")
    private String username;

    @NotBlank(message = "The email can't be blank.")
    @Email(message = "This isn't email format.")
    private String email;

    @NotBlank(message = "The password can't be blank.")
    @Size(min = 8, max = 20, message = "The length of password must be from 8 to 20 characters.")
    private String password;
}
