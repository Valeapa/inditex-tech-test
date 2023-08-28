package com.valea.inditextechtest.application.price.service.util;

import java.time.LocalDateTime;

public class ExceptionMessageBuilder {


    public static String buildPriceNotFoundException(Integer productId, Integer brandId, LocalDateTime applicableDateTime) {
        return new StringBuilder("No result found for query params [productId=")
                .append(productId)
                .append(", brandId=").append(brandId)
                .append(", applicableDateTime=").append(applicableDateTime)
                .append("]")
                .toString();
    }
}
