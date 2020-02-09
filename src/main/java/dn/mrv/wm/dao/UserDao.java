package dn.mrv.wm.dao;

import dn.mrv.wm.model.User;
import org.springframework.data.repository.CrudRepository;
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
