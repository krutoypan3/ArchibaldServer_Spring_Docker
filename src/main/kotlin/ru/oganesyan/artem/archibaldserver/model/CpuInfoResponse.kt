package ru.oganesyan.artem.archibaldserver.model

data class CpuInfoResponse(
    val temperatureCpu: Double? = null,
    val loadCpu: Double? = null,
    val loadMemory: Double? = null,

)