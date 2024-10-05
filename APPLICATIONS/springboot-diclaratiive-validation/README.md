

```
@AssertTrue validates that the annotated property value is true.
@Size validates that the annotated property value has a size between the attributes min and max. We can apply it to String, Collection, Map, and array properties.
@Min validates that the annotated property has a value no smaller than the value attribute.
@Max validates that the annotated property has a value no larger than the value attribute.
@Email validates that the annotated property is a valid email address.
Some annotations accept additional attributes, but the message attribute is common to all of them. This is the message that will usually be rendered when the value of the respective property fails validation.

Here are some additional annotations we can find in the JSR:

@NotEmpty validates that the property isn’t null or empty. We can apply it to String, Collection, Map or Array values.
@NotBlank can be applied only to text values, and validates that the property isn’t null or whitespace.
@Positive and @PositiveOrZero apply to numeric values, and validate that they’re strictly positive, or positive including 0.
@Negative and @NegativeOrZero apply to numeric values, and validate that they’re strictly negative, or negative including 0.
@Past and @PastOrPresent validate that a date value is in the past, or the past including the present. We can apply it to date types, including those added in Java 8.
@Future and @FutureOrPresent validate that a date value is in the future, or in the future including the present.
```

```
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "adarsh",
  "email": "adarsh@kumar",
  "mobile": "+18877665544",
  "age": 50,
  "working": true,
  "aboutMe": "intelligent",
  "dateOfBirth": 13091983,
  "preference": [
    "java",
    "iot"
  ]
}'
```