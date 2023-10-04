package nuricanozturk.dev.calculatorlib.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import nuricanozturk.dev.calculatorlib.IBinaryOperator
import nuricanozturk.dev.calculatorlib.IntMultiplyOperation
import nuricanozturk.dev.calculatorlib.di.module.annotation.IntMultiplyOperationInterceptor


@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule {
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindIntMultiplyOperation(intMultiplyOperation: IntMultiplyOperation) : IBinaryOperator<Int>
}