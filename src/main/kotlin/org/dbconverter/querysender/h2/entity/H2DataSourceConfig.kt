package org.dbconverter.querysender.h2.entity


import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Primary
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "deviceEntityManagerFactory",
    basePackages = ["org.dbconverter.querysender.h2.repository"],
)
class H2DataSourceConfig {

    @Primary
    @Bean(name = ["deviceDataSourceProperties"])
    @ConfigurationProperties("spring.datasource.h2")
    fun deviceDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }


    @Primary
    @Bean(name = ["deviceDataSource"])
    @ConfigurationProperties("spring.datasource.h2.configuration")
    fun dataSource(@Qualifier("deviceDataSourceProperties") deviceDataSourceProperties: DataSourceProperties): DataSource {
        return deviceDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Primary
    @Bean(name = ["deviceEntityManagerFactory"])
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("deviceDataSource") deviceDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(deviceDataSource)
            .packages("org.dbconverter.querysender.h2.entity")
            .persistenceUnit("h2")
            .build()
    }

    @Primary
    @Bean(name = ["deviceTransactionManager"])
    fun transactionManager(
        @Qualifier("deviceEntityManagerFactory") deviceEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager{
        return JpaTransactionManager(deviceEntityManagerFactory)
    }
}
