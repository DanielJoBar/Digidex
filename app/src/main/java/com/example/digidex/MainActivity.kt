package com.example.digidex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.digidex.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflar la vista
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navButton: BottomNavigationView = binding.bottomNav
        // LEm indicamos el fragmentoo que cargará main y le pasamos el controladoor de este
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_digimon_List_Main_Area) as NavHostFragment
        navController = navHostFragment.navController
        //La barra necesita una configuración para que salga el menu de navegaciion
        //Para ello hacemos lo siguiente
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.digimonListFragment,
                R.id.digimonCreateLocalDataListFragment
            )
        )
        //Le pasamos la vista del menu y cargamos el controlador de la navegación
        navButton.setupWithNavController(navController)

    }
}