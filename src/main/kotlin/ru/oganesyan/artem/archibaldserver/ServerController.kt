package ru.oganesyan.artem.archibaldserver

import com.sun.management.OperatingSystemMXBean
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
import java.lang.management.ManagementFactory
import kotlin.math.roundToInt


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
        val osBean: OperatingSystemMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java)
        val loadMemory = (osBean.totalMemorySize - osBean.freeMemorySize).toDouble()
        val loadMemoryPercent = (loadMemory / osBean.totalMemorySize) * 100
        return CpuInfoResponse(
            loadCpu = (osBean.cpuLoad * 100 * 100).roundToInt() / 100.0,
            loadMemory = (loadMemoryPercent * 100).roundToInt() / 100.0,
        )
    }
}