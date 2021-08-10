package com.example.passwordencryptionapi.contoller

import com.example.passwordencryptionapi.model.InputData
import com.example.passwordencryptionapi.model.OutputData
import com.example.passwordencryptionapi.service.EncryptionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class EncryptionController(private val encryptionService: EncryptionService) {
    @PostMapping("/encrypt")
    fun encryptMessage (@RequestParam inputData: InputData) {

    }
    @PostMapping("/decrypt")
    fun decryptMessage (@RequestParam outputData: OutputData) {

    }

}