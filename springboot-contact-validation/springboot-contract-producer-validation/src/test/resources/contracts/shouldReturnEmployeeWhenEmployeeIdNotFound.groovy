import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a GET request with a Employee id=1 is made, the Employee object is returned")
    request {
        method 'GET'
        url '/employee/21'
    }
    response {
        status 404
        body("""
  {
  }
  """)
        headers {
            contentType(applicationJson())
        }
    }
}