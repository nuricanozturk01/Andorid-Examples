package nuricanozturk.dev.android.app.data.service.mapper

import nuricanozturk.dev.android.app.data.service.dto.PaymentSaveDTO
import nuricanozturk.dev.android.repositorylib.entity.Payment
import org.mapstruct.Mapper

@Mapper(implementationName = "PaymentMapperImpl")
interface IPaymentSaveMapper
{
    fun toPayment(paymentSaveDTO : PaymentSaveDTO) : Payment
}