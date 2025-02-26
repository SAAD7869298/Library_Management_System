package com.saad.library_management_system.repository;

import com.saad.library_management_system.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    public Optional<Book> findByTitle(String title);
}
