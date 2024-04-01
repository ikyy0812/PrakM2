/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Buku {
    private String judul;
    private String penulis;

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }
}

class Peminjaman {
    private String namaPeminjam;
    private String tanggalPeminjaman;

    public Peminjaman(String namaPeminjam, String tanggalPeminjaman) {
        this.namaPeminjam = namaPeminjam;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }
}

public class Perpustakaan {
    private static List<Buku> daftarBuku = new ArrayList<>();
    private static Map<Buku, Peminjaman> bukuDipinjam = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Perpustakaan:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Lihat Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Lihat Peminjaman");
            System.out.println("5. Kembalikan Buku");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    tambahBuku(scanner);
                    break;
                case 2:
                    lihatBuku();
                    break;
                case 3:
                    pinjamBuku(scanner);
                    break;
                case 4:
                    lihatPeminjaman();
                    break;
                case 5:
                    kembalikanBuku(scanner);
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan layanan perpustakaan.");
                    return;
                default:
                    System.out.println("Menu tidak valid. Silakan pilih menu yang benar.");
            }
        }
    }

    private static void tambahBuku(Scanner scanner) {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.next();
        System.out.print("Masukkan nama penulis: ");
        String penulis = scanner.next();

        Buku buku = new Buku(judul, penulis);
        daftarBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan ke dalam daftar.");
    }

    private static void lihatBuku() {
        System.out.println("Daftar Buku:");
        for (Buku buku : daftarBuku) {
            System.out.println("- " + buku.getJudul() + " oleh " + buku.getPenulis());
        }
    }

    private static void pinjamBuku(Scanner scanner) {
        lihatBuku();
        System.out.print("Pilih judul buku yang ingin dipinjam: ");
        String judul = scanner.next();
        Buku bukuDipilih = null;
        for (Buku buku : daftarBuku) {
            if (buku.getJudul().equals(judul)) {
                bukuDipilih = buku;
                break;
            }
        }
        if (bukuDipilih == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        if (bukuDipinjam.containsKey(bukuDipilih)) {
            System.out.println("Buku sedang dipinjam.");
        } else {
            System.out.print("Masukkan nama peminjam: ");
            String namaPeminjam = scanner.next();
            System.out.print("Masukkan tanggal peminjaman: ");
            String tanggalPeminjaman = scanner.next();
            Peminjaman peminjaman = new Peminjaman(namaPeminjam, tanggalPeminjaman);
            bukuDipinjam.put(bukuDipilih, peminjaman);
            System.out.println("Buku berhasil dipinjam.");
        }
    }

    private static void lihatPeminjaman() {
        System.out.println("Daftar Buku yang Sedang Dipinjam:");
        for (Map.Entry<Buku, Peminjaman> entry : bukuDipinjam.entrySet()) {
            Buku buku = entry.getKey();
            Peminjaman peminjaman = entry.getValue();
            System.out.println("- " + buku.getJudul() + " oleh " + buku.getPenulis() +
                    ", dipinjam oleh " + peminjaman.getNamaPeminjam() +
                    ", tanggal peminjaman: " + peminjaman.getTanggalPeminjaman());
        }
    }

    private static void kembalikanBuku(Scanner scanner) {
        lihatPeminjaman();
        System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
        String judul = scanner.next();
        Buku bukuDikembalikan = null;
        for (Buku buku : bukuDipinjam.keySet()) {
            if (buku.getJudul().equals(judul)) {
                bukuDikembalikan = buku;
                break;
            }
        }
        if (bukuDikembalikan == null) {
            System.out.println("Buku tidak ditemukan atau tidak sedang dipinjam.");
            return;
        }
        bukuDipinjam.remove(bukuDikembalikan);
        System.out.println("Buku berhasil dikembalikan.");
    }
}
