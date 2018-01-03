package com.panjin.controller;

import com.panjin.api.AccountApi;
import com.panjin.dto.reqeust.CustomerReqDTO;
import com.panjin.dto.response.CustomerRespDTO;
import com.panjin.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;
import sun.font.TrueTypeFont;

/**
 * @author panjin.
 * @date 2018/1/3.
 */
@Api(value = "会员服务")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    AccountApi accountApi;

    @ApiOperation(value = "开户")
    @ApiImplicitParam(name = "customerReqDTO", value = "会员请求实体对象", required = true, dataType = "CustomerReqDTO")
    @PostMapping(value = "/openCustomer")
    public CustomerRespDTO openCustomer(@RequestBody CustomerReqDTO customerReqDTO) {
        CustomerRespDTO respDTO = new CustomerRespDTO();
        respDTO.setCustomerName(customerReqDTO.getThirdUserName());
        respDTO.setCustomerId(customerService.openCustomer(customerReqDTO));
        return respDTO;
    }

    @GetMapping(value = "/getAccount")
    @ApiOperation(value = "获取账户信息")
    @ApiImplicitParam(name = "accountNo", value = "账号", required = true, paramType = "query", dataType = "String")
    public String getAccount(@RequestParam(value = "accountNo", required = true) String accountNo) {
        String account = accountApi.getAccount(accountNo);
        return "hello, customer invoke account " + account;
    }
}
