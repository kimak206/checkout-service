package contracts.user

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Get Not Found"
    request {
        url "/checkout/get/1"
        method HttpMethod.GET.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
    }
    response {
        status NOT_FOUND()
        body("Not Found!")
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}

