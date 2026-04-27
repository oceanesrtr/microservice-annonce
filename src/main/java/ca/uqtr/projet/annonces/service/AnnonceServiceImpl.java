// src/main/java/ca/uqtr/projet/annonces/service/AnnonceServiceImpl.java
package ca.uqtr.projet.annonces.service;

import ca.uqtr.projet.annonces.dto.AnnonceRequestDto;
import ca.uqtr.projet.annonces.dto.AnnonceResponseDto;
import ca.uqtr.projet.annonces.exception.ForbiddenOperationException;
import ca.uqtr.projet.annonces.exception.ResourceNotFoundException;
import ca.uqtr.projet.annonces.model.Annonce;
import ca.uqtr.projet.annonces.repository.AnnonceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public List<AnnonceResponseDto> getAllAnnonces() {
        return annonceRepository.findByActiveTrue()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public AnnonceResponseDto getAnnonceById(Long id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable avec l'id : " + id));
        return toResponseDto(annonce);
    }

    @Override
    public AnnonceResponseDto createAnnonce(AnnonceRequestDto dto, Long ownerId) {
        Annonce annonce = new Annonce();
        annonce.setTitre(dto.getTitre());
        annonce.setDescriptionCourte(dto.getDescriptionCourte());
        annonce.setDescriptionLongue(dto.getDescriptionLongue());
        annonce.setMontant(dto.getMontant());
        annonce.setDateDisponibilite(dto.getDateDisponibilite());
        annonce.setAdresse(dto.getAdresse());
        annonce.setActive(true);
        annonce.setOwnerId(ownerId);
        annonce.setCreatedAt(LocalDateTime.now());
        annonce.setUpdatedAt(LocalDateTime.now());
        //  sauvegarder les photos
        if (dto.getPhotos() != null) {
            annonce.setPhotos(dto.getPhotos());
        }
        return toResponseDto(annonceRepository.save(annonce));
    }

    @Override
    public AnnonceResponseDto updateAnnonce(Long id, AnnonceRequestDto dto, Long ownerId) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable avec l'id : " + id));
        verifyOwner(annonce, ownerId);
        annonce.setTitre(dto.getTitre());
        annonce.setDescriptionCourte(dto.getDescriptionCourte());
        annonce.setDescriptionLongue(dto.getDescriptionLongue());
        annonce.setMontant(dto.getMontant());
        annonce.setDateDisponibilite(dto.getDateDisponibilite());
        annonce.setAdresse(dto.getAdresse());
        annonce.setUpdatedAt(LocalDateTime.now());
        // mettre à jour les photos seulement si fournies
        if (dto.getPhotos() != null) {
            annonce.setPhotos(dto.getPhotos());
        }
        return toResponseDto(annonceRepository.save(annonce));
    }

    @Override
    public void deleteAnnonce(Long id, Long ownerId) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable avec l'id : " + id));
        verifyOwner(annonce, ownerId);
        annonceRepository.delete(annonce);
    }

    @Override
    public List<AnnonceResponseDto> getMyAnnonces(Long ownerId) {
        return annonceRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public AnnonceResponseDto activateAnnonce(Long id, Long ownerId) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable avec l'id : " + id));
        verifyOwner(annonce, ownerId);
        annonce.setActive(true);
        annonce.setUpdatedAt(LocalDateTime.now());
        return toResponseDto(annonceRepository.save(annonce));
    }

    @Override
    public AnnonceResponseDto deactivateAnnonce(Long id, Long ownerId) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce introuvable avec l'id : " + id));
        verifyOwner(annonce, ownerId);
        annonce.setActive(false);
        annonce.setUpdatedAt(LocalDateTime.now());
        return toResponseDto(annonceRepository.save(annonce));
    }

    private void verifyOwner(Annonce annonce, Long ownerId) {
        if (annonce.getOwnerId() == null || !annonce.getOwnerId().equals(ownerId)) {
            throw new ForbiddenOperationException("Vous n'êtes pas autorisé à modifier cette annonce");
        }
    }

    private AnnonceResponseDto toResponseDto(Annonce annonce) {
        AnnonceResponseDto dto = new AnnonceResponseDto();
        dto.setId(annonce.getId());
        dto.setTitre(annonce.getTitre());
        dto.setDescriptionCourte(annonce.getDescriptionCourte());
        dto.setDescriptionLongue(annonce.getDescriptionLongue());
        dto.setMontant(annonce.getMontant());
        dto.setDateDisponibilite(annonce.getDateDisponibilite());
        dto.setAdresse(annonce.getAdresse());
        dto.setActive(annonce.getActive());
        dto.setOwnerId(annonce.getOwnerId());
        // inclure les photos dans la réponse
        dto.setPhotos(annonce.getPhotos() != null ? annonce.getPhotos() : List.of());
        return dto;
    }
}