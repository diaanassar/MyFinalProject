package com.example.myfinalproject.Service;

import com.example.myfinalproject.Service.MyUserDetails;
import jakarta.transaction.Transactional;
import com.example.myfinalproject.Repository.StudentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final StudentRepository userRepo;

    public MyUserDetailsService(StudentRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException, DataAccessException {
        var user = userRepo.getStudentByEmail(s);
        var userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
