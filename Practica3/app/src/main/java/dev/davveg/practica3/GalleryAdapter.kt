package dev.davveg.practica3


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.davveg.practica3.model.GalleryCard



class GalleryAdapter(
    val galleryCardList: List<GalleryCard>,
    private val context: Context,
    private val itemClickListener: (GalleryCard) -> Unit  // Para implementar el click
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_gallery_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = galleryCardList[position]
        holder.bind(item, context)

        holder.itemView.setOnClickListener {
            itemClickListener(item)
        }

        holder.itemView.setOnLongClickListener {
            setPosition(holder.layoutPosition)
            false
        }
    }

    fun getPosition(): Int {
        return position
    }
    fun setPosition(position: Int) {
        this.position = position
    }

    override fun getItemCount(): Int = galleryCardList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardImage: ImageView = view.findViewById(R.id.imageGalleryItem)
        private val cardText: TextView = view.findViewById(R.id.textGalleryItem)

        fun bind(card: GalleryCard, context: Context) {
            cardImage.setImageResource(card.image)
            cardText.text = card.name
        }
    }


}

