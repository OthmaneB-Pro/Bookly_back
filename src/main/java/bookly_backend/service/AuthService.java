package bookly_backend.service;

import bookly_backend.dto.AuthResponseDTO;
import bookly_backend.dto.LoginDTO;
import bookly_backend.dto.RegisterDTO;
import bookly_backend.entity.UserEntity;
import bookly_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponseDTO register(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new IllegalArgumentException("Email déjà utilisé");
        }
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponseDTO(token);
    }
}
