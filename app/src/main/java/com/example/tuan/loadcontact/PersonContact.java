package com.example.tuan.loadcontact;

public class PersonContact {
    private String mName;
    private String mPhone;

    public PersonContact(String name, String phone) {
        mName = name;
        mPhone = phone;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
