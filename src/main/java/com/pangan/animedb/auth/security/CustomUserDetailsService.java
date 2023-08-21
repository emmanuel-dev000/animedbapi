//package com.pangan.animedb.auth.security;
//
//import com.pangan.animedb.auth.dao.Role;
//import com.pangan.animedb.auth.dao.UserAuth;
//import com.pangan.animedb.auth.dao.UserAuthRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserAuthRepository userAuthRepository;
//
//    @Autowired
//    public CustomUserDetailsService(UserAuthRepository userAuthRepository) {
//        this.userAuthRepository = userAuthRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserAuth userAuth = userAuthRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        List<GrantedAuthority> grantedAuthorityList = mapRolesToGrantedAuthorityList(userAuth.getRoleList());
//        return User.withUsername(userAuth.getUsername())
//                .password(userAuth.getPassword())
//                .authorities(grantedAuthorityList)
//                .build();
//
//    }
//
//    private List<GrantedAuthority> mapRolesToGrantedAuthorityList(List<Role> roleList) {
//        return roleList.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
//}
