package com.vidasilva.dizdiz.repository;

import com.vidasilva.dizdiz.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
