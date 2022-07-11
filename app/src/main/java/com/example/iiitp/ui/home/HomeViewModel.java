package com.example.iiitp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
      //  String str="INRODUCTION";
        String str="Indian Institute of Information Technology, Pune (abbreviated IIITP), is one of the Indian Institutes of Information Technology, a group of institutes of Higher education in India focused on Information Technology. It is established by the Ministry of Education (MoE), formerly the Ministry of Human Resource Development, Government of India and few industry partners as Not-for-profit Public Private Partnership (N-PPP) Institution.[1] IIIT Pune was declared as an Institute of National Importance (INI) in August 2017.IIITP is located in Pune, Maharashtra, and it started its academic sessions from July 2016. It offers two courses in Bachelor of Technology (B.Tech), Computer Science and Engineering (CSE) and Electronics and Communication Engineering (ECE). From A.Y. 2019–20, the institute has started Master of Technology (M.Tech) and Doctor of Philosophy (Ph.D.) programmes. The institute offers M.Tech. programmes through its department of CSE with specialization in Artificial Intelligence (AI) and department of ECE with specialization in Internet of Things (IoT).\n" +
                "\n" +
                "M.Tech. programmes are two years structured programmes with credit components from one year of course work and one year of project/ thesis. The academic programme leading to the Ph.D. degree involves a course credit requirement and a research thesis submission. The Institute encourages research in interdisciplinary areas through a system of joint supervision and interdepartmental group activities.";
        mText.setValue(str);
    }

    public LiveData<String> getText() {
        return mText;
    }
}