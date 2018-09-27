package kz.batana.lab3

import kz.batana.lab3.home.homeModule
import org.koin.dsl.module.Module

val appModules: List<Module>
    get() = listOf(
            homeModule
    )