package org.dbconverter.querysender.mysql

import org.dbconverter.querysender.h2.entity.H2Wine
import org.dbconverter.querysender.mysql.entity.MySqlWine
import org.dbconverter.querysender.mysql.repository.MySqlWineRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MySqlService(
    private val mySqlRepository:MySqlWineRepository
) {
    @Transactional
    fun queryRecv(wineList: List<H2Wine>){
        mySqlRepository.saveAllAndFlush(MySqlWine.toMySql(wineList))
    }

    fun check():Boolean{
        return try {
            mySqlRepository.count()
            true
        }catch (e: Exception){
            false
        }
    }
}