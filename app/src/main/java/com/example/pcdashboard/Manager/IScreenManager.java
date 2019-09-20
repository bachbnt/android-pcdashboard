package com.example.pcdashboard.Manager;

import androidx.fragment.app.Fragment;

public interface IScreenManager {
    void openLoginScreen(int screenId);
    Fragment openDashboardScreen(int screenId);
}
