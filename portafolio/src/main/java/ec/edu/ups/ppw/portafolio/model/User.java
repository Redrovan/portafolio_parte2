package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_role", nullable = false)
    private String role;

    // ===== RELACIÓN 1 A 1 CON PERSONA =====
    @OneToOne
    @JoinColumn(
        name = "per_cedula",
        referencedColumnName = "per_cedula"
    )
    private Persona persona;
    
    //--------RELACION MUCHOS A 1 CON ESPECIALIDAD--------
    @ManyToOne
    @JoinColumn(name = "esp_id")
    private Especialidad especialidad;


    // ===== getters y setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    
    // getters y setters especialidad
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

}
