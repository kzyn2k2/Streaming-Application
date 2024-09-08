package com.stream.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {
	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name = "activated")
	private boolean activated;
	
	private String accountName;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<View> views;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<SportChoice> sportChoices;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_"+role));
		return list;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return activated;
	}

}
