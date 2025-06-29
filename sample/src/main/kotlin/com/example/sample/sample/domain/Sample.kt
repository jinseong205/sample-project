package com.example.sample.sample.domain

import jakarta.persistence.*

@Entity
@Table(name = "sample")
class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val description: String,
)
