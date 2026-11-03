public class Diskon extends MenuItem {
    private double diskon;

    public Diskon(String nama, double batasMinimalHarga, String kategori, double diskon) {
        super(nama, batasMinimalHarga, kategori); 
        this.diskon = diskon;
    }

    public double getDiskon() { return diskon; }

    @Override
    public void tampilMenu() {
        System.out.println("- " + getNama() + " [" + getKategori() + "] - Diskon " + (diskon * 100) + "% (Min. Belanja: Rp" + getHarga() + ")");
    }

    @Override
    public String formatSimpanFile() {
        return "Diskon;" + getNama() + ";" + getHarga() + ";" + getKategori() + ";" + diskon;
    }
}