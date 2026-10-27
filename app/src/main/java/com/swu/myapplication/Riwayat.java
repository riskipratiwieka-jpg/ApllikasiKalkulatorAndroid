package com.swu.myapplication;

public class Riwayat {
    private String perhitungan;
    private String hasil;
    public Riwayat(String perhitungan, String hasil){
      this.perhitungan = perhitungan;
      this.hasil = hasil;
    }
    public String getPerhitungan(){
        return perhitungan;
    }
    public String getHasil(){
        return hasil;
    }
}
