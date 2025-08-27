package bookly_backend.controller;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.security.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("resource")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
        public ResponseEntity<String> createResource(@RequestBody ResourceEntity resource) throws Exception {
        this.resourceService.createResource(resource);
        return ResponseEntity.ok("Bien crée");
    }
}
