package com.example.demo.sample.domain.dto

import java.io.Serializable

data class SampleDto(
    val id: Long,
    val name: String,
    val description: String,
) : Serializable
