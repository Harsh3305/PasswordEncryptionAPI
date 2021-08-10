package com.example.passwordencryptionapi.service

import com.example.passwordencryptionapi.model.InputData
import com.example.passwordencryptionapi.model.OutputData
import org.springframework.stereotype.Service
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


@Service
class EncryptionService {
    fun encryptMessage(inputData: InputData): ByteArray {
        val message: String = inputData.message
        val password: String = inputData.password

        return encrypt(message, password)
    }

    fun decryptMessage (outputData: OutputData) : String {
        val message: ByteArray = outputData.encryptedMessage
        val password: String = outputData.password

        return decrypt(message, password)
    }

    private fun encrypt(message : String,  password: String): ByteArray {
        val secret = generateSalt(password)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secret)
        val params = cipher.parameters

        val ciphertext = cipher.doFinal(message.toByteArray(charset("UTF-8")))
        return ciphertext
    }

    private fun decrypt(ciphertext : ByteArray,  password: String): String {
        val secret = generateSalt(password)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val params = cipher.parameters
        val iv = params.getParameterSpec(IvParameterSpec::class.java).iv

        cipher.init(Cipher.DECRYPT_MODE, secret, IvParameterSpec(iv))
        val plaintext = String(cipher.doFinal(ciphertext))

        return plaintext
    }
    private fun generateSalt(password: String) : SecretKey{
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val salt =
            byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
        val tmp = factory.generateSecret(spec)
        val secret: SecretKey = SecretKeySpec(tmp.encoded, "AES")
        return secret
    }
}