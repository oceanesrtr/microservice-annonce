package ca.uqtr.projet.annonces.service;

import ca.uqtr.projet.annonces.dto.AnnonceRequestDto;
import ca.uqtr.projet.annonces.dto.AnnonceResponseDto;

import java.util.List;

public interface AnnonceService {
    List<AnnonceResponseDto> getAllAnnonces();
    AnnonceResponseDto getAnnonceById(Long id);

    AnnonceResponseDto createAnnonce(AnnonceRequestDto dto, Long ownerId);
    AnnonceResponseDto updateAnnonce(Long id, AnnonceRequestDto dto, Long ownerId);
    void deleteAnnonce(Long id, Long ownerId);

    List<AnnonceResponseDto> getMyAnnonces(Long ownerId);
    AnnonceResponseDto activateAnnonce(Long id, Long ownerId);
    AnnonceResponseDto deactivateAnnonce(Long id, Long ownerId);
}