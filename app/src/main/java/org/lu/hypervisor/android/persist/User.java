package org.lu.hypervisor.android.persist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;
    public String userName;
    public String password;
    public String token;
}
