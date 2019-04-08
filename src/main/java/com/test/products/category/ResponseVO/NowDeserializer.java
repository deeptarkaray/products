package com.test.products.category.ResponseVO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class NowDeserializer extends StdDeserializer<String> {

    public NowDeserializer() {
        this(null);
    }

    public NowDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String nowValue = "";
        if(node.get("from") != null && node.get("to") != null) {
            // nowValue = "from " + node.get("from").textValue() + " to " +  node.get("to").textValue();
            nowValue = node.get("to").textValue();
        }
        else {
            nowValue = node.asText();
        }

        return nowValue;
    }
}
