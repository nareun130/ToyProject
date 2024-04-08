package com.nareun.mallapikotlin.enum

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PayGroupTest {

    private fun selectPayType() : PayType  = PayType.BAEMIN_PAY

    @Test
    fun directQuery(){
        val payType = selectPayType()
        val payGroup = PayGroup.findByPayType(payType)

        Assertions.assertEquals("CARD",payGroup.name)
        Assertions.assertEquals("카드",payGroup.title)

    }
}