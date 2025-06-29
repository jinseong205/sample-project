package com.example.sample.sample.repository

import com.example.sample.sample.domain.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
