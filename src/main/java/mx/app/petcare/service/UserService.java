package mx.app.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean disabled(long id) {
		UserAccount userAccount = userRepository.findById(id).get();
		userAccount.setEnabled(false);
		userAccount = userRepository.save(userAccount);
		return !userAccount.isEnabled();

	}

	public boolean enabled(long id) {
		UserAccount userAccount = userRepository.findById(id).get();
		userAccount.setEnabled(true);

		userAccount = userRepository.save(userAccount);

		return userAccount.isEnabled();

	}

	public boolean changePassword(String email, String newPassword) {
		try {
			UserAccount userAccount = userRepository.findByEmail(email);
			userAccount.setPassword(passwordEncoder.encode(newPassword));

			userRepository.save(userAccount);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
