package nuricanozturk.dev.android.hilt.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import nuricanozturk.dev.android.hilt.calculator.IBinaryOperation
import nuricanozturk.dev.android.hilt.calculator.IntMultiplyOperation
import nuricanozturk.dev.android.hilt.calculator.di.module.annotation.IntMultiplyOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule
{
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindIntMultiplyOperation(intMultiplyOperation : IntMultiplyOperation) : IBinaryOperation<Int>
}
