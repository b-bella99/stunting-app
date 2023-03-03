package org.aplas.stuntingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private int id;
    private String nama, alamat, telp, email, password;

    public User() {
    }

    public User(String nama, String alamat, String telp, String email, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
        this.email = email;
        this.password = password;
    }

    protected User(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        alamat = in.readString();
        telp = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(telp);
        dest.writeString(email);
        dest.writeString(password);
    }
}
