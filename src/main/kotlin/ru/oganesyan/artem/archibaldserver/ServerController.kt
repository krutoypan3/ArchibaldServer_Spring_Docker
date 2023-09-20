package ru.oganesyan.artem.archibaldserver

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


@Tag(name = "server_controller")
@Controller
class ServerController {

    @RequestMapping("/")
    fun testGet(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index.html"
        return modelAndView
    }
}