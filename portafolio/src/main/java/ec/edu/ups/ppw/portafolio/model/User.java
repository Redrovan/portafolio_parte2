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

    @Column(name = "usr_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "usr_role", nullable = false)
    private Role role;

    @Column(name = "usr_active")
    private boolean active;

    @Column(name = "usr_photo_url")
    private String photoUrl;

    @Column(name = "usr_phone")
    private String phone;

    @Column(name = "usr_social_links", length = 500)
    private String socialLinks;

    // ✅ CASCADE agregado (AQUÍ ESTABA EL PROBLEMA)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "per_cedula", referencedColumnName = "per_cedula")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "esp_id")
    private Especialidad especialidad;

    // =====================
    // GETTERS Y SETTERS
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
