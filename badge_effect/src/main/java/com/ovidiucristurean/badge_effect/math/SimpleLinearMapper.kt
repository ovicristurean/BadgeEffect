package com.ovidiucristurean.badge_effect.math

class SimpleLinearMapper : Mapper<Double> {

    override fun map(value: Double, x: Double, y: Double, a: Double, b: Double) =
        (value - x) * (b - a) / (y - x) + a
}