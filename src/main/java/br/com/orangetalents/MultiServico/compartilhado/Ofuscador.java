package br.com.orangetalents.MultiServico.compartilhado;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

@Converter
public class Ofuscador implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String documento) {
        return Base64.getEncoder().encodeToString(documento.getBytes());
    }

    @Override
    public String convertToEntityAttribute(String documento) {
        byte[] decode = Base64.getDecoder().decode(documento.getBytes());
        String campoDecodificada = new String(decode);
        return String.format("%10s", campoDecodificada.substring(campoDecodificada.length() - 4)).replace(' ', '*');
    }
}