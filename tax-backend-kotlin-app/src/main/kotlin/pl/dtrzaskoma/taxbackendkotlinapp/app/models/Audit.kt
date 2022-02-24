package pl.dtrzaskoma.taxbackendkotlinapp.app.models

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Audit {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private var createdDate: LocalDateTime = LocalDateTime.now()

    @Column(nullable = false, updatable = false)
    @CreatedBy
    private var createdBy: String? = null

    @Column(nullable = false, updatable = true)
    @LastModifiedDate
    private var modifiedDate: LocalDateTime? = null;

    @Column(nullable = false, updatable = true)
    @LastModifiedBy
    private var modifiedBy: String? = null
}