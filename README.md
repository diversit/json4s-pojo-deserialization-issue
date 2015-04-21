# json4s-pojo-deserialization-issue
A test project to reproduce Pojo deserialisation issue with Json4s

## Issue
Issue 228 on Json4s: [https://github.com/json4s/json4s/issues/228]()

### Description
Json4 has no good support for (de)serialising Java Pojos even though json4s-jackson is used.
When serialising to json, this limitation can be overcome by implementing a CustomSerializer.

However, when deserialising from json to a Pojo a 'Can't find ScalaSig for class ...' exception is thrown even before the CustomSerializer is used.

The problem lies in the Reflector when determining the 'ctorParamType'. It is always using the ScalaSigReader to read the constructor, but why is even used when a CustomSerializer is provided? Should it not delegate the deserialisation to the CustomSerializer earlier?

### Reproduce

Run the Json4sPojoTest to reproduce the Json4s issue.

JsonTest verifies the Pojo can be (de)serialized using Jackson.
