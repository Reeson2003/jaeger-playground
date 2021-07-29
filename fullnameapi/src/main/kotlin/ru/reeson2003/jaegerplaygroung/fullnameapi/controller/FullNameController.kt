package ru.reeson2003.jaegerplaygroung.fullnameapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.reeson2003.jaegerplaygroung.fullnameapi.service.FullName
import ru.reeson2003.jaegerplaygroung.fullnameapi.service.FullNameService

@RestController
@RequestMapping("/v1")
class FullNameController(private val service: FullNameService) {

    @GetMapping("/full_name")
    fun readAll() = service.readAll()
        .map { convert(it) }

    @GetMapping("/full_name/{id}")
    fun readOne(@PathVariable("id") id: Long) = service.readOne(id)
        .map { convert(it) }

    private fun convert(it: FullName) =
        FullNameResponse(it.id, it.firstname, it.lastname)

}