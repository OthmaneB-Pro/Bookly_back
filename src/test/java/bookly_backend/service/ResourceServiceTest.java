package bookly_backend.service;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.entity.UserEntity;
import bookly_backend.enums.Role;
import bookly_backend.enums.Type;
import bookly_backend.repository.ResourceRepository;
import bookly_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {
    @Mock
    ResourceRepository resourceRepository;

    @InjectMocks
    ResourceService resourceService;

    @Mock
    UserRepository userRepository;


    @Test
    void ShouldFindAllResource() {
        UserEntity user1 = new UserEntity(null, "Hamza", "hamza.baar@gmail.com", "Othme98?", Role.USER);
        UserEntity savedUser1 = this.userRepository.save(user1);
        UserEntity user2 = new UserEntity(null, "othmane", "othmane.baar@gmail.com", "Othmane98?", Role.ADMIN);
        UserEntity savedUser2 = this.userRepository.save(user2);
        ResourceEntity res1 = new ResourceEntity(null, Type.ROOM, "Pizza", "La pizza 4 fromages", 4, new Date(), true,  savedUser1);
        ResourceEntity res2 = new ResourceEntity(null, Type.EVENT, "Burger", "Burger BBQ", 4, new Date(), false,  savedUser2);

        when(resourceRepository.findAll()).thenReturn(List.of(res1, res2));

        List<ResourceEntity> listRes = resourceService.findAllResource();

        assertThat(listRes).hasSize(2);

    }
}