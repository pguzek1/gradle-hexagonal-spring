package pl.alfn.project.runnable.micronaut.configuration

import jakarta.inject.Singleton

@Singleton
class MathService {
    fun compute(num: Int): Int = num * 4
}