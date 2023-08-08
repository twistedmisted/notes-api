package ua.zxc.notes.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "The username can't be blank.")
    private String username;

    @NotBlank(message = "The password can't be blank.")
    @Size(min = 8, max = 20, message = "The password is incorrect.")
    private String password;
}
