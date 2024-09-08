package com.stream.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.stream.model.entities.Admin;
import com.stream.repositories.AdminRepository;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String pass = authentication.getCredentials().toString();
		
		Optional<Admin> res = adminRepository.findByName(name);
		if(res.isEmpty()) {
			throw new BadCredentialsException("Admin name not found");
		}
		
		Admin a = res.get();
		
		if(name.equals(a.getName()) && pass.equals(a.getPassword())) {

			List<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new UsernamePasswordAuthenticationToken(name, pass, list);
		}else {
			throw new BadCredentialsException("Wrong password");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
