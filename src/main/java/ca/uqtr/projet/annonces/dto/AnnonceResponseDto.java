// src/main/java/ca/uqtr/projet/annonces/dto/AnnonceResponseDto.java
package ca.uqtr.projet.annonces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnonceResponseDto {

    private Long id;
    private String titre;
    private String descriptionCourte;
    private String descriptionLongue;
    @JsonProperty("mensualite")
    private Double montant;
    private LocalDate dateDisponibilite;
    private String adresse;
    private Boolean active;
    private Long ownerId;
    private Integer consultations;


    private List<String> photos = new ArrayList<>();

    // ── Getters / Setters ──────────────────────────

    public AnnonceResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescriptionCourte() { return descriptionCourte; }
    public void setDescriptionCourte(String descriptionCourte) { this.descriptionCourte = descriptionCourte; }

    public String getDescriptionLongue() { return descriptionLongue; }
    public void setDescriptionLongue(String descriptionLongue) { this.descriptionLongue = descriptionLongue; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public LocalDate getDateDisponibilite() { return dateDisponibilite; }
    public void setDateDisponibilite(LocalDate dateDisponibilite) { this.dateDisponibilite = dateDisponibilite; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public Integer getConsultations() { return consultations; }
    public void setConsultations(Integer consultations) { this.consultations = consultations; }

}