package com.example.amazonclone.dto;

import com.example.amazonclone.models.User;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements DtoEntity<User, Long>{
    protected Long id;
    protected String username;
    protected String firstname;
    @Nullable
    protected String middlename;
    protected String surname;
    protected String email;
    protected String phone;
    protected String token;
    protected String roleName;
    protected Timestamp createdAt;
    private List<Long> favouriteProductColorIds = new ArrayList<>();
    private List<Long> productIds = new ArrayList<>();

    public UserDto(User entity) {
        this.username = entity.getUsername();
        this.firstname = entity.getFirstname();
        this.middlename = entity.getMiddlename();
        this.surname = entity.getSurname();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.roleName = entity.getRole().getRoleName();
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        if(entity.getFavouriteProductColors() != null)
            entity.getFavouriteProductColors().forEach(x->favouriteProductColorIds.add(x.getId()));
        if(entity.getProducts() != null)
            entity.getProducts().forEach(x-> productIds.add(x.getId()));
    }

    @Override
    public User buildEntity() {
        User user = new User();

        if(id != null)
            user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setMiddlename(middlename);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phone);

        return user;
    }

    @Override
    public User buildEntity(Long id) {
        this.id = id;

        return buildEntity();
    }
}
