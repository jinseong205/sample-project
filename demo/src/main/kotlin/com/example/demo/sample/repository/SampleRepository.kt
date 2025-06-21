package com.example.demo.sample.repository

import com.example.demo.sample.domain.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>
