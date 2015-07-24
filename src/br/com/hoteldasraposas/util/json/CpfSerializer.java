package br.com.hoteldasraposas.util.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CpfSerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String cpf = String.format("%1$011d", value);
		
		cpf = String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9, 11));
		
		gen.writeString(cpf);
	}

}
