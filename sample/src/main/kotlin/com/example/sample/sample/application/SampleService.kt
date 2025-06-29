package com.example.sample.sample.application

import com.example.sample.sample.domain.SampleMapper
import com.example.sample.sample.domain.dto.SampleCommand
import com.example.sample.sample.domain.dto.SampleDto
import com.example.sample.sample.repository.SampleRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class SampleService(
    private val sampleRepository: SampleRepository,
) {
    @CacheEvict(cacheNames = ["sampleAll"], allEntries = true)
    fun createSample(command: SampleCommand): SampleDto {
        val entity = SampleMapper.toEntity(command)
        val saved = sampleRepository.save(entity)
        return SampleMapper.toDto(saved)
    }

    @Cacheable(cacheNames = ["sampleAll"])
    fun getAll(): List<SampleDto> = sampleRepository.findAll().map(SampleMapper::toDto)

    @CacheEvict(cacheNames = ["sampleAll"], allEntries = true)
    fun deleteSample(id: Long) {
        sampleRepository.deleteById(id)
    }
}
