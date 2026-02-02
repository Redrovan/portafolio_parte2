
-- 1) TABLA TBL_PERSONA
CREATE TABLE TBL_PERSONA (
    per_cedula VARCHAR(10) PRIMARY KEY,
    per_nombre VARCHAR(60),
    per_direccion TEXT
);


-- 2) TABLA TBL_ESPECIALIDAD
CREATE TABLE TBL_ESPECIALIDAD (
    esp_id SERIAL PRIMARY KEY,
    esp_nombre VARCHAR(100) NOT NULL,
    esp_descripcion VARCHAR(300)
);


-- 3) TABLA TBL_USER
CREATE TABLE TBL_USER (
    usr_id SERIAL PRIMARY KEY,
    usr_email VARCHAR(100) NOT NULL UNIQUE,
    usr_password VARCHAR(255) NOT NULL,
    usr_role VARCHAR(20) NOT NULL,
    usr_active BOOLEAN,
    usr_photo_url TEXT,
    usr_phone VARCHAR(20),
    usr_social_links VARCHAR(500),

    per_cedula VARCHAR(10),
    esp_id INTEGER,

    CONSTRAINT fk_user_persona
        FOREIGN KEY (per_cedula)
        REFERENCES TBL_PERSONA(per_cedula),

    CONSTRAINT fk_user_especialidad
        FOREIGN KEY (esp_id)
        REFERENCES TBL_ESPECIALIDAD(esp_id)
);


-- 4) TABLA TBL_PROGRAMMER_PROFILE
CREATE TABLE TBL_PROGRAMMER_PROFILE (
    pp_id SERIAL PRIMARY KEY,
    pp_bio VARCHAR(500),
    pp_experience INTEGER,
    pp_photo_url TEXT,
    pp_social_links VARCHAR(500),
    usr_id INTEGER NOT NULL,

    CONSTRAINT fk_pp_user
        FOREIGN KEY (usr_id)
        REFERENCES TBL_USER(usr_id)
);


-- 5) TABLA TBL_PARTICIPATION_TYPE
CREATE TABLE TBL_PARTICIPATION_TYPE (
    pt_id SERIAL PRIMARY KEY,
    pt_name VARCHAR(50) NOT NULL,
    pt_description VARCHAR(200)
);


-- 6) TABLA TBL_PROJECT
CREATE TABLE TBL_PROJECT (
    pro_id SERIAL PRIMARY KEY,
    pro_name VARCHAR(100) NOT NULL,
    pro_description VARCHAR(500),
    pro_repo_url TEXT,
    pro_deploy_url TEXT,
    pro_technologies VARCHAR(300),
    pro_section VARCHAR(20) NOT NULL,
    pro_active BOOLEAN,

    pt_id INTEGER,
    usr_id INTEGER NOT NULL,

    CONSTRAINT fk_project_participation
        FOREIGN KEY (pt_id)
        REFERENCES TBL_PARTICIPATION_TYPE(pt_id),

    CONSTRAINT fk_project_owner
        FOREIGN KEY (usr_id)
        REFERENCES TBL_USER(usr_id)
);


-- 7) TABLA TBL_APPOINTMENT_STATUS
CREATE TABLE TBL_APPOINTMENT_STATUS (
    st_id SERIAL PRIMARY KEY,
    st_name VARCHAR(30) NOT NULL
);


-- 8) TABLA TBL_APPOINTMENT
CREATE TABLE TBL_APPOINTMENT (
    app_id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    programmer_id INTEGER NOT NULL,
    app_date DATE NOT NULL,
    app_time TIME NOT NULL,
    st_id INTEGER NOT NULL,
    app_comment VARCHAR(500),
    app_mode VARCHAR(50),
    app_created_at DATE,

    CONSTRAINT fk_appointment_client
        FOREIGN KEY (client_id)
        REFERENCES TBL_USER(usr_id),

    CONSTRAINT fk_appointment_programmer
        FOREIGN KEY (programmer_id)
        REFERENCES TBL_USER(usr_id),

    CONSTRAINT fk_appointment_status
        FOREIGN KEY (st_id)
        REFERENCES TBL_APPOINTMENT_STATUS(st_id)
);


-- 9) TABLA AVAILABILITY
CREATE TABLE AVAILABILITY (
    id SERIAL PRIMARY KEY,
    day_name VARCHAR(20) NOT NULL,
    start_time VARCHAR(20) NOT NULL,
    end_time VARCHAR(20) NOT NULL,
    mode VARCHAR(50),

    programmer_id INTEGER NOT NULL,

    CONSTRAINT fk_availability_programmer
        FOREIGN KEY (programmer_id)
        REFERENCES TBL_USER(usr_id)
);

