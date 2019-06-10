package com.br.marte.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.marte.app.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
