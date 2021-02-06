package com.eniola.usermanagementapp.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eniola.usermanagementapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_users_list.view.*

class UserListAdapter(private val userClicked: UserClickedListener)
    : RecyclerView.Adapter<UserListAdapter.TransactionViewHolder>() {

    private var userList = mutableListOf<UserData>()

    fun setListItems(subjectData: List<UserData>){
        userList.clear()
        userList.addAll(subjectData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_users_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = userList[position]
        holder.userTitle.text = item.title.toUpperCase()
        holder.userEmail.text = item.email
        holder.userName.text = """${item.firstName} ${item.lastName}"""

        val iconImageUrl = item.picture

        //load image url into imageView using picasso
        Picasso.get().load(iconImageUrl).placeholder(
            R.drawable.image_circle_backgroud).fit().into(holder.userImage)

        holder.userItem.setOnClickListener {
            userClicked.onUserClicked(it, item)
        }

    }

    class TransactionViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val userTitle: TextView = view.user_title
        val userName = view.user_name
        val userEmail = view.user_email
        val userImage = view.user_image
        val userItem = view.userItem
    }

    interface UserClickedListener {
        fun onUserClicked(view: View, item: UserData)
    }

}