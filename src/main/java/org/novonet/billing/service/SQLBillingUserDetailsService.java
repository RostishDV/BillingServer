package org.novonet.billing.service;

import org.novonet.billing.models.BillingUser;
import org.novonet.billing.repo.BillingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SQLBillingUserDetailsService implements UserDetailsService {
    @Autowired
    private BillingUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BillingUser> optionalUser = repository.findByName(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        BillingUser user = optionalUser.get();
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
