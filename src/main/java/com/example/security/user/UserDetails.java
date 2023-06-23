package com.example.security.user;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

// UserDetails -> 하나 이상의 권한을 가지고 있음.
public interface UserDetails extends Serializable {
    String getUsername();
    String getPassword();
    // 유저가 가지고 있는 권한.
    Collection<? extends GrantedAuthority> getAuthorities();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
