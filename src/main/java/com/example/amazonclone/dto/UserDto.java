package com.example.amazonclone.dto;


import com.example.amazonclone.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotEmpty(message = "Введіть дійсне ім'я.")
    private String name;
    @NotEmpty(message = "Введіть дійсне lastName.")
    private String lastName;

    @NotEmpty(message = "Введіть дійсний номер.")
    @NumberFormat
    private String number;

    @NotEmpty(message = "Введіть дійсний пароль.")
    private String password;

    @NotEmpty(message = "Повторіть введений вами пароль.")
    private String watchingPassword;



    @Transient
    @Email
    private String email;

//@Transient
//    private String photo;

//    public UserDto(User user) {
//        this.name=user.getFirstName();
//        this.lastName=user.getLastName();
////        this.number=user.getNumber();
//        this.email=user.getEmail();
////        this.photo=user.getPhotoPath();
//    }
}