package com.example.iiitp.ui.faculty.utlities;

public class FacultyData {
    public String name;
    public String positiion;
    public String qualification;
    public String email;
    public String phoneNumber;
    public int imageId;

    public FacultyData(String name,String position,
                       String qualification,String email,
                       String phoneNumber,int imageId){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.qualification = qualification;
        this.positiion = position;
        this.imageId = imageId;
    }
}


