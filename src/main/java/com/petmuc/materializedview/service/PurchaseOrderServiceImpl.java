package com.petmuc.materializedview.service;

import com.petmuc.materializedview.dto.PurchaseOrderSummaryDto;
import com.petmuc.materializedview.entity.Product;
import com.petmuc.materializedview.entity.PurchaseOrder;
import com.petmuc.materializedview.entity.User;
import com.petmuc.materializedview.repository.ProductRepository;
import com.petmuc.materializedview.repository.PurchaseOrderRepository;
import com.petmuc.materializedview.repository.PurchaseOrderSummaryRepository;
import com.petmuc.materializedview.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private List<User> users;
    private List<Product> products;
    @Autowired
    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @PostConstruct
    private void init(){
        this.users = this.userRepository.findAll();
        this.products = this.productRepository.findAll();
    }
    @Override
    public void placeOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex).getId());
        purchaseOrder.setUserId(this.users.get(userIndex).getId());
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrderSummaryDto> getSaleSummary() {
        return this.purchaseOrderSummaryRepository.findAll()
                .stream()
                .map(pos -> {
                    PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
                    dto.setState(pos.getState());
                    dto.setTotalSale(pos.getTotalSale());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
