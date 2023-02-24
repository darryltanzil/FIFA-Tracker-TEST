package com.darryltanzil.FIFASTAT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Player(String type, Value value) {

}