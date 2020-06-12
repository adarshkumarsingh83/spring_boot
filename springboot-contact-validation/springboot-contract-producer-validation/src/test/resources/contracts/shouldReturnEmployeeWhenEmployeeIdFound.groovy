import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When a GET request with a Employee id=1 is made, the Employee object is returned")
    request {
        method 'GET'
        url '/employee/1'
    }
    response {
        status 200
        body("""
  {
    "id": "1",
    "fname": "Adarsh",
    "lname": "Kumar",
    "salary": "1000.0",
    "gender": "M"
  }
  """)
        headers {
            contentType(applicationJson())
        }
    }
}