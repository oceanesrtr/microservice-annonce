// src/main/java/ca/uqtr/projet/annonces/model/Annonce.java
package ca.uqtr.projet.annonces.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "annonces")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(name = "description_courte")
    private String descriptionCourte;

    @Column(name = "description_longue", columnDefinition = "TEXT")
    private String descriptionLongue;

    private Double montant;

    @Column(name = "date_disponibilite")
    private LocalDate dateDisponibilite;

    private String adresse;

    private Boolean active;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(
            name = "annoncephotos",
            joinColumns = @JoinColumn(name = "annonceid")
    )
    @Column(name = "photourl", columnDefinition = "TEXT")
    private List<String> photos = new ArrayList<>();

    // ── Constructeur ─────────────────────────────────────────

    public Annonce() {}

    // ── Getters / Setters ────────────────────────────────────

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }
}