package vn.tayjava.services;

import vn.tayjava.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(Long userId);
}
