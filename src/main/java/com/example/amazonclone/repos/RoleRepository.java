package com.example.amazonclone.repos;

import com.example.amazonclone.models.Role;

public interface RoleRepository extends RefreshableRepository<Role, Long> {
    public Role findByRoleName(String roleName);
}
