package com.example.ischeduler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import org.w3c.dom.Text

class ScheduleAdapter(data: OrderedRealmCollection<ISchedule>):
    RealmRecyclerViewAdapter<ISchedule,ScheduleAdapter.ViewHolder>(data,true) {

    private var listener: ((Long?)-> Unit)? =null

    fun setOnItemClickListener(listener:(Long?)-> Unit){
        this.listener=listener
    }

    init {
        setHasStableIds(true)
    }
    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell){
        val title: TextView=cell.findViewById(android.R.id.text1)
        val time: TextView=cell.findViewById(android.R.id.text2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(android.R.layout.simple_list_item_2,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
       val schedule :ISchedule? =getItem(position)
        holder.title.text=schedule?.title
        holder.time.text=schedule?.time
        holder.itemView.setOnClickListener {
            listener?.invoke(schedule?.id)
        }

    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?:0
    }
}