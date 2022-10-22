package com.board.security;

import com.board.domain.MemberVO;
import com.board.mapper.MemberMapper;
import com.board.security.domain.CustomUser;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    @Setter(onMethod_ = @Autowired)
    private MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("username : " + username);

        // userName = userid
        MemberVO memberVO = mapper.read(username);

        log.warn("queried by member mapper : " + memberVO);
        return memberVO == null? null : new CustomUser(memberVO);
    }
}
