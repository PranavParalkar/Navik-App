package com.example.navik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private static final int VIEW_TYPE_BOT = 1;
    private static final int VIEW_TYPE_USER = 2;
    
    private Context context;
    private List<ChatMessage> messages;
    
    public ChatAdapter(Context context, List<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
    }
    
    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isBot() ? VIEW_TYPE_BOT : VIEW_TYPE_USER;
    }
    
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BOT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_bot, parent, false);
            return new BotViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_user, parent, false);
            return new UserViewHolder(view);
        }
    }
    
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        
        if (holder instanceof BotViewHolder) {
            ((BotViewHolder) holder).botMessage.setText(message.getMessage());
        } else if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).userMessage.setText(message.getMessage());
        }
    }
    
    @Override
    public int getItemCount() {
        return messages.size();
    }
    
    static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView botMessage;
        
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMessage = itemView.findViewById(R.id.botMessage);
        }
    }
    
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userMessage;
        
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userMessage = itemView.findViewById(R.id.userMessage);
        }
    }
}
