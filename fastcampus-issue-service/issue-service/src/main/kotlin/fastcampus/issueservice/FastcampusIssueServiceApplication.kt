package fastcampus.issueservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication

// 아래 annotation을 spring boot application에 추가해줘야 'BaseEntity'에 있는 @EntityListeners이 사용가능해짐
@EnableJpaAuditing
class FastcampusIssueServiceApplication

fun main(args: Array<String>) {
  runApplication<FastcampusIssueServiceApplication>(*args)
}
