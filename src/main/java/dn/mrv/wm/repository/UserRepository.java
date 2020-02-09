package dn.mrv.wm.repository;

import dn.mrv.wm.model.User;

/**
 * Extends Jpa repo & declare custom query
 */
public interface UserRepository extends MyBaseRepository<User, Long> {
    User findByUsername(String username);
}
