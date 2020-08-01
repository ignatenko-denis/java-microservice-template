package com.sample.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "client")
@Data
public class Client implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Lob
    @Column(name = "xml", length = Integer.MAX_VALUE)
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "com.sample.entity.SQLXMLType")
    private String xml;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "vip", nullable = false)
    private Boolean vip;
}
