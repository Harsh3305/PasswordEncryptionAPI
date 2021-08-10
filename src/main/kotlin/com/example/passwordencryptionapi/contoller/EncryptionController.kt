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
    fun encryptMessage (inputData: InputData) : OutputData{
        val byteArray: ByteArray =  encryptionService.encryptMessage(inputData)

        val encryptedMessage: OutputData = OutputData(encryptedMessage = byteArray, password = inputData.password)
        return encryptedMessage
    }
    @PostMapping("/decrypt")
    fun decryptMessage (outputData: OutputData) : InputData {
        val decryptedMessage : String =  encryptionService.decryptMessage(outputData)
        val decryptedOutput : InputData = InputData(decryptedMessage, outputData.password)
        return decryptedOutput
    }

}