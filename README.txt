Tubes OOP - Kelompok 11
Nama         	: Dzulfaqor Ali Dipangegara
NIM            	: 18222017   	
Nama         	: Nasywaa Anggun Athiefah
NIM            	: 18222021
Nama         	: Habib Akhmad Al Farisi
NIM            	: 18222029
Nama         	: Muhammad Rafi Dhiyaulhaq
NIM            	: 18222069
Nama         	: Steven Adrian Corne
NIM            	: 18222101

Implementasi Plant vs Zombies dalam Bahasa Java
Cara Menjalankan

1.	Buka TUBES-OOP-2024-PvZ\app\src atau folder src
2.	Buka cmd (Windows) atau terminal (MacOS)
3.	Kompilasi semua file java dengan command:
4.	javac pvz/*.java
5.	Jalankan kelas utama:
6.	java pvz.Main

Tentang Game

Roro Jonggrang diserang oleh pasukan jin yang berubah menjadi zombie. Bandung Bondowoso ingin menyelamatkan istri tercintanya. Ia memiliki beberapa benih tanaman yang memiliki kemampuan khusus. Namun, dia bingung mencari strategi yang tepat. Mari bantu Bandung Bondowoso mensimulasikan serangan zombie dan strategi pertahanan yang akan digunakan.
Struktur Proyek

●	Main.java: Titik masuk aplikasi.
●	Player.java: Mengelola informasi dan tindakan pemain.
●	Map.java: Menghandle peta permainan di mana tanaman dan zombie berinteraksi.
●	Tile.java: Mewakili ubin individu pada peta permainan.
●	Time.java: Mengelola waktu dan penjadwalan permainan.
●	Inventory.java: Mengelola inventaris tanaman yang tersedia untuk pemain.
●	Plant.java: Kelas dasar untuk semua jenis tanaman.
●	Sun.java: Mengelola poin matahari yang digunakan untuk membeli tanaman.
●	PlantDeck.java: Menghandle pemilihan tanaman untuk permainan.
●	PlantandZombie.java: Logika permainan utama yang mengatur interaksi antara tanaman dan zombie.

Tanaman
●	Sunflower.java: Menghasilkan poin matahari dari waktu ke waktu.
●	PeaShooter.java: Menembakkan kacang pada zombie.
●	RepeaterPea.java: Menembakkan dua kacang sekaligus.
●	SnowPea.java: Memperlambat zombie dengan kacang beku.
●	Jalapeno.java: Meledak untuk memberikan kerusakan pada baris tertentu.
●	Wallnut.java: Tanaman pertahanan yang menghalangi zombie.
●	TallWallnut.java: Versi Wallnut yang lebih tinggi dan lebih kuat.
 
●	Lilypad.java: Memungkinkan penempatan tanaman di atas air.
●	TangleKelp.java: Menarik zombie ke bawah air.
●	Squash.java: Melompat dan menghancurkan zombie.
●	SunProduce.java: Kelas dasar untuk tanaman yang menghasilkan matahari.
●	Shooter.java: Kelas dasar untuk tanaman penembak.

Zombie
●	Zombie.java: Kelas dasar untuk semua jenis zombie.
●	NormalZombie.java: Zombie standar dengan perilaku dasar.
●	ConeheadZombie.java: Zombie dengan kerucut untuk perlindungan tambahan.
●	BucketheadZombie.java: Zombie dengan ember untuk perlindungan lebih lanjut.
●	PoleVaultingZombie.java: Melompati tanaman pertama yang ditemui.
●	FootballZombie.java: Zombie cepat dan kuat.
●	JesterZombie.java: Zombie dengan perilaku yang tidak dapat diprediksi.
●	DuckyTubeZombie.java: Zombie yang bisa berenang.
●	DolphinRiderZombie.java: Zombie berenang dengan cepat.
●	GiantZombie.java: Zombie yang sangat kuat dan tangguh.
●	ShieldZombie.java: Zombie dengan perisai.

Spesial
●	Bomb.java: Mewakili tanaman peledak.
●	Barrier.java: Mewakili penghalang pertahanan.
●	Aquatic.java: Antarmuka untuk tanaman dan zombie yang berada di air.
●	SpecialMove.java: Menghandle gerakan dan kemampuan khusus.
