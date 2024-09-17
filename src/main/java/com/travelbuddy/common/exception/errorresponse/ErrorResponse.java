package com.travelbuddy.common.exception.errorresponse;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ErrorResponse {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ObjectNode attributes;

        public Builder() {
            this.attributes = new ObjectNode(JsonNodeFactory.instance);
        }

        public Builder withError(Object error) {
            attributes.put("error", error.toString());
            return this;
        }

        public Builder withMessage(Object message) {
            attributes.put("message", message.toString());
            return this;
        }

        public Builder withField(String key, Object value) {
            attributes.put(key, value.toString());
            return this;
        }

        public ObjectNode build() {
            return attributes;
        }
    }
}
