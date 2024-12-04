package dev.davveg.practica2


import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.davveg.practica2.model.GalleryCard


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var superheros: List<GalleryCard>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(superheros : List<GalleryCard>, context: Context){
        this.superheros = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_gallery_list, parent, false))
    }

    override fun getItemCount(): Int {
        return superheros.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardImage = view.findViewById(R.id.imageGalleryItem) as ImageView
        val cardText = view.findViewById(R.id.textGalleryItem) as TextView

        fun bind(card: GalleryCard, context: Context){
            cardImage.setImageResource(card.image)
            cardText.setText(card.name)

        }

    }
}
