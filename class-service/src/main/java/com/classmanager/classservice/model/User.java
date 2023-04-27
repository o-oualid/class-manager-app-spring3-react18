package com.classmanager.classservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "First name is required")
    @Column(name = "f_name", length = 45)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "l_name", length = 45)
    private String lastName;

    @Column(name = "handle", length = 100)
    private String handle;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "email_is_verified")
    private boolean isEmailVerified = false;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    @NotBlank(message = "password is required")
    @Column(name = "password", length = 70)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(System.currentTimeMillis());

    @Column(name = "background_picture", length = 100)
    private String backgroundPicture;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY)
    private List<UserClassroom> userClassrooms;


    public User(String firstName, String lastName, String handle, String email, String password, UserRole role, String backgroundPicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = new Date(System.currentTimeMillis());
        this.backgroundPicture = backgroundPicture;
    }
}
