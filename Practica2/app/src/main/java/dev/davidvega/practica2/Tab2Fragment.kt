package dev.davidvega.practica2

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.davidvega.practica2.databinding.FragmentTab2Binding
import dev.davveg.practica2.model.GalleryCard


class Tab2Fragment: Fragment() {

    private lateinit var binding: FragmentTab2Binding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: GalleryAdapter
    private var selectedItem: GalleryCard? = null
    private lateinit var toolbar: Toolbar
    private var isInActionMode = false
    private lateinit var defaultNavigationIcon: Drawable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTab2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = requireActivity().findViewById(R.id.toolbar)

        val cardList = listOf(
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

        mRecyclerView = binding.recyclerViewGallery
        mRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        registerForContextMenu(mRecyclerView)

        mAdapter = GalleryAdapter(cardList, requireContext()) { item ->
            handleItemClick(item)
        }

        mRecyclerView.adapter = mAdapter

        defaultNavigationIcon = toolbar.navigationIcon!!

    }

    private fun handleItemClick(item: GalleryCard) {
        if ( toolbar.menu.size() == 0 ) {
            startContextualToolbar(item)
        }
        if (isInActionMode) {
            // Si estamos en modo action y seleccionamos un item diferente se reemplaza
            if (selectedItem != item) {
                selectedItem = item
                toolbar.title = selectedItem?.name
            }
        } else {
            //startContextualToolbar(item)
        }
    }


    private fun startContextualToolbar(item: GalleryCard) {
        isInActionMode = true
        selectedItem = item
        toolbar.title = item.name
        toolbar.inflateMenu(R.menu.menu_action_menu)

        toolbar.setOnMenuItemClickListener { menuItem ->
            var handled = when (menuItem.itemId) {
                R.id.actionMenuDelete -> {
                    Toast.makeText(context, "Eliminar ${selectedItem?.name}", Toast.LENGTH_LONG)
                        .show()
                    clearSelection()
                    true
                }

                R.id.actionMenuEdit -> {
                    Toast.makeText(context, "Editar ${selectedItem?.name}", Toast.LENGTH_LONG)
                        .show()
                    clearSelection()
                    true
                }

                R.id.actionMenuShare -> {
                    Toast.makeText(
                        context,
                        "Compartir ${selectedItem?.name}",
                        Toast.LENGTH_LONG
                    ).show()
                    clearSelection()
                    true
                }

                else -> false
            }
            handled
        }

    }

    private fun clearSelection() {
        isInActionMode = false
        selectedItem = null

        // Reinicia el Toolbar
        toolbar.menu.clear()
        toolbar.navigationIcon = defaultNavigationIcon
        toolbar.title = "Practica3"
    }

    // Menu contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val toast: Toast = Toast.makeText(
            context,
            mAdapter.galleryCardList[mAdapter.getPosition()].name, Toast.LENGTH_SHORT
        )
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
}