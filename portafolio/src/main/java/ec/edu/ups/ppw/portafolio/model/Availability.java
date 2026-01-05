package ec.edu.ups.ppw.portafolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AVAILABILITY")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_name", nullable = false)
    private String day;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "programmer_id", nullable = false)
    private User programmer;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public User getProgrammer() {
        return programmer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setProgrammer(User programmer) {
        this.programmer = programmer;
    }
}
