-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 15 Jan 2019 pada 05.08
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 5.6.39

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_barang` bigint(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `id_kategori` int(5) NOT NULL,
  `harga` bigint(10) NOT NULL,
  `tgl_kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_barang`, `nama`, `id_kategori`, `harga`, `tgl_kadaluarsa`) VALUES
(123, 'AYAM GEPREK + ES TEH', 1, 13000, '2012-12-12'),
(963, 'AYAM GEPREK KEJU + ES TEH', 1, 16000, '2012-12-12'),
(5555, 'AYAM GEPREK + TEMPE + ES TEH', 1, 14000, '2012-12-12'),
(12345, 'AYAM GEPREK + JAMUR + ES TEH', 1, 17000, '2012-12-12'),
(55555, 'NASI PUTIH', 1, 3000, '2012-12-12'),
(5656565, 'ES TEH', 2, 2000, '2017-07-30'),
(32323232, 'ES JERUK', 2, 3000, '2017-07-25');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kode_barang` bigint(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `kode_barang`) VALUES
(1, 123),
(2, 123),
(3, 123),
(4, 12345),
(6, 123),
(7, 123),
(8, 123),
(9, 123),
(10, 123),
(11, 12345),
(12, 5555),
(13, 32323232),
(14, 123),
(15, 123),
(16, 55555),
(17, 963),
(18, 5555),
(19, 32323232),
(20, 32323232),
(21, 123),
(22, 5656565),
(23, 55555),
(24, 123),
(25, 123),
(26, 123),
(27, 123),
(28, 123),
(29, 123),
(30, 123),
(31, 123),
(32, 123),
(33, 963),
(34, 123),
(35, 123),
(36, 55555),
(37, 55555),
(38, 55555),
(39, 123),
(40, 55555);

-- --------------------------------------------------------

--
-- Struktur dari tabel `faktur`
--

CREATE TABLE `faktur` (
  `id_faktur` bigint(10) NOT NULL,
  `nomer_faktur` bigint(10) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `faktur`
--

INSERT INTO `faktur` (`id_faktur`, `nomer_faktur`, `tanggal`) VALUES
(1, 12345, '2017-06-07'),
(2, 123456789, '2017-07-23'),
(3, 55555, '2017-07-23');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(5) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama`) VALUES
(1, 'Makanan'),
(2, 'Minuman');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` bigint(10) NOT NULL,
  `id_faktur` bigint(10) NOT NULL,
  `id_suplier` int(5) NOT NULL,
  `tanggal` date NOT NULL,
  `jumlah_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `id_faktur`, `id_suplier`, `tanggal`, `jumlah_harga`) VALUES
(1, 1, 1, '2017-07-23', 1000000),
(2, 2, 2, '2017-07-23', 500000),
(3, 3, 1, '2017-07-18', 1000000),
(4, 3, 2, '2017-07-10', 1000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplier`
--

CREATE TABLE `suplier` (
  `id_suplier` int(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` bigint(15) NOT NULL,
  `bank` varchar(20) NOT NULL,
  `nomor_rekening` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `suplier`
--

INSERT INTO `suplier` (`id_suplier`, `nama`, `alamat`, `no_telp`, `bank`, `nomor_rekening`) VALUES
(1, 'Fiva Renas', 'Kebumen', 85728757832, 'Mandiri', 123456789),
(2, 'Fera Saputri', 'Jakarta', 85222333555, 'BRI', 987654321);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kode_transaksi` int(11) NOT NULL,
  `nama_kasir` varchar(30) NOT NULL,
  `tanggal` date NOT NULL,
  `harga_barang` int(8) NOT NULL,
  `jumlah_barang` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `kode_transaksi`, `nama_kasir`, `tanggal`, `harga_barang`, `jumlah_barang`) VALUES
(1, 1, 'Wahyu', '2017-07-22', 10000, 1),
(2, 1, 'Wahyu', '2017-07-22', 10000, 1),
(3, 2, 'Wahyu', '2017-07-22', 10000, 1),
(4, 2, 'Wahyu', '2017-07-22', 20000, 2),
(5, 2, 'Wahyu', '2017-07-22', 20000, 10),
(6, 3, 'Wahyu', '2017-07-22', 50000, 5),
(7, 4, 'Wahyu', '2017-07-25', 10000, 1),
(8, 4, 'Wahyu', '2017-07-25', 50000, 5),
(9, 5, 'Wahyu', '2017-07-25', 10000, 1),
(10, 5, 'Wahyu', '2017-07-25', 50000, 5),
(11, 5, 'Wahyu', '2017-07-25', 10000, 1),
(12, 5, 'Wahyu', '2017-07-25', 34000, 2),
(13, 5, 'Wahyu', '2017-07-25', 10000, 10),
(14, 6, 'Wahyu', '2017-07-25', 10000, 1),
(15, 6, 'Wahyu', '2017-07-25', 20000, 2),
(16, 6, 'Wahyu', '2017-07-25', 10000, 5),
(17, 6, 'Wahyu', '2017-07-25', 4000, 2),
(18, 6, 'Wahyu', '2017-07-25', 17000, 1),
(19, 6, 'Wahyu', '2017-07-25', 1000, 1),
(20, 7, 'Wahyu', '2017-07-25', 10000, 10),
(21, 7, 'Wahyu', '2017-07-25', 10000, 1),
(22, 7, 'Wahyu', '2017-07-25', 10000, 2),
(23, 7, 'Wahyu', '2017-07-25', 10000, 5),
(24, 8, 'Wahyu', '2017-07-26', 10000, 1),
(25, 9, 'Wahyu', '2017-07-26', 10000, 1),
(26, 10, 'Wahyu', '2017-07-26', 10000, 1),
(27, 11, 'Wahyu', '2017-07-26', 10000, 1),
(28, 12, 'Wahyu', '2017-07-26', 10000, 1),
(29, 12, 'Wahyu', '2017-07-26', 10000, 1),
(30, 13, 'Wahyu', '2017-07-26', 10000, 1),
(31, 13, 'Wahyu', '2017-07-26', 10000, 1),
(32, 13, 'Wahyu', '2017-07-26', 10000, 1),
(33, 13, 'Wahyu', '2017-07-26', 10000, 5),
(34, 14, 'Wahyu', '2017-07-27', 10000, 1),
(35, 15, 'Wahyu', '2017-07-27', 40000, 4),
(36, 15, 'Wahyu', '2017-07-27', 10000, 5),
(37, 16, 'chandra', '2019-01-14', 2000, 2),
(38, 17, 'muflih', '2019-01-14', 3000, 1),
(39, 18, 'muflih', '2019-01-14', 13000, 1),
(40, 19, 'muflih', '2019-01-15', 3000, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(5) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `level` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', 'Admin'),
(3, 'kasir', 'kasir', 'Kasir'),
(4, 'chandra', 'chandra', 'Kasir'),
(5, 'muflih', 'muflih', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `barang_ibfk_1` (`id_kategori`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `faktur`
--
ALTER TABLE `faktur`
  ADD PRIMARY KEY (`id_faktur`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `pembelian_ibfk_1` (`id_faktur`),
  ADD KEY `pembelian_ibfk_2` (`id_suplier`);

--
-- Indeks untuk tabel `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode_barang`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`id_faktur`) REFERENCES `faktur` (`id_faktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`id_suplier`) REFERENCES `suplier` (`id_suplier`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
