package bookly_backend.repository;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.entity.UserEntity;
import bookly_backend.enums.Role;
import bookly_backend.enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ResourceRepositoryTest {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldCreateNewResource() {
        UserEntity user = new UserEntity(null, "othmane", "othmane.baar@gmail.com", "Othmane98?", Role.USER);
        UserEntity savedUser = this.userRepository.save(user);
    ResourceEntity resourceEntity = new ResourceEntity(null, Type.ROOM, "Pizza", "La pizza 4 fromages", 4, new Date(), true,  savedUser);

    ResourceEntity newResource = this.resourceRepository.save(resourceEntity);


        assertThat(newResource.getId()).isNotNull();
        assertThat(newResource.getTitle()).isEqualTo("Pizza");
        assertThat(newResource.getUser().getId()).isEqualTo(savedUser.getId());
    }

    @Test
    void shouldDeleteResource() {
        UserEntity user = new UserEntity(null, "othmane", "othmane.baar@gmail.com", "Othmane98?", Role.USER);
        UserEntity savedUser = this.userRepository.save(user);
        ResourceEntity resourceEntity = new ResourceEntity(null, Type.ROOM, "Pizza", "La pizza 4 fromages", 4, new Date(), true,  savedUser);
        ResourceEntity savedResource = this.resourceRepository.save(resourceEntity);

        assertThat(savedResource.getTitle()).isNotNull();

        this.resourceRepository.deleteById(savedResource.getId());

        Optional<ResourceEntity> deletedResource = resourceRepository.findById(savedResource.getId());
        assertThat(deletedResource).isEmpty();


    }

    @Test
    void shouldUpdateResource() {
        UserEntity user = new UserEntity(null, "othmane", "othmane.baar@gmail.com", "Othmane98?", Role.USER);
        UserEntity savedUser = this.userRepository.save(user);
        ResourceEntity resourceEntity = new ResourceEntity(null, Type.ROOM, "Pizza", "La pizza 4 fromages", 4, new Date(), true,  savedUser);

        resourceEntity.setType(Type.EVENT);
        resourceEntity.setTitle("Burger");

        ResourceEntity newResource = this.resourceRepository.save(resourceEntity);


        assertThat(newResource.getId()).isNotNull();
        assertThat(newResource.getTitle()).isEqualTo("Burger");
        assertThat(newResource.getUser().getId()).isEqualTo(savedUser.getId());
    }
}