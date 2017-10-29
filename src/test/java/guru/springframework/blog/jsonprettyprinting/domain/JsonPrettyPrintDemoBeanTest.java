package guru.springframework.blog.jsonprettyprinting.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.blog.jsonprettyprinting.domain.JsonPrettyPrintDemoBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JsonPrettyPrintDemoBeanTest {
    private ObjectMapper objectMapper;
    @Before
    public void setUp() throws Exception{
        objectMapper = new ObjectMapper();
    }
    @After
    public void tearDown() throws Exception{
        objectMapper = null;
    }
    @Test
    public void testJsonPrettyPrintObject() throws JsonProcessingException {
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(new JsonPrettyPrintDemoBean());
        System.out.println(jsonString);
        assertThat(jsonString, containsString("person-id"));
        assertThat(jsonString, containsString("person-name"));
    }
    @Test
    public void testJsonPrettyPrintString() throws IOException {
        String jsonString = "{\"person-id\": 231, \"person-name\": \"Mary Parker\"}";
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(jsonString));
        JsonPrettyPrintDemoBean bean = objectMapper
                .readValue(jsonString, JsonPrettyPrintDemoBean.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(bean));
        System.out.println(bean);
        assertThat(bean.personName, is(equalTo("Mary Parker")));
        assertThat(bean.personId, is(equalTo(231L)));
    }
}
