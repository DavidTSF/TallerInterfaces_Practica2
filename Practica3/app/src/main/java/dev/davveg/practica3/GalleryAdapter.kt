package dev.davveg.practica3


import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.RecyclerView
import dev.davveg.practica3.model.GalleryCard


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var position = 0

    var superheros: List<GalleryCard>  = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(superheros : List<GalleryCard>, context: Context){
        this.superheros = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)

        holder.itemView.setOnLongClickListener {
            setPosition(holder.layoutPosition)
            false
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_gallery_list, parent, false))
    }

    override fun getItemCount(): Int {
        return superheros.size
    }
    fun getPosition(): Int {
        return position
    }
    fun setPosition(position: Int) {
        this.position = position
    }





    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val cardImage = view.findViewById(R.id.imageGalleryItem) as ImageView
        val cardText = view.findViewById(R.id.textGalleryItem) as TextView


        fun bind(card: GalleryCard, context: Context){
            cardImage.setImageResource(card.image)
            cardText.setText(card.name)

        }


        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            v?.setOnCreateContextMenuListener(this);
        }
    }

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created. startActionMode() is called.
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items.
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.menu_action_menu, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after
        // onCreateActionMode, and might be called multiple times if the mode
        // is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item.
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.context_edit2 -> {
                    shareCurrentItem()
                    mode.finish() // Action picked, so close the CAB.
                    true
                }
                else -> false
            }
        }

        // Called when the user exits the action mode.
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }

}
