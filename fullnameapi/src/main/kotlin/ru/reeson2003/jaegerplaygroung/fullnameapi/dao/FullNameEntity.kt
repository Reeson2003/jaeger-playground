package ru.reeson2003.jaegerplaygroung.fullnameapi.dao

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "full_names")
class FullNameEntity(
    @Id val id: Long? = null,
    @Column(name = "firstname") val firstName: String? = null,
    @Column(name = "lastname") val lastName: String? = null
)
