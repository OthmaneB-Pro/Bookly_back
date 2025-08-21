package bookly_backend.service;

import bookly_backend.entity.UserEntity;
import bookly_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("username not found : "+ username)
        );
    }

    public List<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}
