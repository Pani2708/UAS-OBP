package com.example.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi")
    private Long idTransaksi;

    @Column(name = "total_harga")
    private double totalHarga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMenu")
    private DetailMenu detailMenu;

    @Column(name = "qty")
    private Integer qty;

    public Transaction() {}

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void updateTotalHarga() {
        if (detailMenu != null && qty > 0) {
            this.totalHarga = detailMenu.getPrice() * qty;
        } else {
            this.totalHarga = 0;
        }
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DetailMenu getDetailMenu() {
        return detailMenu;
    }

    public void setDetailMenu(DetailMenu detailMenu) {
        this.detailMenu = detailMenu;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}