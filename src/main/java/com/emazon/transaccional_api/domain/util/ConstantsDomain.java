package com.emazon.transaccional_api.domain.util;


public final class ConstantsDomain {
    private ConstantsDomain(){
        throw new IllegalStateException("Utility class");
    }
public static final String No_ARTICLE_FOUND_EXCEPTION="The item does not exist in inventory";
public static final String ERROR_WITH_THE_OTHER_MICRO="Internal error in inventory microservice";
public static final String ERROR_SERVICE_OFF="Service not available, please try again later";
    
}
