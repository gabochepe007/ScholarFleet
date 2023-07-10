package com.example.scholarfleet

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dbHelper: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        setupFabButtons()



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.agendaFragment, R.id.profesorFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Aqui se esta creando el objeto del paquete database
        dbHelper = Database(this)

        // Crear o actualizar la base de datos
        dbHelper.writableDatabase


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.home, menu)


        return true


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

    override fun onClick(view: View?) {
        val tarjetauna = FormularioProFragment()
        val tarjetados = FormularioMatFragment()
        when (view?.id) {
            R.id.fab_menu_actions -> {
                expandOrCollapseFAB()
            }
            R.id.fab_menu_add_alarm -> {
                expandOrCollapseFAB()
                tarjetados.show(supportFragmentManager, "FormularioMatFragment")

            }
            R.id.fab_menu_add_person -> {
                expandOrCollapseFAB()
                tarjetauna.show(supportFragmentManager, "FormularioProFragment")

            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun expandOrCollapseFAB() {
        if (binding.appBarHome.fabMenuActions.isExtended) {
            binding.appBarHome.fabMenuActions.shrink()
            binding.appBarHome.fabMenuAddAlarm.hide()
            binding.appBarHome.fabMenuAddAlarmText.visibility = View.GONE
            binding.appBarHome.fabMenuAddPerson.hide()
            binding.appBarHome.fabMenuAddPersonText.visibility = View.GONE
        } else {
            binding.appBarHome.fabMenuActions.extend()
            binding.appBarHome.fabMenuAddAlarm.show()
            binding.appBarHome.fabMenuAddAlarmText.visibility = View.VISIBLE
            binding.appBarHome.fabMenuAddPerson.show()
            binding.appBarHome.fabMenuAddPersonText.visibility = View.VISIBLE
        }
    }

    private fun setupFabButtons() {
        binding.appBarHome.fabMenuActions.shrink()
        binding.appBarHome.fabMenuActions.setOnClickListener(this)
        binding.appBarHome.fabMenuAddAlarm.setOnClickListener(this)
        binding.appBarHome.fabMenuAddPerson.setOnClickListener(this)
    }
}