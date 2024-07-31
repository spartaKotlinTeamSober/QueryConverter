package org.dbconverter.querysender

import org.dbconverter.querysender.h2.H2Service
import org.dbconverter.querysender.h2.entity.H2Wine
import org.dbconverter.querysender.mysql.MySqlService
import org.springframework.stereotype.Service

@Service
class MasterService(
    private val h2Service: H2Service,
    private val mySqlService: MySqlService
) {
    fun queryConvert():Boolean{
        return try {
            h2Service.querySend()
                .also { mySqlService.queryRecv(it) }
            true
        }catch (e:Exception){
            false
        }
    }

    fun check():String{
        val str=StringBuilder()
        str.append("h2 DB: ${h2Service.check()}"+"/")
        str.append("MySql DB: ${mySqlService.check()}")
        return str.toString()
    }
}