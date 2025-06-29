package com.example.sample.sample.interfaces

import com.example.sample.sample.application.SampleService
import com.example.sample.sample.domain.SampleMapper
import com.example.sample.sample.domain.dto.SampleCommand
import com.example.sample.sample.interfaces.rqrs.SampleRq
import com.example.sample.sample.interfaces.rqrs.SampleRs
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/samples")
class SampleController(
    private val sampleService: SampleService,
) {
    @PostMapping
    fun create(
        @RequestBody request: SampleRq,
    ): SampleRs {
        val command = SampleCommand(request.name, request.description)
        val dto = sampleService.createSample(command)
        return SampleMapper.toResponse(dto)
    }

    @GetMapping
    fun getAll(): List<SampleRs> = sampleService.getAll().map(SampleMapper::toResponse)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ) {
        sampleService.deleteSample(id)
    }
}
