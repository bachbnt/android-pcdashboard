package com.example.pcdashboard.View;

import com.example.pcdashboard.Model.ChatMessage;

import java.util.ArrayList;

public interface IChatView {
    void onSuccess(ArrayList<ChatMessage> chatMessages);
    void onFailure();
}
