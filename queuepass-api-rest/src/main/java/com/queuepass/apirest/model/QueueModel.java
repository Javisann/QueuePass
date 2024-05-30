package com.queuepass.apirest.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queue")
public class QueueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime date;

    private Integer num_personas;

    // Funcion para que guarde la fecha actual en la base de datos
    @PrePersist
    protected void onCreated() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }
}
