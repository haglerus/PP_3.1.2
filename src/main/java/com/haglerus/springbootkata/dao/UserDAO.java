package com.haglerus.springbootkata.dao;

import com.haglerus.springbootkata.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getUser(Long id);
    void add(User user);

    void update(User user);

    void delete(Long id);
}
