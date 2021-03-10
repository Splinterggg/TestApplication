package cvdevelopers.takehome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.transaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.ActivityMainBinding
import cvdevelopers.takehome.ui.ClientsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val clientsFragment = ClientsFragment.newInstance()
        supportFragmentManager.commit {
            replace(R.id.main_container, clientsFragment)
        }
    }
}
