package mx.app.petcare.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.app.petcare.config.JwtResponse;
import mx.app.petcare.config.JwtTokenUtil;
import mx.app.petcare.dto.AuthRequestDto;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public ResponseEntity<JwtResponse> createTokenAuthentication(AuthRequestDto authRequestDto) {
		if(authentication(authRequestDto.getEmail(), authRequestDto.getPassword())){
			String token;

			try{
				UserDetails userDetails = loadUserByUsername(authRequestDto.getEmail());

				token = jwtTokenUtil.generateToken(userDetails);

				if(userRepository.findByEmail(authRequestDto.getEmail()) != null){
					UserAccount userAccount = userRepository.findByEmail(authRequestDto.getEmail());
					if(!userAccount.isEnabled()){						
						return new ResponseEntity<JwtResponse>(HttpStatus.FORBIDDEN); 
					}
				}
			}catch (Exception e){
				e.printStackTrace();
				return new ResponseEntity<JwtResponse>(HttpStatus.FORBIDDEN);
			}

			return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
		}else{
			return new ResponseEntity<JwtResponse>(HttpStatus.FORBIDDEN);
		}
	}

	public boolean authentication(String username, String password) {
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return true;
		}catch (DisabledException | BadCredentialsException e){
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRole().getName());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

	private List<GrantedAuthority> getUserAuthority(String userRol) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRol));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

	private UserDetails buildUserForAuthentication(UserAccount userAccount, List<GrantedAuthority> authorities){
		return new User(userAccount.getEmail(), userAccount.getPassword(), authorities);
	}


}
