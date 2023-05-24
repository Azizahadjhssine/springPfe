package com.app.Entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class User implements UserDetails{
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		//@GeneratedValue(strategy = GenerationType.TABLE)

	    private Long id;
	    private String firstname;
	    private String lastname;
	    private String password;
	    private String email;
	    
	    /**@ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(  name = "user_roles",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();*/
	    

	    @Enumerated(EnumType.STRING)
	    private UserRole role;
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			/*List<GrantedAuthority> authorities = this.roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());*/
	        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}


		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

}
