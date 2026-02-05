
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

select * froM tbl_user;

------------------------------------------
INSERT INTO TBL_PERSONA (per_cedula, per_nombre, per_direccion)
VALUES
('1100000001', 'Zoila Chiqui', 'Cuenca - Ecuador'),
('1100000002', 'Robinson Redrovan', 'Quito - Ecuador'),
('1100000003', 'Stalin Redrovan', 'Cuenca - Ecuador');

-------------------------------------------------
INSERT INTO TBL_ESPECIALIDAD (esp_nombre, esp_descripcion)
VALUES
('Fullstack', 'Desarrollo completo frontend y backend');
--


SELECT * FROM TBL_PROJECT;

SELECT * FROM AVAILABILITY;

SELECT programmer_id, app_date, app_time 
FROM TBL_APPOINTMENT;

UPDATE TBL_APPOINTMENT_STATUS
SET st_name = 'PENDING'
WHERE st_name = 'PENDIENTE';

UPDATE TBL_APPOINTMENT_STATUS
SET st_name = 'APPROVED'
WHERE st_name = 'CONFIRMADA';

UPDATE TBL_APPOINTMENT_STATUS
SET st_name = 'REJECTED'
WHERE st_name = 'RECHAZADA';

SELECT * FROM TBL_APPOINTMENT_STATUS;


UPDATE TBL_APPOINTMENT a
SET st_id = s.st_id
FROM TBL_APPOINTMENT_STATUS s
WHERE a.st_id = s.st_id;


SELECT * FROM notifications;


INSERT INTO TBL_ESPECIALIDAD (esp_nombre, esp_descripcion)
VALUES
('Fullstack', 'Desarrollo completo frontend y backend'),

('Backend Java', 'APIs REST, Spring Boot, JPA, bases de datos'),

('Frontend Angular', 'Interfaces modernas con Angular y UX'),

('Frontend React', 'Aplicaciones web con React y consumo de APIs'),

('Mobile Developer', 'Aplicaciones móviles Android y Flutter'),

('Data Science', 'Análisis de datos, Python, Machine Learning'),

('DevOps', 'CI/CD, Docker, servidores y despliegues'),

('Cybersecurity', 'Seguridad informática y protección de sistemas'),

('Database Administrator', 'Gestión y optimización de bases de datos'),

('Cloud Engineer', 'Servicios en la nube AWS, Azure, Google Cloud');

DELETE FROM TBL_ESPECIALIDAD
WHERE esp_nombre = 'Fullstack'
  AND esp_descripcion = 'Desarrollo completo frontend y backend';


SELECT usr_id, usr_email, usr_phone
FROM TBL_USER;

UPDATE TBL_USER
SET usr_phone = '+593' || SUBSTRING(usr_phone FROM 2)
WHERE usr_phone IS NOT NULL;
