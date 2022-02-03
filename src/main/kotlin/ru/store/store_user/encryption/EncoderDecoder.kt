package ru.store.store_user.encryption

import org.springframework.stereotype.Component
import java.util.*

@Component
class EncoderDecoder {

    fun encoder(value: String): String {
        val encoder: Base64.Encoder = Base64.getEncoder()
        return encoder.encodeToString(value.toByteArray())
    }

    fun decoder(value: String): String {
        val decoder: Base64.Decoder = Base64.getDecoder()
        return String(decoder.decode(value))
    }
}