package com.loadinggames.api.repository;

import com.loadinggames.api.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository <Login, Long>{

    Login findByUsuario(String email);
}
