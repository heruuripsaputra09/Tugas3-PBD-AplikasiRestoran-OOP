public class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String kategori, String jenisMinuman) {
        super(nama, harga, kategori);
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() { return jenisMinuman; }

    @Override
    public void tampilMenu() {
        System.out.println("- " + getNama() + " [" + getKategori() + " - " + jenisMinuman + "] - Rp" + getHarga());
    }

    @Override
    public String formatSimpanFile() {
        return "Minuman;" + getNama() + ";" + getHarga() + ";" + getKategori() + ";" + jenisMinuman;
    }
}