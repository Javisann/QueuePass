package com.queuepass.apirest.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyModel {

    @Id
    private Long id;
    private String name;
    private String location;
    private String contactEmail;
    private String phoneNumber;
    private String image;

}