package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcdashboard.Adapter.ExamAdapter;
import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.R;

import java.util.ArrayList;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_ACCOUNT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private ImageButton ibBack;
    private RecyclerView recyclerView;
    private ExamAdapter examAdapter;


    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        examAdapter = new ExamAdapter(getContext(), list());
        recyclerView = view.findViewById(R.id.recycler_view_exam);
        recyclerView.setAdapter(examAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ibBack = view.findViewById(R.id.ib_back_exam);
        ibBack.setOnClickListener(this);
    }

    private ArrayList<Exam> list() {
        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(new Exam("PHY010101", "Cảm biến và đo lường", "16VLTH", "20/10/2019 15:30", "E205 NVC", 8.5));
        exams.add(new Exam("PHY010101", "Cảm biến và đo lường", "16VLTH", "20/10/2019 15:30", "E205 NVC", 8.5));

        exams.add(new Exam("PHY010101", "Cảm biến và đo lường", "16VLTH", "20/10/2019 15:30", "E205 NVC", 8.5));
        exams.add(new Exam("PHY010101", "Cảm biến và đo lường", "16VLTH", "20/10/2019 15:30", "E205 NVC", 8.5));

        exams.add(new Exam("PHY010101", "Cảm biến và đo lường", "16VLTH", "20/10/2019 15:30", "E205 NVC", 8.5));

        return exams;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_exam:
                SharedPreferencesUtils.saveTabId(getContext(), TAB_ACCOUNT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
        }
    }
}
