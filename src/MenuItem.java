public abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Encapsulation (Getter & Setter)
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    // Metode Abstrak
    public abstract void tampilMenu();
    public abstract String formatSimpanFile();
}