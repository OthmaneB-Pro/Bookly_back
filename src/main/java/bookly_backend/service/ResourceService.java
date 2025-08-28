package bookly_backend.service;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.entity.UserEntity;
import bookly_backend.enums.Type;
import bookly_backend.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserService userService;


    public void createResource(ResourceEntity resource) throws Exception {
        if(resource.getUser() == null || resource.getUser().getId() == 0){
            throw new Exception("Utilisateur introuvable 1");
        }

        UserEntity user = userService.getUserById(resource.getUser().getId());
        if(user == null){
            throw new Exception("Utilisateur introuvable 2");
        }
        resource.setType(resource.getType() != null ? resource.getType() : Type.ROOM);
        resource.setDate(new Date());
        resource.setUser(user);
        resourceRepository.save(resource);
    }

    @Transactional
    public void deleteResource(Long id) throws Exception {
        if (!resourceRepository.existsById(id)) {
            throw new Exception("Ressource introuvable");
        }
        this.resourceRepository.deleteById(id);
    }

    @Transactional
    public void updateResource(Long id, ResourceEntity resource) throws Exception  {
        Optional<ResourceEntity> optionalResource = resourceRepository.findById(id);

        if(optionalResource.isPresent()){
            ResourceEntity existingResource = optionalResource.get();

            existingResource.setType(resource.getType());
            existingResource.setTitle(resource.getTitle());
            existingResource.setCapacity(resource.getCapacity());
            existingResource.setAvailability(resource.getAvailability());
            existingResource.setDescription(resource.getDescription());
            existingResource.setDate(new Date());

            resourceRepository.save(existingResource);
        }
        else {
            throw new Exception("Resource introuvable");
        }
    }
}
