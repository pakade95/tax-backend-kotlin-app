package pl.dtrzaskoma.taxbackendkotlinapp.app.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import pl.dtrzaskoma.taxbackendkotlinapp.app.configuration.audit.AuditorAwareImpl

@Configuration
@EnableJpaAuditing
class AuditingConfiguration {

    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAwareImpl()
    }
}