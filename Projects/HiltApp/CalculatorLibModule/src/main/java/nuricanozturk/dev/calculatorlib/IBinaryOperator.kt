package nuricanozturk.dev.calculatorlib

interface IBinaryOperator<T> {
    fun applyAsInt(a: T, b: T) : T
    fun isValid(op: Char) : Boolean
}