package dev.davveg.practica3

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.davveg.practica3.databinding.FragmentGalleryBinding
import dev.davveg.practica3.model.GalleryCard


class GalleryFragment : Fragment(), ActionMode.Callback  {
    lateinit var binding: FragmentGalleryBinding
    private var actionMode: ActionMode? = null
    private lateinit var tracker: SelectionTracker

    var mCurrentItemPosition = 0
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : GalleryAdapter = GalleryAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewGallery = getView()?.findViewById<RecyclerView>(R.id.recyclerViewGallery)

        var cardList = listOf(
            GalleryCard("Card 1", R.drawable.image1),
            GalleryCard("Card 2", R.drawable.image2),
            GalleryCard("Card 3", R.drawable.image3),
            GalleryCard("Card 4", R.drawable.image4),
            GalleryCard("Card 5", R.drawable.image5),
            GalleryCard("Card 6", R.drawable.image6),
            GalleryCard("Card 7", R.drawable.image7),
            GalleryCard("Card 8", R.drawable.image8),
            GalleryCard("Card 9", R.drawable.image9),
        )


        mRecyclerView = view?.findViewById(R.id.recyclerViewGallery) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL )
        getView()?.let { mAdapter.RecyclerAdapter(cardList, it.context) }
        mRecyclerView.adapter = mAdapter
        registerForContextMenu(mRecyclerView)

        //tracker
        var tracker = SelectionTracker.Builder(
            "my-selection-id",
            binding.recyclerViewGallery,
            StableIdKeyProvider(binding.recyclerViewGallery),
            ItemDetailsLookup.ItemDetails<RecyclerView>(binding.recyclerViewGallery),
            StorageStrategy.createLongStorage())
            .withOnItemActivatedListener(myItemActivatedListener)
            .build()



    }

    // Menu contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val toast:Toast  = Toast.makeText(context,
            mAdapter.superheros[mAdapter.getPosition()].name, Toast.LENGTH_SHORT)
        toast.show()

        return super.onContextItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = activity?.menuInflater
        inflater?.inflate(R.menu.menu_context, menu)
    }

    // Menu action

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.menu_action_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = true

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.context_share2 -> {
                //write your own logic here for the Item you want to do with it.
                val selected = mAdapter.superheros.filterNot {
                    tracker.selection.contains(it.title)
                }.toMutableList()
                mAdapter.superheros = selected
                mAdapter.notifyDataSetChanged()
                actionMode?.finish()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        tracker.clearSelection()
        actionMode = null
    }
}