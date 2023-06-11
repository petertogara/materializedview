package com.petmuc.materializedview.service;

import com.petmuc.materializedview.dto.PurchaseOrderSummaryDto;

import java.util.List;

public interface PurchaseOrderService {
    void placeOrder(int userIndex, int productIndex);
    List<PurchaseOrderSummaryDto> getSaleSummary();
}
