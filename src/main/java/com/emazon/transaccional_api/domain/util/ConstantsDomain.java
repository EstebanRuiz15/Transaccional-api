package com.emazon.transaccional_api.domain.util;


public final class ConstantsDomain {
    private ConstantsDomain(){
        throw new IllegalStateException("Utility class");
    }
public static final String No_ARTICLE_FOUND_EXCEPTION="The item does not exist in inventory";
public static final String ERROR_WITH_THE_OTHER_MICRO="Internal error in inventory microservice";
public static final String ERROR_SERVICE_OFF="Service not available, please try again later";
public static final String NOT_FOUND_ARTICLE="Article not found: ";
public static final String MICRO_NO_AVAILABLE="Microservice not available (503).";
public static final String NOT_AUTHORIZED_AT_THIS_SERVICE="Inauthorized at this microservice.";
public static final String ERROR_NOT_HANDLER="unknown error ";
public static final String STOCK_CLIENT="stockClient";
public static final String BUY_SUCESS="buy sucessfull";
}
