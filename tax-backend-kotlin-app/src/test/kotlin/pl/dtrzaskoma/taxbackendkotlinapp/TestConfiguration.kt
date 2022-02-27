package pl.dtrzaskoma.taxbackendkotlinapp

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import pl.dtrzaskoma.taxbackendkotlinapp.app.repositories.owner.OwnerRepository
import pl.dtrzaskoma.taxbackendkotlinapp.app.services.mock.owner.MockOwnerRepository
import javax.sql.DataSource

@Configuration
class TestConfiguration {

    @Profile("E2ETest")
    @Bean
    fun testDataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.h2.Driver")
        dataSourceBuilder.url("jdbc:h2:mem:test")
        dataSourceBuilder.username("sa")
        dataSourceBuilder.password("")
        return dataSourceBuilder.build()
    }

    @Profile("integration")
    @Bean
    fun ownerRepository(): OwnerRepository {
        return MockOwnerRepository(mutableListOf())
    }
}