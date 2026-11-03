import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu pengelolaMenu = new Menu();
        Pesanan pesananPelanggan = new Pesanan();
        int pilihanUtama = 0;

        do {
            System.out.println("\n=== MANAJEMEN RESTORAN (TUGAS 3) ===");
            System.out.println("1. Tambah Item Menu");
            System.out.println("2. Tampilkan Menu Restoran");
            System.out.println("3. Input Pesanan Pelanggan");
            System.out.println("4. Hitung & Cetak Struk");
            System.out.println("5. Keluar Aplikasi");
            System.out.print("Pilih Menu (1-5): ");

            try {
                pilihanUtama = Integer.parseInt(input.nextLine());

                switch (pilihanUtama) {
                    case 1:
                        tambahMenuUI(input, pengelolaMenu);
                        break;
                    case 2:
                        pengelolaMenu.tampilkanMenu();
                        break;
                    case 3:
                        terimaPesananUI(input, pengelolaMenu, pesananPelanggan);
                        break;
                    case 4:
                        pesananPelanggan.cetakStruk(pengelolaMenu);
                        pesananPelanggan = new Pesanan(); 
                        break;
                    case 5:
                        System.out.println("\nProgram selesai. Data otomatis tersimpan di file .txt.");
                        break;
                    default:
                        System.out.println("[Peringatan] Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error Input] Harap masukkan angka bulat!"); 
            }
        } while (pilihanUtama != 5);

        input.close();
    }

    private static void tambahMenuUI(Scanner input, Menu pengelolaMenu) {
        System.out.println("\n--- Tipe Menu Baru ---");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Voucher Diskon");
        System.out.print("Pilih tipe (1-3): ");
        String tipe = input.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();

        System.out.print("Kategori Umum (contoh: Utama/Dessert/Promo): ");
        String kategori = input.nextLine();

        System.out.print("Harga (Atau batas Min. Belanja khusus opsi Diskon): ");
        double harga = Double.parseDouble(input.nextLine());

        if (tipe.equals("1")) {
            System.out.print("Jenis Makanan (Berat/Ringan): ");
            String jenisMakanan = input.nextLine();
            pengelolaMenu.tambahMenu(new Makanan(nama, harga, kategori, jenisMakanan));
            System.out.println("-> Makanan ditambahkan!");
        } else if (tipe.equals("2")) {
            System.out.print("Jenis Minuman (Dingin/Panas): ");
            String jenisMinuman = input.nextLine();
            pengelolaMenu.tambahMenu(new Minuman(nama, harga, kategori, jenisMinuman));
            System.out.println("-> Minuman ditambahkan!");
        } else if (tipe.equals("3")) {
            System.out.print("Nominal Diskon (Desimal, misal 0.1 untuk 10%): ");
            double diskon = Double.parseDouble(input.nextLine());
            pengelolaMenu.tambahMenu(new Diskon(nama, harga, kategori, diskon));
            System.out.println("-> Diskon diterapkan pada sistem!");
        } else {
            System.out.println("[Gagal] Tipe tidak dikenal.");
        }
    }

    private static void terimaPesananUI(Scanner input, Menu pengelolaMenu, Pesanan pesananPelanggan) {
        System.out.println("\n--- Input Pemesanan ---");
        pengelolaMenu.tampilkanMenu();
        System.out.println("\n(Ketik 'selesai' jika pesanan sudah cukup)");
        
        while (true) {
            System.out.print("Ketik Nama Menu: ");
            String pesanan = input.nextLine().trim();
            
            if (pesanan.equalsIgnoreCase("selesai")) {
                break;
            }
            
            try {
                MenuItem itemDitemukan = pengelolaMenu.cariMenu(pesanan);
                
                if (itemDitemukan instanceof Diskon) {
                    System.out.println("[Peringatan] Item 'Diskon' akan otomatis dihitung, tidak untuk dipesan.");
                } else {
                    pesananPelanggan.tambahPesanan(itemDitemukan);
                    System.out.println("=> '" + itemDitemukan.getNama() + "' ditambahkan ke keranjang.");
                }
            } catch (Exception e) {
                System.out.println("[Error]" + e.getMessage());
            }
        }
    }
}