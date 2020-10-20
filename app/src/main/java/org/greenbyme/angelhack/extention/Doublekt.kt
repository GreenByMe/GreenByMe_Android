package org.greenbyme.angelhack.extention

import java.math.BigDecimal

fun Double.roundTo2Decimal() = BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()