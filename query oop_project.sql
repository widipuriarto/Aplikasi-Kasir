CREATE oop_project;
USE oop_project;

CREATE TABLE users (
    idUser INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    namaLengkap VARCHAR(100),
    role ENUM('kasir', 'admin') DEFAULT 'kasir'
);

CREATE TABLE barang (
    kodeBarang INT PRIMARY KEY AUTO_INCREMENT,
    namaBarang VARCHAR(100) NOT NULL,
    deskripsi TEXT,
    hargaJual INT,
    hargaBeli INT,
    stok INT NOT NULL
);


CREATE TABLE transaksi (
    idTransaksi INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT,
    tanggal DATETIME NOT NULL,
    kodeBarang INT,
    namaBarang VARCHAR(100),
    harga INT,
    jumlah INT,
    subtotal INT,
    FOREIGN KEY (idUser) REFERENCES users(idUser)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (kodeBarang) REFERENCES barang(kodeBarang)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE keranjang (
    idTransaksi INT,
    namaBarang VARCHAR(100),
    harga INT,
    jumlah INT,
    subtotal INT,
    FOREIGN KEY (idTransaksi) REFERENCES transaksi(idTransaksi)
        ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO users (username, PASSWORD, namaLengkap, role) VALUES
('admin', '123', 'Admin', 'admin'),
('kasir01', 'kasir123', 'Kasir Satu', 'kasir'),
('kasir02', 'kasir123', 'Kasir Dua', 'kasir'),
('kasir03', 'kasir123', 'Kasir Tiga', 'kasir'),
('kasir04', 'kasir123', 'Kasir Empat', 'kasir');

INSERT INTO barang (namaBarang, deskripsi, hargaJual, stok) VALUES
('Beras 5kg', 'Beras premium kemasan 5kg', 65000, 30),
('Minyak Goreng 2L', 'Minyak goreng kemasan 2 liter', 32000, 25),
('Gula Pasir 1kg', 'Gula pasir putih', 14000, 50),
('Sabun Mandi Lifebuoy', 'Sabun batang ukuran besar', 4500, 100),
('Indomie Goreng', 'Mie instan rasa goreng', 3000, 200),
('Susu Dancow 400g', 'Susu bubuk anak-anak', 42000, 40),
('Teh Celup Sariwangi', 'Isi 25 kantong', 9000, 60),
('Kopi Kapal Api', 'Kopi hitam kemasan sachet', 2500, 150),
('Tissue Paseo', 'Tissue wajah isi 200 lembar', 12000, 35),
('Aqua 600ml', 'Air mineral kemasan botol', 3500, 180),
('Rinso 1kg', 'Deterjen bubuk 1kg', 17000, 45),
('Baygon Aerosol', 'Obat nyamuk semprot', 23000, 20),
('Pepsodent 190g', 'Pasta gigi keluarga', 12000, 55),
('Telur Ayam 1kg', 'Telur ayam segar', 25000, 70),
('Sarden ABC', 'Sarden dalam saus tomat', 8500, 90),
('Mie Sedap Soto', 'Mie instan rasa soto', 2800, 210),
('Kecap Bango 275ml', 'Kecap manis botol', 11000, 50),
('Sambal ABC 335ml', 'Saos sambal botol', 10500, 40),
('Bayam 1 ikat', 'Sayur bayam segar', 3000, 60),
('Wortel 1kg', 'Wortel segar', 9000, 80),
('Dettol Handwash', 'Sabun cuci tangan', 18000, 50),
('Sunlight Jeruk 800ml', 'Sabun cuci piring', 16000, 45),
('Royco Ayam 250g', 'Penyedap rasa ayam', 6500, 90),
('Blue Band 250g', 'Margarin serbaguna', 12000, 50),
('Good Day Freeze', 'Minuman kopi siap minum', 5000, 100),
('Le Minerale 1.5L', 'Air mineral ukuran besar', 7000, 70),
('Coca Cola 1L', 'Minuman soda', 9500, 60),
('Sprite 1L', 'Minuman soda lemon', 9500, 60),
('Fanta 1L', 'Minuman soda stroberi', 9500, 60),
('Ultra Milk Coklat', 'Susu UHT coklat 1L', 11000, 80),
('Ultra Milk Vanilla', 'Susu UHT vanila 1L', 11000, 80),
('Ultra Milk Strawberry', 'Susu UHT stroberi 1L', 11000, 80),
('Sari Roti Tawar', 'Roti tawar tanpa kulit', 14000, 40),
('Chitato Sapi Panggang', 'Keripik kentang rasa sapi', 9500, 90),
('Qtela Singkong', 'Keripik singkong original', 7000, 85),
('SilverQueen Almond', 'Cokelat batangan almond', 18000, 45),
('Tango Vanilla', 'Wafer krim vanila', 5000, 100),
('Beng-Beng Share It', 'Cokelat mini isi banyak', 12000, 60),
('Top Coffee Susu', 'Kopi susu sachet', 2500, 120),
('Energen Coklat', 'Minuman sereal rasa coklat', 3000, 150),
('Energen Vanila', 'Minuman sereal rasa vanila', 3000, 150),
('Supermi Ayam Bawang', 'Mie instan klasik', 2700, 200),
('Mie ABC Selera Pedas', 'Mie instan pedas', 2800, 190),
('Sarimi Ayam Kecap', 'Mie instan rasa ayam kecap', 2900, 185),
('Pop Mie Ayam', 'Mie instan cup ayam', 6000, 100),
('Pop Mie Baso', 'Mie instan cup baso', 6000, 100),
('Yakult 5 botol', 'Minuman probiotik', 9500, 60),
('Buavita Jambu', 'Jus jambu botol', 7000, 70),
('Floridina Orange', 'Minuman jeruk dengan bulir', 4000, 80),
('Pulpy Orange', 'Minuman jeruk dengan serat', 7500, 60);


-- =============== REVISI ===========================

ALTER TABLE barang ADD COLUMN hargaBeli INT AFTER hargaJual;
ALTER TABLE barang
ADD CONSTRAINT fk_barang_supplierBarang
FOREIGN KEY (kodeBarang)
REFERENCES supplier_barang(kodeBarang);


CREATE TABLE supplier (
    idSupplier INT AUTO_INCREMENT PRIMARY KEY,
    namaSupplier VARCHAR(100) NOT NULL,
    alamat TEXT,
    telepon VARCHAR(20)
);

CREATE TABLE supplier_barang (
    kodeBarang INT PRIMARY KEY,
    idSupplier INT,
    namaBarang VARCHAR(100) NOT NULL,
    hargaSatuan INT NOT NULL,
    FOREIGN KEY (idSupplier) REFERENCES supplier(idSupplier)
);


CREATE TABLE pembelian (
    idPembelian INT AUTO_INCREMENT PRIMARY KEY,
    kodeBarang INT,
    idSupplier INT,
    jumlah INT,
    hargaBeli INT,
    totalHarga INT,
    tanggal DATE,
    FOREIGN KEY (kodeBarang) REFERENCES supplier_barang(kodeBarang),
    FOREIGN KEY (idSupplier) REFERENCES supplier(idSupplier)
);


CREATE TABLE stok_mutasi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE,
    kodeBarang INT,
    namaBarang VARCHAR(100),
    tipe ENUM('masuk', 'keluar'),
    jumlah INT,
    stokTerakhir INT,
    FOREIGN KEY (kodeBarang) REFERENCES barang(kodeBarang)
);

INSERT INTO supplier (idSupplier, namaSupplier, alamat, telepon) VALUES
(101, 'PT. Maju Jaya', 'Jl. Raya Timur No.1', '081234567801'),
(102, 'CV. Sumber Berkah', 'Jl. Melati No.12', '081234567802'),
(103, 'UD. Sentosa', 'Jl. Mawar No.34', '081234567803'),
(104, 'PT. Segar Abadi', 'Jl. Kenanga No.45', '081234567804'),
(105, 'CV. Sinar Harapan', 'Jl. Anggrek No.56', '081234567805'),
(106, 'UD. Bintang Terang', 'Jl. Dahlia No.67', '081234567806'),
(107, 'PT. Prima Logistik', 'Jl. Cempaka No.78', '081234567807'),
(108, 'CV. Makmur Sejahtera', 'Jl. Flamboyan No.89', '081234567808'),
(109, 'UD. Harapan Mulya', 'Jl. Kamboja No.90', '081234567809'),
(110, 'PT. Indo Supplier', 'Jl. Teratai No.100', '081234567810');

INSERT INTO supplier_barang (kodeBarang, idSupplier, namaBarang, hargaSatuan) VALUES
(1, 101, 'Beras Ramos 5kg', 65000),
(2, 101, 'Minyak Goreng 2L', 33000),
(3, 101, 'Gula Pasir 1kg', 14500),
(4, 102, 'Sabun Lifebuoy', 5000),
(5, 102, 'Pasta Gigi Pepsodent', 8500),
(6, 102, 'Sampo Sunsilk 170ml', 18000),
(7, 103, 'Kopi Kapal Api 250g', 22000),
(8, 103, 'Teh Celup Sosro 30s', 15000),
(9, 103, 'Air Mineral 600ml', 3000),
(10, 104, 'Tepung Terigu Segitiga', 11000),
(11, 104, 'Garam Halus 500g', 4000),
(12, 104, 'Mentega Blueband 250g', 13500),
(13, 105, 'Susu Kental Manis', 10000),
(14, 105, 'Kecap ABC 275ml', 12000),
(15, 105, 'Saus Sambal Indofood', 13000),
(16, 106, 'Mi Instan Goreng', 3000),
(17, 106, 'Mi Instan Kuah', 3000),
(18, 106, 'Kerupuk Udang 200g', 9000),
(19, 107, 'Tisu Basah 50 lembar', 14000),
(20, 107, 'Tisu Kering Paseo', 15000),
(21, 107, 'Hand Sanitizer 100ml', 17000),
(22, 108, 'Baterai ABC AA 2pcs', 8000),
(23, 108, 'Lampu LED 10w', 23000),
(24, 108, 'Stop Kontak 3 Lubang', 25000),
(25, 109, 'Sabun Cuci Piring Sunlight', 7000),
(26, 109, 'Sapu Lantai Plastik', 12000),
(27, 109, 'Kemoceng Serat', 10000),
(28, 110, 'Botol Minum 1L', 17000),
(29, 110, 'Tempat Makan 3 Sekat', 18000),
(30, 110, 'Gelas Plastik 6 pcs', 15000),
(31, 101, 'Tepung Beras Rosebrand', 11500),
(32, 101, 'Kacang Hijau 500g', 18000),
(33, 101, 'Minyak Goreng Curah', 29000),
(34, 102, 'Sikat Gigi Formula', 6000),
(35, 102, 'Sampo Dove 170ml', 19000),
(36, 102, 'Body Lotion Vaseline', 25000),
(37, 103, 'Teh Botol 350ml', 4000),
(38, 103, 'Susu Ultra Milk 250ml', 6000),
(39, 103, 'Air Galon Aqua', 20000),
(40, 104, 'Bumbu Racik Ayam', 2500),
(41, 104, 'Santan Kara 200ml', 7500),
(42, 104, 'Ragi Instan 100g', 8000),
(43, 105, 'Sambal ABC Sachet', 1000),
(44, 105, 'Kecap Manis Bango 275ml', 13000),
(45, 105, 'Saus Tomat Delmonte', 12000),
(46, 106, 'Mi Sedap Ayam Bawang', 3000),
(47, 106, 'Mi Sedap Soto', 3000),
(48, 106, 'Kerupuk Ikan 250g', 10000),
(49, 107, 'Tisu Gulung Nice', 12000),
(50, 107, 'Masker Kain 3 Lapis', 7000);


CREATE TABLE saldo_bulanan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bulan VARCHAR(7) NOT NULL, -- format '2025-07'
    saldo_awal INT NOT NULL,
    saldo_akhir INT DEFAULT 0
);

INSERT INTO saldo_bulanan (bulan, saldo_awal)
VALUES ('2025-06', 5000000); -- Contoh saldo awal bulan Juli
