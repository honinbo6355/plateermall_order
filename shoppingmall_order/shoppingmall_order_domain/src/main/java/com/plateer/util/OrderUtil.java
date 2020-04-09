package com.plateer.util;

import com.plateer.domain.orderstate.OrderType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {

    /*
    소문자의 문자열을 받아 해당하는 대문자 값을 가진 OrderType enum을 반환한다.
     */
    public static OrderType getOrderTypeByString(String lowerCaseOrderType) {

        OrderType orderType = OrderType.valueOf(lowerCaseOrderType.toUpperCase());

        return orderType;
    }

    /*
    url 패턴의 문자열을 enum 패턴으로 변환한다. ex) order-complete -> ORDER_COMPLETE
     */
    public static String parsingStatusUrlPatternToEnumPattern(String urlPattern) {

        return urlPattern.toUpperCase().replaceAll("-", "_");
    }

    public static String getToday() {

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(today);
    }
}
