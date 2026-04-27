package ca.uqtr.projet.annonces.controller;

import ca.uqtr.projet.annonces.dto.AnnonceRequestDto;
import ca.uqtr.projet.annonces.dto.AnnonceResponseDto;
import ca.uqtr.projet.annonces.service.AnnonceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping
    public ResponseEntity<List<AnnonceResponseDto>> getAllAnnonces() {
        return ResponseEntity.ok(annonceService.getAllAnnonces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceResponseDto> getAnnonceById(@PathVariable Long id) {
        return ResponseEntity.ok(annonceService.getAnnonceById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<List<AnnonceResponseDto>> getMyAnnonces(@RequestParam Long ownerId) {
        return ResponseEntity.ok(annonceService.getMyAnnonces(ownerId));
    }

    @PostMapping
    public ResponseEntity<AnnonceResponseDto> createAnnonce(@Valid @RequestBody AnnonceRequestDto dto,
                                                            @RequestParam Long ownerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(annonceService.createAnnonce(dto, ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceResponseDto> updateAnnonce(@PathVariable Long id,
                                                            @Valid @RequestBody AnnonceRequestDto dto,
                                                            @RequestParam Long ownerId) {
        return ResponseEntity.ok(annonceService.updateAnnonce(id, dto, ownerId));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<AnnonceResponseDto> activateAnnonce(@PathVariable Long id,
                                                              @RequestParam Long ownerId) {
        return ResponseEntity.ok(annonceService.activateAnnonce(id, ownerId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<AnnonceResponseDto> deactivateAnnonce(@PathVariable Long id,
                                                                @RequestParam Long ownerId) {
        return ResponseEntity.ok(annonceService.deactivateAnnonce(id, ownerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id,
                                              @RequestParam Long ownerId) {
        annonceService.deleteAnnonce(id, ownerId);
        return ResponseEntity.noContent().build();
    }
}