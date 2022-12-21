package contracts.user

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Post and insert checkout order"
    request {
        url "/checkout"
        method HttpMethod.POST.toString()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            accept(MediaType.APPLICATION_JSON_VALUE)
        }
        body("""
          {
                "orderId" : "5000",
                "userId" : "1",
                "status" : "true",
                "orderDate" : "2020-09-01"
           }
       """)
    }
    response {
        status CREATED()
        body("""
         {
            "checkoutId": 1,
            "orderId": 5000,
            "userId": 1,
            "status": true,
            "orderDate": "2020-09-01T00:00:00.000+00:00"
         }   
       """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}

