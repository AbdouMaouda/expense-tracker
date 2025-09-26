package com.example.demo.service;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final AppUserRepository appUserRepository;

    public UserServiceImplementation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public AppUser saveUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username).orElse(null);
    }
}
