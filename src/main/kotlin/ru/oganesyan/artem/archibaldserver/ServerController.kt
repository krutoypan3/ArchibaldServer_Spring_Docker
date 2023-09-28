package ru.oganesyan.artem.archibaldserver

import com.profesorfalken.jsensors.JSensors
import com.profesorfalken.jsensors.model.components.Components
import com.profesorfalken.jsensors.model.components.Cpu
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.oganesyan.artem.archibaldserver.model.CpuInfoResponse


@Tag(name = "server_controller")
@RestController
class ServerController {

    @RequestMapping("/")
    fun testGet(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index.html"
        return modelAndView
    }


    @Operation(
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Cpu info response",
                content = [Content(schema = Schema(implementation = CpuInfoResponse::class))]
            )
        ]
    )
    @GetMapping("/api/states/sensors", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCpuTemp(): CpuInfoResponse {

        val components: Components = JSensors.get.components()

        val cpu: Cpu = components.cpus.first()
            if (cpu.sensors != null) {
                val temperatureCpu = cpu.sensors.temperatures.last().value

                val loadSensors = cpu.sensors.loads.takeLast(2)
                val loadCpu = loadSensors.first().value
                val loadMemory = loadSensors.last().value
                return CpuInfoResponse(
                    temperatureCpu = temperatureCpu,
                    loadCpu = loadCpu,
                    loadMemory = loadMemory
                )
            }
        return CpuInfoResponse()
    }
}