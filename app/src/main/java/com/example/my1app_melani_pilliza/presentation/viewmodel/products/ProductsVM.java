package com.example.my1app_melani_pilliza.presentation.viewmodel.products;

import androidx.lifecycle.ViewModel;

import com.example.my1app_melani_pilliza.domain.usecase.products.ListProductsUseCase;

public class ProductsVM extends ViewModel {
    private final ListProductsUseCase listProductsUseCase;

    public ProductsVM(ListProductsUseCase listProductsUseCase) {
        this.listProductsUseCase = listProductsUseCase;
    }
}
