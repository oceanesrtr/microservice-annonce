// src/main/java/ca/uqtr/projet/annonces/dto/AnnonceRequestDto.java
package ca.uqtr.projet.annonces.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnonceRequestDto {

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotBlank(message = "La description courte est obligatoire")
    private String descriptionCourte;

    private String descriptionLongue;

    @Min(value = 0, message = "Le montant doit être positif ou nul")
    private Double montant;

    private LocalDate dateDisponibilite;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    private List<String> photos = new ArrayList<>();

    // ── Getters / Setters ──────────────────────────

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

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }
}