package com.classmanager.classservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UserClassroomKey implements Serializable {
    private String userId;

    private String classroomId;
}
