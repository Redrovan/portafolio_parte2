package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "TBL_APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "programmer_id", nullable = false)
    private User programmer;

    @Column(name = "app_date", nullable = false)
    private LocalDate date;

    @Column(name = "app_time", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "st_id", nullable = false)
    private AppointmentStatus status;

    @Column(name = "app_comment", length = 500)
    private String comment;

    @Column(name = "app_mode")
    private String mode;

    @Column(name = "app_created_at")
    private LocalDate createdAt;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }

    public User getProgrammer() { return programmer; }
    public void setProgrammer(User programmer) { this.programmer = programmer; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
}
