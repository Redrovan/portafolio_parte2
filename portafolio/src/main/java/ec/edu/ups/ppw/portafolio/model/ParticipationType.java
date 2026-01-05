package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PARTICIPATION_TYPE")
public class ParticipationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_id")
    private Long id;

    @Column(name = "pt_name", nullable = false, length = 50)
    private String name;

    @Column(name = "pt_description", length = 200)
    private String description;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
