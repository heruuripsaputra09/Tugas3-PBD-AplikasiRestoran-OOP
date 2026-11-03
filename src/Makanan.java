public class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String kategori, String jenisMakanan) {
        super(nama, harga, kategori);
        this.jenisMakanan = jenisMakanan;
    }

    public String getJenisMakanan() { return jenisMakanan; }

    @Override
    public void tampilMenu() {
        System.out.println("- " + getNama() + " [" + getKategori() + " - " + jenisMakanan + "] - Rp" + getHarga());
    }

    @Override
    public String formatSimpanFile() {
        return "Makanan;" + getNama() + ";" + getHarga() + ";" + getKategori() + ";" + jenisMakanan;
    }
}