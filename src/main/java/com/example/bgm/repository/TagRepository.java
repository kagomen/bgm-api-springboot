package com.example.bgm.repository;

import com.example.bgm.entity.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
  Optional<Tag> findByTitle(String title);
}
