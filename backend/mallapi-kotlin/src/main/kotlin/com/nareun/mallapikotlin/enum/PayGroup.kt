package com.nareun.mallapikotlin.enum

enum class PayGroup(val title: String, val payList:List<PayType>) {
    CASH("현금", listOf(PayType.ACCOUNT_TRANSFER, PayType.REMITTANCE, PayType.ON_SITE_PAYMENT, PayType.TOSS)),
    CARD("카드", listOf(PayType.PAYCO, PayType.CARD, PayType.KAKAO_PAY, PayType.BAEMIN_PAY)),
    ETC("기타", listOf(PayType.POINT, PayType.COUPON)),
    EMPTY("없음", emptyList());

    companion object {
        fun findByPayType(payType: PayType): PayGroup {
            return entries.firstOrNull { it.hasPayCode(payType) } ?: EMPTY
        }
    }

    fun hasPayCode(payType: PayType): Boolean {

        return payList.contains(payType)
    }

}