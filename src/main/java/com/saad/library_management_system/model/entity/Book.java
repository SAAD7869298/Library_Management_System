package com.saad.library_management_system.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "books")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;
}
