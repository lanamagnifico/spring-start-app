package com.newvision.springstart.service;

import com.newvision.springstart.dao.UserRepository;
import com.newvision.springstart.entity.Role;
import com.newvision.springstart.entity.User;
import com.newvision.springstart.entity.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public User save(User user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        user.addRole(roleService.findRoleByName(RolesEnum.ROLE_USER.toString()));
        User dbUser = userRepository.save(user);
        logger.info("User "+dbUser.getUserName()+" created.");
        return dbUser;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPass(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }

    @Override
    public void delete(int theId) {
        Optional<User> theUser = userRepository.findById(theId);
        if (theUser.isPresent()) {
            userRepository.delete(theUser.get());
        }
    }
}
