package com.cuwallet.commons.json;

import java.io.IOException;

import com.cuwallet.commons.service.Protocol;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class ProtocolEnumTypeAdapterFactory implements TypeAdapterFactory {
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		Class<T> rawType = (Class<T>) type.getRawType();
		if (rawType != Protocol.class) {
			return null;
		}

		return new TypeAdapter<T>() {
			public void write(JsonWriter out, T value) throws IOException {
				if (value == null) {
					out.nullValue();
				} else {
					out.value(value.toString());
				}
			}

			@SuppressWarnings("unchecked")
			public T read(JsonReader reader) throws IOException {
				try {
					if (reader.peek() == JsonToken.NULL) {
						reader.nextNull();
						return null;
					} else {
						
						return (T) Protocol.valueOf(reader.nextString());
					}
				} catch (Exception e) {
					return null;
				}
			}
		};
	}
}