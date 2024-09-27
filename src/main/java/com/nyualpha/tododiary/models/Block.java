package com.nyualpha.tododiary.models;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="block")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 64)
    private String category;

    @Column(length = 128)
    private String description;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "block")
    private Set<Task> tasks;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
