package com.ovidiucristurean.badge_effect.math

interface Mapper<T:Number> {
    fun map(value: T, x: T, y: T, a: T, b: T): T
}