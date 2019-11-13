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
//import java.util.logging.Logger;
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

    //private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public User save(User theUser) {
        User dbUserByName = userRepository.findByUserName(theUser.getUserName());
        if (dbUserByName == null && theUser.getId() == 0) {
            // this is new user 
            theUser.setUserName(theUser.getUserName().trim());
            theUser.setUserPass(passwordEncoder.encode(theUser.getUserPass()));
            theUser.addRole(roleService.findRoleByName(RolesEnum.ROLE_USER.toString()));
            dbUserByName = userRepository.save(theUser);
        } else if (dbUserByName == null && theUser.getId() != 0) {
            // only update name and pass
            theUser.setUserName(theUser.getUserName().trim());
            theUser.setUserPass(passwordEncoder.encode(theUser.getUserPass())); 
            dbUserByName = userRepository.save(theUser);
        } else if (dbUserByName.getId() == theUser.getId()) {
            // only update pass
            theUser.setUserPass(passwordEncoder.encode(theUser.getUserPass())); 
            dbUserByName = userRepository.save(theUser);
        } else if (dbUserByName.getId() != theUser.getId()) {
            // the user with the same name is allready exist
            throw new RuntimeException("This name is already exist");
        }
        return dbUserByName;
    }

    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username.");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName);
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
