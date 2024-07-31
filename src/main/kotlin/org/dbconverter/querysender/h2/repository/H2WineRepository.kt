package org.dbconverter.querysender.h2.repository

import org.dbconverter.querysender.h2.entity.H2Wine
import org.springframework.data.jpa.repository.JpaRepository

interface H2WineRepository: JpaRepository<H2Wine, Long>