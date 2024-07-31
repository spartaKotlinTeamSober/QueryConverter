package org.dbconverter.querysender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuerySenderApplication

fun main(args: Array<String>) {
    runApplication<QuerySenderApplication>(*args)
}
