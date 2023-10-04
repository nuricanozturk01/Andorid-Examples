package nuricanozturk.dev.calculatorlib

import javax.inject.Inject

class IntAddOperation @Inject constructor() : IBinaryOperator<Int> {
    override fun applyAsInt(a: Int, b: Int): Int = a + b

    override fun isValid(op: Char) = op == '+'
}