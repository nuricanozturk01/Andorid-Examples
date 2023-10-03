package nuricanozturk.dev.android.hilt.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import nuricanozturk.dev.android.hilt.calculator.IBinaryOperation
import nuricanozturk.dev.android.hilt.calculator.IntAddOperation
import nuricanozturk.dev.android.hilt.calculator.di.module.annotation.IntAddOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule
{
    @Binds
    @IntAddOperationInterceptor
    abstract fun bindIntAddOperation(intAddOperation : IntAddOperation) : IBinaryOperation<Int>
}
