package com.oauth2.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Response<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> listErros = new ArrayList<>(0);

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public Response() {
    }

    public Response(T data, List<String> listErros, String message) {
        this.data = data;
        this.listErros = listErros;
        this.message = message;
    }

    public static <T> Response<T> create(T data) {
        return new Response(data, null, null);
    }

    public static <T> Response<T> erro(String ... erro) {
        return new Response(null, Arrays.asList(erro), null);
    }

    public static <T> Response<T> erro(List erro) {
        return new Response(null, erro, null);
    }

    public static <T> Response<T> message(final String message) {
        return new Response(null, null, message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getListErros() {
        return listErros;
    }

    public void setListErros(List<String> listErros) {
        this.listErros = listErros;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
