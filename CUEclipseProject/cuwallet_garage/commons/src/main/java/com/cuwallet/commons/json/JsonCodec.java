package com.cuwallet.commons.json;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.cuwallet.commons.util.CollectionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@Component
public class JsonCodec {

	private Gson gson;

	private Gson gsonForSerialization;

	private static JsonCodec JSON_CODEC;

	public <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public <T> T fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

	public <T> T fromJson(Reader reader, Type type) {
		return gson.fromJson(reader, type);
	}

	public String toJson(Object src) {
		return gsonForSerialization.toJson(src);
	}

	public void toJson(Object src, Writer writer) {
		gsonForSerialization.toJson(src, writer);
	}

	public void removeNotIncludedFields(JsonObject jsonObject, String elementsToInclude) {
		if (jsonObject != null) {
			Set<String> elements = CollectionUtil.toSet(elementsToInclude.split(","));
			Iterator<Entry<String, JsonElement>> itr = jsonObject.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				if (!elements.contains(entry.getKey())) {
					itr.remove();
				}
			}
		}
	}

	public JsonElement getJsonTree(Object src) {
		return gsonForSerialization.toJsonTree(src);
	}

	public static JsonCodec getInstance() {
		return JSON_CODEC;
	}

	@PostConstruct
	public void initialize() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		// gsonBuilder.registerTypeAdapterFactory(new EnumAdapterFactory());
		// gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new
		// EnumDeserializer<>());
		//gsonBuilder.registerTypeAdapter(IRichData.class, new IRichData.RichDataDeserializer());
		//gsonBuilder.registerTypeAdapter(IRichDataCumulate.class, new IRichDataCumulate.RichDataDeserializer());
		//gsonBuilder.registerTypeAdapter(ModerationSearchFi.class, new ModerationSearchField.ModerationSearchFieldDeserializer());
		gsonBuilder.registerTypeAdapterFactory(new ProtocolEnumTypeAdapterFactory());
		gson = gsonBuilder.create();
		GsonBuilder gsonBuilderForSerial = new GsonBuilder();
		gsonBuilderForSerial.registerTypeAdapterFactory(new ProtocolEnumTypeAdapterFactory());
		gsonForSerialization = gsonBuilderForSerial.create();
		JSON_CODEC = this;
	}

	
}
