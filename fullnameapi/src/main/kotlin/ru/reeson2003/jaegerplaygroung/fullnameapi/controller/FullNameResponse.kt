package ru.reeson2003.jaegerplaygroung.fullnameapi.controller

import com.fasterxml.jackson.annotation.JsonProperty

class FullNameResponse(
    val id: Long,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String
)
