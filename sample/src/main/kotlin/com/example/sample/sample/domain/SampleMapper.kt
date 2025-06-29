package com.example.sample.sample.domain

import com.example.sample.sample.domain.dto.SampleCommand
import com.example.sample.sample.domain.dto.SampleDto
import com.example.sample.sample.interfaces.rqrs.SampleRs

object SampleMapper {
    fun toDto(entity: Sample): SampleDto = SampleDto(id = entity.id, name = entity.name, description = entity.description)

    fun toEntity(command: SampleCommand): Sample = Sample(name = command.name, description = command.description)

    fun toResponse(dto: SampleDto): SampleRs = SampleRs(id = dto.id, name = dto.name, description = dto.description)
}
