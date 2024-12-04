package dev.davveg.practica2


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView



class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Buscamos la toolbar y le asignamos el titulo
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar);
        supportActionBar?.title = "Home"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView = findViewById<NavigationView>(R.id.nav_view)
        //Le añadimos un listener y segun a lo que haga click vamos a realizar una accion
        //En este caso cambiaremos a otro fragment, cambiaremos el titulo y cerraremos el drawer
        navView.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, FragmentHome())
                        .commit()
                    supportActionBar?.title = "Home"
                }
                R.id.nav_gallery -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, GalleryFragment())
                        .commit()
                    supportActionBar?.title = "Gallery"
                }
                R.id.nav_slideshow -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, SlideshowFragment())
                        .commit()
                    supportActionBar?.title = "Slideshow"
                }
            }
            drawerLayout.close()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){ true }
        else { super.onOptionsItemSelected(item) }
    }


}