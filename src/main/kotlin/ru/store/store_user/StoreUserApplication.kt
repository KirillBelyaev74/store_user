package ru.store.store_user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StoreUserApplication

fun main(args: Array<String>) {
	runApplication<StoreUserApplication>(*args)
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
fun String.lowercaseWords(): String = split(" ").joinToString(" ") { it.lowercase() }