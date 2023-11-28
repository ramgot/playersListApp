package ru.turing.playerslistapp

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.turing.playerslistapp.databinding.PlayerItemBinding

class PlayersAdapter(private val actionListener: PlayerActionListener) :
    RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>(), View.OnClickListener {

    var players: List<Player> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlayerItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.moreImageButton.setOnClickListener(this)
        return PlayersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = players[position]
        with(holder.binding) {
            holder.itemView.tag = player
            moreImageButton.tag = player
            playerNameText.text = player.name
            playerClubText.text = player.club
            Glide.with(photoPlayerImage)
                .load(player.photoUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_player_placeholder)
                .error(R.drawable.ic_player_placeholder)
                .into(photoPlayerImage)
            Glide.with(clubImage)
                .load(player.clubUrl)
                .placeholder(R.drawable.ic_player_placeholder)
                .error(R.drawable.ic_player_placeholder)
                .into(clubImage)
        }
    }

    class PlayersViewHolder(val binding: PlayerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val player = v.tag as Player
        when (v.id) {
            R.id.more_image_button -> {
                showPopupMenu(v)
            }

            else ->
                actionListener.onPlayerDetails(player)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val player = view.tag as Player
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, "Удалить")
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_REMOVE -> {
                    actionListener.onPlayerDelete(player)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object {
        private const val ID_REMOVE = 1
    }
}

interface PlayerActionListener {
    fun onPlayerDelete(player: Player)
    fun onPlayerDetails(player: Player)
}