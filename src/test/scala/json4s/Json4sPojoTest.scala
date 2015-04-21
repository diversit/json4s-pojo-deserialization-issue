package json4s

import com.fasterxml.jackson.databind.ObjectMapper
import org.scalatest.{ShouldMatchers, WordSpec}
import pojo.{Person, ValueWrapper}

/**
 * Verify (de)serialization of Pojos using JSON4S.
 */
class Json4sPojoTest extends WordSpec with ShouldMatchers {

  val values = List(
    new ValueWrapper(10),
    new ValueWrapper("test"),
    new ValueWrapper(new Person("tester", 12))
  )

  val valuesJson = """[{"value":10},{"value":"test"},{"value":["pojo.Person",{"name":"tester","age":12}]}]"""

  import org.json4s._
  import org.json4s.jackson.JsonMethods._
  import org.json4s.jackson.Serialization._

  implicit val formats = DefaultFormats + new ValueWrapperSerializer

  class ValueWrapperSerializer extends CustomSerializer[ValueWrapper[_]](formats => (
    {
      case in => throw new RuntimeException("Never called during deserialization!!")
    },
    {
      case vw: ValueWrapper[_] =>
        val mapper = new ObjectMapper().enableDefaultTyping()

        val json: String = mapper.writeValueAsString(vw)
        val jValue = parse(json)
        jValue
    }))

  "json4s" should {

    "serialize Java POJOs to json" in {

      val json = write(values)
      println(s"json: $json")

      json should be(valuesJson)
    }

    "deserialize json into Java POJOs" in {

      val readValues = read[List[ValueWrapper[_]]](valuesJson)
      println(s"Values: $readValues")

      readValues should equal(values)
    }
  }
}
