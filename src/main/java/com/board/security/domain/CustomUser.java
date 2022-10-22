package com.board.security.domain;

import com.board.domain.MemberVO;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
    private MemberVO member;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO vo) {
        super(
                vo.getUserid()
                ,vo.getUserpw()
                ,vo.getAuthList().stream().map(
                        auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList())
        );
        this.member = vo;
    }
}
