package nuricanozturk.dev.android.payment.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PaymentApplication : Application()
{

}

/*
mvn install:install-file -DgroupId=nuricanozturk.dev -DartifactId=Unit-Test-Framework -Dversion=1.0.0 -Dfile=/Users/nuricanozturk/Desktop/Unit-Test-Framework-1.0.0.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true
mvn install:install-file -DgroupId=nuricanozturk.dev -DartifactId=Unit-Test-Framework -Dversion=1.0.0 -Dfile=/Users/nuricanozturk/Desktop/Unit-Test-Framework-1.0.0.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true

 */