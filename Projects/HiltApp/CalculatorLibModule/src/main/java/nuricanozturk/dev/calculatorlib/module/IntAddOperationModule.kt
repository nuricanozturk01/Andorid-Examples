package nuricanozturk.dev.calculatorlib.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import nuricanozturk.dev.calculatorlib.IBinaryOperator
import nuricanozturk.dev.calculatorlib.IntAddOperation
import nuricanozturk.dev.calculatorlib.module.annotation.IntAddOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule {
    @Binds
    @IntAddOperationInterceptor
    abstract fun bindIntAddOperation(intAddOperation: IntAddOperation) : IBinaryOperator<Int>
}