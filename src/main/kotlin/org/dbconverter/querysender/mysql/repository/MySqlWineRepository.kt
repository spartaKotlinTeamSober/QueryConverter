package org.dbconverter.querysender.mysql.repository

import org.dbconverter.querysender.mysql.entity.MySqlWine
import org.springframework.data.jpa.repository.JpaRepository

interface MySqlWineRepository: JpaRepository<MySqlWine, Long>