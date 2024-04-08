package com.nareun.mallapikotlin.enum

enum class CalculatorType(private val expression:(Long) -> Long) {
    CALC_A({value -> value}),
    CALC_B({ value -> value * 10 }),
    CALC_C({ value -> value * 3 }),
    CALC_ETC({0L });

    fun calculate(value : Long) :Long = expression(value)
}