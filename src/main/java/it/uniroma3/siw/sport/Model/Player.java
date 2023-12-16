package it.uniroma3.siw.sport.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;

    private LocalDate date;
    private String place;
    private Role role;

    private LocalDate startTex;
    private LocalDate endTex;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @OneToOne
    private Image image;

    @OneToOne
    private Team team;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getStartTex() {
        return startTex;
    }

    public void setStartTex(LocalDate startTex) {
        this.startTex = startTex;
    }

    public LocalDate getEndTex() {
        return endTex;
    }

    public void setEndTex(LocalDate endTex) {
        this.endTex = endTex;
    }
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
