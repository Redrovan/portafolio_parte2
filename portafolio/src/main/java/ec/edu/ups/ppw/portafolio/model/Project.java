package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_name", length = 100, nullable = false)
    private String name;

    @Column(name = "pro_description", length = 500)
    private String description;

    @Column(name = "pro_repo_url")
    private String repositoryUrl;

    // ===== RELACIÓN CON USER =====
    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User owner;

    // ===== getters y setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
