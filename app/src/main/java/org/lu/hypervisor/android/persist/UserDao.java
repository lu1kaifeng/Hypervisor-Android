package org.lu.hypervisor.android.persist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from user")
    List<User> getALl();
    @Query("delete from user")
    void delAll();
    @Query("select COUNT(*) from user")
    int entryCount();
    @Insert
    void insert(User user);
}
