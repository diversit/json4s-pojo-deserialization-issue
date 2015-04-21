import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.Person;
import pojo.ValueWrapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test (de)serializing to/from json
 */
public class JsonTest {

    private final ValueWrapper testWrapper = new ValueWrapper(new Person("tester", 23));
    private final String json = "{\"value\":[\"pojo.Person\",{\"name\":\"tester\",\"age\":23}]}";

    private static ObjectMapper mapper;

    @BeforeClass
    public static void setupWrapper() {
        mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
    }

    @Test
    public void serializeToJson() throws Exception {

        String valueJson = mapper.writeValueAsString(testWrapper);
        assertThat(valueJson, is(json));
    }

    @Test
    public void deserializeFromJson() throws Exception {

        ValueWrapper wrapper = mapper.readValue(json, ValueWrapper.class);
        assertThat(wrapper, is(equalTo(testWrapper)));
    }
}
