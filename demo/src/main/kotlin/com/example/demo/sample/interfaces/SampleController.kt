package com.example.demo.sample.interfaces

import com.example.demo.sample.application.SampleService
import com.example.demo.sample.domain.SampleMapper
import com.example.demo.sample.domain.dto.SampleCommand
import com.example.demo.sample.interfaces.rqrs.SampleRq
import com.example.demo.sample.interfaces.rqrs.SampleRs
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
