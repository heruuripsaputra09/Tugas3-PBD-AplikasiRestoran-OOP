import java.io.*;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<MenuItem> daftarPesanan;
    private final String FILE_STRUK = "struk_pesanan.txt";

    public Pesanan() {
        daftarPesanan = new ArrayList<>();
    }

    public void tambahPesanan(MenuItem item) {
        daftarPesanan.add(item);
    }

    public void cetakStruk(Menu menuRestoran) {
        if (daftarPesanan.isEmpty()) {
            System.out.println("[Info] Anda belum memesan apapun.");
            return;
        }

        System.out.println("\n===========================");
        System.out.println("       STRUK PESANAN       ");
        System.out.println("===========================");
        
        double totalKotor = 0;
        for (MenuItem item : daftarPesanan) {
            System.out.println(item.getNama() + " \t Rp" + item.getHarga());
            totalKotor += item.getHarga();
        }

        double maxDiskon = 0;
        for (MenuItem item : menuRestoran.getDaftarMenu()) {
            if (item instanceof Diskon) {
                Diskon d = (Diskon) item;
                if (totalKotor >= d.getHarga()) {
                    double nominalDiskon = totalKotor * d.getDiskon();
                    if (nominalDiskon > maxDiskon) {
                        maxDiskon = nominalDiskon;
                    }
                }
            }
        }

        double totalBersih = totalKotor - maxDiskon;

        System.out.println("---------------------------");
        System.out.println("Subtotal    : Rp" + totalKotor);
        if (maxDiskon > 0) {
            System.out.println("Diskon      :-Rp" + maxDiskon);
        }
        System.out.println("Total Bayar : Rp" + totalBersih);
        System.out.println("===========================");

        simpanStrukKeFile(totalKotor, maxDiskon, totalBersih);
    }

    private void simpanStrukKeFile(double subtotal, double diskon, double total) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_STRUK, true))) { 
            bw.write("=== STRUK PESANAN BARU ===");
            bw.newLine();
            for (MenuItem item : daftarPesanan) {
                bw.write(item.getNama() + " \t Rp" + item.getHarga());
                bw.newLine();
            }
            bw.write("---------------------------");
            bw.newLine();
            bw.write("Subtotal    : Rp" + subtotal); bw.newLine();
            if (diskon > 0) { bw.write("Diskon      :-Rp" + diskon); bw.newLine(); }
            bw.write("Total Bayar : Rp" + total); bw.newLine();
            bw.write("===========================\n\n");
            
            System.out.println("-> Struk digital berhasil disimpan ke '" + FILE_STRUK + "'");
        } catch (IOException e) {
            System.out.println("Gagal mencetak struk: " + e.getMessage());
        }
    }
}