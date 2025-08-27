package bookly_backend.security;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.entity.UserEntity;
import bookly_backend.repository.ResourceRepository;
import bookly_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserService userService;


    public void createResource(ResourceEntity resource) throws Exception {
        System.out.println("User : "+ resource.getUser());
        if(resource.getUser() == null || resource.getUser().getId() == 0){
            throw new Exception("Utilisateur introuvable 1");
            
        }

        UserEntity user = userService.getUserById(resource.getUser().getId());
        if(user == null){
            throw new Exception("Utilisateur introuvable 2");
        }
        resource.setUser(user);
        resourceRepository.save(resource);
    }
}
