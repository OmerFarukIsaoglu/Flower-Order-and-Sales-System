/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bp2proje2;

/**
 *
 * @author awada
 */
// Exception sınıfından türetilen özel sipariş exception sınıfı
public class SiparisException extends Exception{
    // Hata mesajı constructor aracılığıyla üst sınıfa iletiliyor
    public SiparisException(String mesaj) {
        super(mesaj);
    }
}
