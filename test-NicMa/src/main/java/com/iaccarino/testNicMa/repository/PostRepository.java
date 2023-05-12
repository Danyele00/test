package com.iaccarino.testNicMa.repository;

import com.iaccarino.testNicMa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Gestione del layer repository
 Acquisizione dei metodi per le operazioni CRUD forniti da JpaRepository.
 */
public interface PostRepository extends JpaRepository<Post,Integer> {
}
