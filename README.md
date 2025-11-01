# TP6DPBO2425C1

# Janji
Saya Rizka Aulia dengan NIM 2403245 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan

# Desain Program
Program ini menggunakan pendekatan Object-Oriented Programming (OOP) dengan pemisahan tanggung jawab antar kelas agar rapi dan mudah dikembangkan. Berikut penjelasan tiap kelasnya:
1. Logic.java (Logika utama permainan) : Berfungsi sebagai pengendali game (game controller). Tugasnya meliputi:
- Mengatur pergerakan player (burung) dan pipa.
- Mengatur gravitasi, collision detection, dan score.
- Menjalankan loop permainan dengan Timer.
- Mengatur kondisi game over dan restart.

2. Player.java (Objek burung pemain) : Kelas ini merepresentasikan karakter utama yang dikendalikan pemain. Berfungsi sebagai model data untuk burung tanpa logika yang kompleks. Digunakan oleh Logic dan View untuk menggambar posisi burung.

3. Pipe.java (Objek pipa penghalang) : Mewakili pipa atas dan bawah yang menjadi rintangan. Dikelola oleh Logic dalam sebuah ArrayList<Pipe> yang berisi semua pipa di layar.

4. View.java (Tampilan visual game) : Merupakan kelas tampilan (UI panel) yang mewarisi JPanel dan menangani semua gambar (rendering) yang terlihat di layar. Fungsinya:
- Menggambar background, player, dan pipa.
- Menampilkan UI overlay saat Game Over (dengan label skor dan tombol restart).
- Mengatur tata letak komponen (layout null) sehingga elemen dapat diposisikan bebas.

5. App.java (Jendela utama game) : Kelas ini membuat window permainan utama (JFrame). Berfungsi sebagai “tempat” bagi View dan label skor (JLabel).

6. MainMenu.java (Menu utama sebelum game) : Menampilkan halaman awal dengan judul “Flappy Bird”, tombol Play Game dan tombol Exit. Saat tombol Play Game diklik maka menu utama (MainMenu) akan ditutup dengan dispose() dan objek new App() dijalankan alias membuka game utama. Lalu saat tombol Exit yang diklik, maka akan keluar program.

# Alur Program
1. Program dimulai : Program dijalankan dari MainMenu.main() dan menampilkan jendela utama menu (judul + tombol).

2. Pemain menekan "Play Game" : Event listener di playButton mendeteksi klik, menu utama ditutup (dispose()) dan kelas App dibuat (membuka jendela permainan baru).

3. Inisialisasi Game, App akan membuat : 
- Logic → menangani pergerakan, skor, tabrakan, dll.
- View → menggambar tampilan.
- JLabel scoreLabel → menampilkan skor di layar.
View menambahkan KeyListener untuk mendeteksi tombol spasi (lompat) dan R (restart).

4. Game dimulai, Logic menyalakan dua timer:
- pipesCoolDown → menambahkan pipa setiap 1.5 detik.
- gameLoop → memperbarui posisi burung & pipa 60x per detik.
Pemain menekan spasi untuk membuat burung melompat (velocityY = -10).

5. Gameplay berlangsung, setiap frame:
- Burung jatuh akibat gravitasi (gravity = 1).
- Pipa bergerak ke kiri (pipeVelocityX = -2).
- Ketika burung melewati pipa bawah → skor naik.
- Jika menabrak pipa atau jatuh ke bawah → triggerGameOver() dijalankan.

6. Game Over : Timer dihentikan, view menampilkan overlay gelap dan teks “GAME OVER”, menampilkan skor akhir dan High Score, tombol “Restart” muncul.

8. Restart Game : Pemain menekan tombol “Restart” atau huruf R lalu restartGame() dipanggil dan akan menjalankan:
- Skor di-reset ke 0.
- Pipa dihapus.
- Posisi burung di-reset.
- Timer dinyalakan lagi.
- Game dimulai ulang dari awal.

# Dokumentasi
1. Play Game
https://github.com/user-attachments/assets/c66a429b-bc02-4d09-9bf7-237315f2256c

2. Exit
https://github.com/user-attachments/assets/78c6e855-e6f0-4323-9550-5e1054392b85

