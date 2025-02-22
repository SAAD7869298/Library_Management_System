package com.saad.library_management_system.repository;

import com.saad.library_management_system.model.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    Optional<List<Borrow>> findByUserId(UUID userId);

    Optional<List<Borrow>> findByBookId(UUID bookId);

//    Optional<Borrow> findByBookIdAndUserId(UUID bookId, UUID userId);
}
