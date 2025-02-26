package com.saad.library_management_system.repository;

import com.saad.library_management_system.model.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    Optional<List<Borrow>> findByUser_UserId(UUID userId);

    Optional<List<Borrow>> findByBook_BookId(UUID bookId);

    Optional<Borrow> findByBook_BookIdAndUser_UserId(UUID bookId, UUID userId);
}
