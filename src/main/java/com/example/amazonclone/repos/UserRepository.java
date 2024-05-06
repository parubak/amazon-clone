package com.example.amazonclone.repos;


import com.example.amazonclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByNumber(String phone);

    User findFirstByEmail(String email);


}