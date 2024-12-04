package dev.davveg.practica2

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.davveg.practica2.model.GalleryCard


class GalleryFragment : Fragment() {
    var mCurrentItemPosition = 0
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : GalleryAdapter = GalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    }

}