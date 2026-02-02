package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;

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

    @Column(name = "pro_deploy_url")
    private String deployUrl;

    @Column(name = "pro_technologies", length = 300)
    private String technologies;

    @Enumerated(EnumType.STRING)
    @Column(name = "pro_section", nullable = false)
    private ProjectSection section;

    @ManyToOne
    @JoinColumn(name = "pt_id")
    private ParticipationType participationType;

    @Column(name = "pro_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    @JsonbTransient // Reemplaza a @JsonIgnore para corregir el error 500 de recursividad
    private User owner;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }
    public String getDeployUrl() { return deployUrl; }
    public void setDeployUrl(String deployUrl) { this.deployUrl = deployUrl; }
    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }
    public ProjectSection getSection() { return section; }
    public void setSection(ProjectSection section) { this.section = section; }
    public ParticipationType getParticipationType() { return participationType; }
    public void setParticipationType(ParticipationType participationType) { this.participationType = participationType; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}