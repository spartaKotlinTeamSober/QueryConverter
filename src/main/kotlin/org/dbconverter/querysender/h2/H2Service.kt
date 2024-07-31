package org.dbconverter.querysender.h2

import org.dbconverter.querysender.h2.entity.H2Wine
import org.dbconverter.querysender.h2.repository.H2WineRepository
import org.dbconverter.querysender.mysql.entity.MySqlWine
import org.springframework.stereotype.Service

@Service
class H2Service(
    private val h2Repository: H2WineRepository
) {
    fun querySend(): List<H2Wine>{
        return h2Repository.findAll()
    }

    fun check():Boolean{
        return try {
            h2Repository.count()
            true
        }catch (e: Exception){
            false
        }
    }
}