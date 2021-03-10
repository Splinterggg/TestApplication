package cvdevelopers.takehome.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import cvdevelopers.githubstalker.databinding.ItemClientBinding
import cvdevelopers.takehome.models.Client

class ClientsAdapter() : RecyclerView.Adapter<CharacterViewHolder>() {


    private val items = ArrayList<ClientView>()

    fun setItems(items: ArrayList<ClientView>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemClientBinding =
            ItemClientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])
}

class CharacterViewHolder(private val itemBinding: ItemClientBinding) :
    RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ClientView) {
        itemBinding.firstName.text = item.firstName
        itemBinding.lastName.text = item.lastName
        Glide.with(itemBinding.root)
            .load(item.picture)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {

    }
}