select * froM TBL_PROGRAMMER_PROFILE;

INSERT INTO TBL_PERSONA (per_cedula, per_nombre, per_direccion)
VALUES
('0102030405', 'Robinson Redrovan', 'Av. Universitaria, Quito'),
('0102030406', 'Ana Pérez', 'Calle 10, Guayaquil'),
('0102030407', 'Carlos Gómez', 'Av. Amazonas, Quito'),
('0102030408', 'María Torres', 'Calle 5, Cuenca');


INSERT INTO TBL_ESPECIALIDAD (esp_nombre, esp_descripcion)
VALUES
('Frontend', 'Desarrollo de interfaces con Angular, React y Vue'),
('Backend', 'APIs REST con Node.js, Java y Python'),
('Base de Datos', 'Modelado y administración de bases de datos'),
('Fullstack', 'Desarrollo completo frontend + backend');


INSERT INTO TBL_USER (usr_email, usr_password, usr_role, usr_active, usr_photo_url, usr_phone, usr_social_links, per_cedula, esp_id)
VALUES
('admin@portafolio.com', 'admin123', 'ADMIN', TRUE, 'https://i.imgur.com/admin.png', '0991234567', 'https://linkedin.com/admin', '0102030405', 4),

('dev_front@portafolio.com', 'dev123', 'PROGRAMMER', TRUE, 'https://i.imgur.com/dev.png', '0992345678', 'https://github.com/dev_front', '0102030406', 1),

('dev_back@portafolio.com', 'dev123', 'PROGRAMMER', TRUE, 'https://i.imgur.com/dev2.png', '0993456789', 'https://github.com/dev_back', '0102030407', 2),

('user1@portafolio.com', 'user123', 'USER', TRUE, 'https://i.imgur.com/user.png', '0994567890', 'https://facebook.com/user1', '0102030408', NULL);


INSERT INTO TBL_PROGRAMMER_PROFILE (pp_bio, pp_experience, pp_photo_url, pp_social_links, usr_id)
VALUES
('Frontend Developer especializado en Angular y React', 3, 'https://i.imgur.com/dev.png', 'https://linkedin.com/dev_front', 2),

('Backend Developer especializado en Node.js y Java', 4, 'https://i.imgur.com/dev2.png', 'https://linkedin.com/dev_back', 3);

INSERT INTO TBL_PARTICIPATION_TYPE (pt_name, pt_description)
VALUES
('Frontend', 'Desarrollo de interfaz'),
('Backend', 'Desarrollo de APIs'),
('Base de Datos', 'Modelado y administración DB');


INSERT INTO TBL_PROJECT (pro_name, pro_description, pro_repo_url, pro_deploy_url, pro_technologies, pro_section, pro_active, pt_id, usr_id)
VALUES
('Portafolio Web', 'Portafolio personal con proyectos', 'https://github.com/dev_front/portfolio', 'https://dev_front.vercel.app', 'Angular, Tailwind, Firebase', 'Academico', TRUE, 1, 2),

('API de Citas', 'API REST para gestionar citas', 'https://github.com/dev_back/appointments-api', 'https://api.dev_back.com', 'Java, JAX-RS, PostgreSQL', 'Laboral', TRUE, 2, 3);


INSERT INTO TBL_APPOINTMENT_STATUS (st_name)
VALUES
('PENDIENTE'),
('CONFIRMADA'),
('RECHAZADA');

INSERT INTO TBL_APPOINTMENT (client_id, programmer_id, app_date, app_time, st_id, app_comment, app_mode, app_created_at)
VALUES
(4, 2, '2026-02-05', '10:00', 1, 'Quiero una asesoría sobre Angular', 'Virtual', '2026-01-21'),
(4, 3, '2026-02-06', '15:00', 1, 'Necesito ayuda con APIs REST', 'Presencial', '2026-01-21');


INSERT INTO AVAILABILITY (day_name, start_time, end_time, mode, programmer_id)
VALUES
('Lunes', '09:00', '12:00', 'Virtual', 2),
('Miércoles', '14:00', '17:00', 'Presencial', 2),
('Martes', '10:00', '13:00', 'Virtual', 3),
('Jueves', '15:00', '18:00', 'Presencial', 3);


