package bookly_backend.service;

import bookly_backend.dto.AuthResponseDTO;
import bookly_backend.dto.LoginDTO;
import bookly_backend.dto.RegisterDTO;
import bookly_backend.dto.UserResponseDTO;
import bookly_backend.entity.UserEntity;
import bookly_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserResponseDTO register(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return new UserResponseDTO(user);
    }

    public AuthResponseDTO login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDTO(token);
    }
}
