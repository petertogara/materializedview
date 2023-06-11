package com.petmuc.materializedview.controller;

import com.petmuc.materializedview.dto.PurchaseOrderSummaryDto;
import com.petmuc.materializedview.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("purchase-order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/sale/{userIndex}/{prodIndex}")
    public void placeOrder(@PathVariable final int userIndex,
                           @PathVariable final int prodIndex){
        this.purchaseOrderService.placeOrder(userIndex, prodIndex);
    }

    @GetMapping("/summary")
    public List<PurchaseOrderSummaryDto> getSummary(){
        return this.purchaseOrderService.getSaleSummary();
    }
}
