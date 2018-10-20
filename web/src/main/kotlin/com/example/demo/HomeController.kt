package com.example.demo

import com.example.persistence.TestingRepo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "MVC PoC"
        model["text"] = TestingRepo().testing()
        return "index"
    }

}