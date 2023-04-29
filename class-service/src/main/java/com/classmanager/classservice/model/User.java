package com.classmanager.classservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
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

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRole role;

    @NotBlank(message = "password is required")
    @Column(name = "password", length = 256)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
