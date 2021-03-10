package cvdevelopers.takehome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
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
