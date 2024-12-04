package dev.davveg.practica1


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
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

        // Buscamos el header y dentro de el tenemos el spiner, al cual le añadiremos una lista de correps
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val spinner = navView.getHeaderView(0).findViewById<Spinner>(R.id.headerMenuEmails)
        val emailList = listOf("carladom@gmail.com", "alu123@ieselcaminas.org", "correocarla@gmail.es")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emailList)
        spinner.adapter = adapter

        //Le añadimos un listener y segun a lo que haga click vamos a realizar una accion
        //En este caso cambiaremos a otro fragment, cambiaremos el titulo y cerraremos el drawer
        navView.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_inbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, InboxFragment())
                        .commit()
                    supportActionBar?.title = "Inbox"
                }
                R.id.nav_trash -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, TrashFragment())
                        .commit()
                    supportActionBar?.title = "Trash"
                }
                R.id.nav_outbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, OutboxFragment())
                        .commit()
                    supportActionBar?.title = "Outbox"
                }
                R.id.nav_spam -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, SpamFragment())
                        .commit()
                    supportActionBar?.title = "Spam"
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