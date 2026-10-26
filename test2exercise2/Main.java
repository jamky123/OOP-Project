

public class Main {
    public static void main(String[] args) {
        // 1. Bikin objek
        Address addrLibrary = new Address("Jalan Ilmu", "Kota Bharu", "Kelantan", "Malaysia");
        Library library = new Library("Knowledge Hub Library", addrLibrary);

        Address addrAli = new Address("Jalan Pelajar", "Kota Bharu", "Kelantan", "Malaysia");
        Member ali = new Member("M010", "Ali Bin Ahmad", addrAli);
        Book b1 = new Book("B001", "Fiction");
        Book b2 = new Book("B002", "Science");

        // 2. Tambah buku ke library
        library.addBook(b1);
        library.addBook(b2);

        // 3. Ali pinjam buku
        ali.borrowBook(b1, library);
        ali.borrowBook(b2, library);

        // 4. Ali baca + catat
        b1.addReadingLog("Read Chapter 1-5", 120.0);
        b1.addReadingLog("Read Chapter 6-10", 150.0);
        b1.addReadingLog("Skipped Index", 20.0); // ini yg nanti di-remove
        b2.addReadingLog("Read Chapter 1", 80.0);

        // 5. PRINT PERTAMA - sebelum dihapus
        System.out.println(library.getLibraryName());
        System.out.println("Address: " + library.getAddress());
        System.out.println("Books in Library:");
        library.printAllInfo();
        ali.printAllInfo();

        System.out.println(); // kasih jarak

        // 6. Aksi remove + return
        b1.removeReadingLog("Skipped Index", 20.0); // total B001 jadi 250.0
        b2.removeReadingLog("Read Chapter 1", 80.0); // borrowedBy B002 jadi "none"
        ali.returnBook(b2, library); // B002 hilang dari list pinjam Ali

        // 7. PRINT KEDUA - sesudah dihapus
        System.out.println(library.getLibraryName());
        System.out.println("Address: " + library.getAddress());
        System.out.println("Books in Library:");
        library.printAllInfo(); // B001 harusnya masih ada, total 250.0, log tinggal 2
        ali.printAllInfo(); // Borrowed Books tinggal B001 doang
    }
}