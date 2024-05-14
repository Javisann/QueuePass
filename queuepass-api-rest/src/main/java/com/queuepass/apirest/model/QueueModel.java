package com.queuepass.apirest.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private UserModel userModel;

    private String date;

    private Integer num_personas;
    
}
