package com.example.appMelani.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import com.example.appMelani.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel:ViewModel() {
    private val _product= MutableStateFlow(
        Product(8,"",0.0,"")
    )
    val product:StateFlow<Product> =_product

    fun setId(id:Int){
        _product.value=_product.value.copy(id=id)
    }

    fun setName(name:String){
        _product.value=_product.value.copy(name=name)
    }

    fun setPrice(price:Double){
        _product.value=_product.value.copy(price=price)
    }

    fun setDescription(description:String){
        _product.value=_product.value.copy(description=description)
    }

    fun save(){
        //GUARDAR
    }
}