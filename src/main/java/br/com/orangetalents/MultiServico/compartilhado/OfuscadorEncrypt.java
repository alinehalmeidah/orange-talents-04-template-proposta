package br.com.orangetalents.MultiServico.compartilhado;

import org.springframework.security.crypto.encrypt.Encryptors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OfuscadorEncrypt implements AttributeConverter<String, String> {
    @SuppressWarnings("deprecation")
    @Override
    public String convertToDatabaseColumn(String documento) {
        try {
            return Encryptors.queryableText("${proposta.ofuscar.dados}", "senha").encrypt(documento);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public String convertToEntityAttribute(String documento) {
        try {
            return Encryptors.queryableText("${proposta.ofuscar.dados}", "senha").decrypt(documento);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}