package com.krakus.bizimsemt.auth;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("authUserGroups")
@TypeAlias("authUserGroup")
public class AuthGroup {
    @Id
    private ObjectId id;
    private String username;
    private String authGroup;

    public AuthGroup(ObjectId id, String username, String authGroup) {
        this.id = id;
        this.username = username;
        this.authGroup = authGroup;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
