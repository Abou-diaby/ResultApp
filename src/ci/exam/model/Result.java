package ci.exam.model;

import java.util.Date;

public class Result {
    private int id;
    private String matricule;
    private boolean statut;
    private float moyenne;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String ecole;

    // Constructeur avec tous les paramètres
    public Result(int id, String matricule, boolean statut, float moyenne, String nom, String prenom, Date dateNaissance, String ecole) {
        this.id = id;
        this.matricule = matricule;
        this.statut = statut;
        this.moyenne = moyenne;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ecole = ecole;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public boolean getStatut() {
        return statut;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getEcole() {
        return ecole;
    }
    
 // Setters
    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
}

