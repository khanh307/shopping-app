package com.example.shoppe.Listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var gridLayoutManager: GridLayoutManager):
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var visibleItemCount = gridLayoutManager.childCount
        var totalItemCount = gridLayoutManager.itemCount
        var firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

        if(isLoading() || isLastPage()){
            return;
        }
        if(firstVisibleItemPosition >= 0 && visibleItemCount + firstVisibleItemPosition >= totalItemCount){
            loadMoreItems()
        }
    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
}