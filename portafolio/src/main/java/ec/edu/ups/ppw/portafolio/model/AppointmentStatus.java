package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_APPOINTMENT_STATUS")
public class AppointmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private Long id;

    @Column(name = "st_name", nullable = false, length = 30)
    private String name;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
