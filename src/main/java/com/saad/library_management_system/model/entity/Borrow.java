package com.saad.library_management_system.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "borrowed_books")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private UUID borrowId;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id" , nullable = false)
    private User user;

}
