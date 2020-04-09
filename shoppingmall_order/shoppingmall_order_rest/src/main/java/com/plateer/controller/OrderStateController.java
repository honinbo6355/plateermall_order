package com.plateer.controller;

import com.plateer.domain.OrderDto;
import com.plateer.service.OrderStateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "true", origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
        allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Set-Cookie", "Authorization"},
        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}, maxAge = 3000)
@RestController
@RequestMapping("/api/order/state")
public class OrderStateController {

    OrderStateService orderStateService;

    public OrderStateController(OrderStateService orderStateService) {

        this.orderStateService = orderStateService;
    }

    @GetMapping("/list/{state}/{userid}")
    public List<OrderDto> getOrderStateList(@PathVariable("state") String state, @PathVariable("userid") String userid){

        return orderStateService.findOrderListFromUserid(userid, state);
    }


    @GetMapping("/{original}/{changed}/{orderid}")
    public boolean changeOrderState(@PathVariable("orderid") String orderid, @PathVariable("original") String original, @PathVariable String changed) {

        orderStateService.changeOrderState(orderid, original, changed);
        return true;
    }

    @GetMapping("/count/{state}/{userid}")
    public Map<String, Integer> getStateCountMap(@PathVariable String state, @PathVariable String userid){

        return orderStateService.getOrderStateCount(userid, state);
    }

    @GetMapping("/specificstatelist/{state}/{specific}/{userid}")
    public List<OrderDto> getSpecificStateList(@PathVariable String state, @PathVariable String specific, @PathVariable String userid){

        return orderStateService.getSpecificStatusOrderList(state, specific, userid);
    }

}
