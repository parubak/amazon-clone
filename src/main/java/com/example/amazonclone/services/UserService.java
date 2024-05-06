package com.example.amazonclone.services;

import com.example.amazonclone.dto.UserDto;
import com.example.amazonclone.models.Role;
import com.example.amazonclone.models.User;
import com.example.amazonclone.repos.RoleRepository;
import com.example.amazonclone.repos.UserRepository;
import com.example.amazonclone.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public UserService(){
    }

    public void saveUserRegistration(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(),userDto.getWatchingPassword()))
            throw new RuntimeException("Паролі не співпадають!!!");

        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        User user= User.builder().firstName(userDto.getName())
                .email(userDto.getNumber())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(List.of(role))
                .build();

        userRepository.save(user);
        System.out.println("userDto = " + userDto+"saveUserRegistrationEnd");
    }



    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findFirstByNumber(phoneNumber);
    }
    public User findUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }




    public User uploadUserPhoto(User user, MultipartFile multipartFile, String uploadPath) throws IOException {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        User userDb = findUserByPhoneNumber(user.getNumber());
        fileName = user.getId() + "_" + UUID.randomUUID()+fileName;

        String delPhoto = userDb.getNumber();
        userDb.setNumber(fileName);


        FileUploadUtil.saveFile(Paths.get(uploadPath, userDb.getId().toString()).toString()
                , fileName, multipartFile, delPhoto);

        userRepository.save(userDb);
        return userDb;
    }


    public User findUserById(long id) {
        return userRepository.getReferenceById(id);
    }


    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByEmail(auth.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrNumber) throws UsernameNotFoundException {

        User user = userRepository.findFirstByEmail(usernameOrNumber);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail()
                    ,user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid number or password");
        }
    }






}