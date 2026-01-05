package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PROGRAMMER_PROFILE")
public class ProgrammerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pp_id")
    private Long id;

    @Column(name = "pp_bio", length = 500)
    private String bio;

    @Column(name = "pp_experience")
    private int experienceYears;

    @OneToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
