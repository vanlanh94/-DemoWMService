package dn.mrv.wm.service.impl;

import dn.mrv.wm.dao.UserDao;
import dn.mrv.wm.model.User;
import dn.mrv.wm.repository.UserRepository;
import dn.mrv.wm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List findAll() {
        List list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User create(User entity) {
        if (entity.getId() != 0) return null;
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public User update(User entity) {
        Optional<User> user = userRepository.findById(entity.getId());
        if (!user.isPresent()) return null;     // todo: handle exception
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) return false;
        userRepository.deleteById(id);
        return true;
    }

}
