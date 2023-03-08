package com.test.lichday.model;

import javax.persistence.*;

@Entity
@Table
public class Lhp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lhpName;
    private String toa;
    private String phong;
    private int kip;
    private int thu;

    public Lhp(){}


    public Lhp(String lhpName, String toa, String phong, int kip, int thu) {

        this.lhpName = lhpName;
        this.toa = toa;
        this.phong = phong;
        this.kip = kip;
        this.thu = thu;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLhpName() {
        return lhpName;
    }

    public void setLhpName(String lhpName) {
        this.lhpName = lhpName;
    }

    public String getToa() {
        return toa;
    }

    public void setToa(String toa) {
        this.toa = toa;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getKip() {
        return kip;
    }

    public void setKip(int kip) {
        this.kip = kip;
    }


    @Override
    public String toString() {
        return "Lhp{" +
                "id=" + id +
                ", lhpName='" + lhpName + '\'' +
                ", toa='" + toa + '\'' +
                ", phong='" + phong + '\'' +
                ", kip=" + kip +
                ", thu=" + thu +
                '}';
    }
}
