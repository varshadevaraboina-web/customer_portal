package com.vcube.customerportal.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.customerportal.Entity.UserAccount;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	Optional<UserAccount> findByEmail(String email);

	boolean existsByEmail(String email);

}
