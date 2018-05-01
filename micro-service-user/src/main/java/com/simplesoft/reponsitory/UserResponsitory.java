package com.simplesoft.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplesoft.bean.User;

@Repository
public interface UserResponsitory extends JpaRepository<User, Long> {

}
