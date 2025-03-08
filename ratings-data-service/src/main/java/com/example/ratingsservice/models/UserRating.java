package com.example.ratingsservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserRating {
    @Id
    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    private String userId;
    @Column(name = "username", nullable = false)
    private String username;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;
}
