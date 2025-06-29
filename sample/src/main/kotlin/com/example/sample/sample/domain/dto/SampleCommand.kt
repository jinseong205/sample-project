package com.example.sample.sample.domain.dto

data class SampleCommand(
    val name: String,
    val description: String,
) {
    data class Create(
        val name: String,
        val description: String,
    )
}
