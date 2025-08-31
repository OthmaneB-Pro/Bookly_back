package bookly_backend.controller;

import bookly_backend.entity.ResourceEntity;
import bookly_backend.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable Long id) throws Exception{
        this.resourceService.deleteResource(id);
        return ResponseEntity.ok("La ressource à été supprimé avec succès");
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateResource(@PathVariable Long id, @RequestBody ResourceEntity resource) throws Exception{
        this.resourceService.updateResource(id, resource);
        return ResponseEntity.ok("La ressource est mis à jour avec succès");
    }
}
