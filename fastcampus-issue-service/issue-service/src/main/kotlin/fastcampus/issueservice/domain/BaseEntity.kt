package fastcampus.issueservice.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass

// 해당 entity를 상속받은 entity들 모니터링, annotation 수행
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity (
  @CreatedDate
  val createdAt: LocalDateTime?=null,

  @LastModifiedDate
  val updatedAt: LocalDateTime?=null,
)