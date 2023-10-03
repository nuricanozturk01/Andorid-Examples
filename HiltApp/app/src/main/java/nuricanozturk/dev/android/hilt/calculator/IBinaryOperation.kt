package nuricanozturk.dev.android.hilt.calculator

interface IBinaryOperation<T>
{
    fun applyAsInt(a: T, b: T) : T
    fun isValid(op: Char) : Boolean
}