package nuricanozturk.dev.android.hilt.calculator

import javax.inject.Inject

class IntMultiplyOperation @Inject constructor() : IBinaryOperation<Int> {
    override fun applyAsInt(a: Int, b: Int): Int = a * b

    override fun isValid(op: Char): Boolean = op == '*'
}