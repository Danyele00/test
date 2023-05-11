package com.iaccarino.testNicMa.repository;

import com.iaccarino.testNicMa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
