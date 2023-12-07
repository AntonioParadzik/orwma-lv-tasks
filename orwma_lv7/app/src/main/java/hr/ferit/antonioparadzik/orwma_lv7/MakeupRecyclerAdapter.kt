package hr.ferit.antonioparadzik.orwma_lv7

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MakeupRecyclerAdapter(val items: ArrayList<Makeup>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MakeupViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MakeupViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MakeupViewHolder(val view:View):RecyclerView.ViewHolder(view){
        private val makeupImage=view.findViewById<ImageView>(R.id.makeupImage)
        private val makeupName=view.findViewById<TextView>(R.id.makeupName)
        private val makeupPrice=view.findViewById<TextView>(R.id.makeupPrice)
        private val makeupRating=view.findViewById<TextView>(R.id.makeupRating)
        private val makeupDescription=view.findViewById<TextView>(R.id.makeupDescription)

        @SuppressLint("SetTextI18n")
        fun bind(makeup:Makeup){
            Glide.with(view.context).load(makeup.image_link).into(makeupImage)
            makeupName.text=makeup.name
            makeupPrice.text=makeup.price.toString()
            makeupRating.text=makeup.rating.toString()
            makeupDescription.text=makeup.description
        }
    }

}