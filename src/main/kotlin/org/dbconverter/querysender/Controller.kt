package org.dbconverter.querysender

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val masterService: MasterService
) {
    @GetMapping("/convert")
    fun queryConvert(): ResponseEntity<Boolean> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(masterService.queryConvert())
    }

    @GetMapping("/check")
    fun check(): ResponseEntity<String> {
        return ResponseEntity.ok().body(masterService.check())
    }
}