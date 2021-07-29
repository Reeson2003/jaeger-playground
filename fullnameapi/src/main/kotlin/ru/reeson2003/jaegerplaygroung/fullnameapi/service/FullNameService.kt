package ru.reeson2003.jaegerplaygroung.fullnameapi.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.reeson2003.jaegerplaygroung.fullnameapi.dao.FullNameEntity
import ru.reeson2003.jaegerplaygroung.fullnameapi.dao.FullNameRepository

@Service
class FullNameService(private val repository: FullNameRepository) {

    fun readAll() = Flux.fromIterable(repository.findAll())
        .flatMap { convert(it) }

    fun readOne(id: Long) = Mono.justOrEmpty(repository.findById(id))
        .flatMap { convert(it) }


    private fun convert(it: FullNameEntity) = it.id?.let { id ->
        it.firstName?.let { firstname ->
            it.lastName?.let { lastname ->
                Mono.just(FullName(id, firstname, lastname))
            }
        }
    } ?: Mono.empty()
}