package com.example.my1app_melani_pilliza.di

import com.example.my1app_melani_pilliza.data.source.remote.ProductFirestoreRepository
import com.example.my1app_melani_pilliza.data.source.remote.UserRepository
import com.example.my1app_melani_pilliza.domain.usecase.products.AddProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.DeleteProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.GetProductUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.ListProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.products.UpdateProductsUseCase
import com.example.my1app_melani_pilliza.domain.usecase.users.DeleteUserUseCase
import com.example.my1app_melani_pilliza.domain.usecase.users.GetUsersUseCase
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.AddProductViewModel
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.ProductsScreenViewModel
import com.example.my1app_melani_pilliza.presentation.viewmodel.products.UpdateProductViewModel
import com.example.my1app_melani_pilliza.presentation.viewmodel.users.UsersScreenViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // FIRESTORE
    single { FirebaseFirestore.getInstance() }

    // REPOSITIRIES
    // Singleton del respositorio de usuarios, se le inyecta el FirebaseFirestore creado en la sección anterior
    single { UserRepository(get()) }

    single { ProductFirestoreRepository(get()) }

    // USE CASES
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { GetUsersUseCase(get()) }
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { DeleteUserUseCase(get()) }

    factory { AddProductsUseCase(get()) }
    factory { UpdateProductsUseCase(get()) }

    factory { ListProductsUseCase(get()) }
    factory { GetProductUseCase(get()) }
    factory { DeleteProductsUseCase(get()) }

    // VIEW MODELS
    // Crea el viewModel con las dependencias que tenga definidas
    viewModel { UsersScreenViewModel(get(), get()) }
    viewModel { UpdateProductViewModel(get(), get()) }
    viewModel { ProductsScreenViewModel(get(), get()) }
    viewModel { AddProductViewModel(get()) }
}