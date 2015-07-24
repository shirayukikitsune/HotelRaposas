package br.com.hoteldasraposas.util.json;

import java.io.IOException;

import br.com.hoteldasraposas.util.Conversors;
import br.com.hoteldasraposas.util.Validators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CpfDeserializer extends JsonDeserializer<Long> {

	@Override
	public Long deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String cpfString = parser.getValueAsString();
		Long cpf;
		
		try {
			cpf = Conversors.convertStringToCpf(cpfString);
			
			if (!Validators.validateCpf(cpf))
				cpf = 0L;
		}
		catch (NumberFormatException e) {
			cpf = 0L;
		}
		
		return cpf;
	}

}
