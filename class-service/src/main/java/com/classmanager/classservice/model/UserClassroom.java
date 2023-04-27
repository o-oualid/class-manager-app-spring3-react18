package com.classmanager.classservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserClassroom {
    @EmbeddedId
    private UserClassroomKey id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("userId")

    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("classroomId")
    private Classroom classroom;
}
