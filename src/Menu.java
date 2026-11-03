import java.io.*;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;
    private final String NAMA_FILE = "data_menu.txt";

    public Menu() {
        daftarMenu = new ArrayList<>();
        muatDariFile();
    }

    public void tambahMenu(MenuItem item) {
        daftarMenu.add(item);
        simpanKeFile();
    }

    public void tampilkanMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("[Info] Daftar menu masih kosong.");
            return;
        }
        System.out.println("\n--- DAFTAR MENU RESTORAN ---");
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
        }
    }

    public MenuItem cariMenu(String nama) throws Exception {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new Exception("Menu '" + nama + "' tidak ditemukan! Mohon periksa kembali.");
    }

    public ArrayList<MenuItem> getDaftarMenu() { return daftarMenu; }

    private void simpanKeFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            for (MenuItem item : daftarMenu) {
                bw.write(item.formatSimpanFile());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file menu: " + e.getMessage());
        }
    }

    private void muatDariFile() {
        File file = new File(NAMA_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String baris;
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split(";");
                if (data[0].equals("Makanan")) {
                    daftarMenu.add(new Makanan(data[1], Double.parseDouble(data[2]), data[3], data[4]));
                } else if (data[0].equals("Minuman")) {
                    daftarMenu.add(new Minuman(data[1], Double.parseDouble(data[2]), data[3], data[4]));
                } else if (data[0].equals("Diskon")) {
                    daftarMenu.add(new Diskon(data[1], Double.parseDouble(data[2]), data[3], Double.parseDouble(data[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file menu: " + e.getMessage());
        }
    }
}