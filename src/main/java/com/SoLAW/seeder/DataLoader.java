package com.SoLAW.seeder;

import com.SoLAW.model.Articles;
import com.SoLAW.model.Categories;
import com.SoLAW.repository.ArticlesRepository;
import com.SoLAW.repository.CategoriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ArticlesRepository articlesRepository;
    private final CategoriesRepository categoriesRepository;

    public DataLoader(ArticlesRepository articlesRepository, CategoriesRepository categoriesRepository) {
        this.articlesRepository = articlesRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        articlesRepository.deleteAll();
        categoriesRepository.deleteAll();

        // Seeder Categories
        List<Categories> categoriesList = new ArrayList<>();
        Categories properti = new Categories(0, "Properti", null);
        Categories bank = new Categories(0, "Bank", null);
        Categories perusahaan = new Categories(0, "Perusahaan", null);
        Categories sosial = new Categories(0, "Sosial", null);
        categoriesList.add(properti);
        categoriesList.add(bank);
        categoriesList.add(perusahaan);
        categoriesList.add(sosial);

        categoriesRepository.saveAll(categoriesList);

        // Seeder Articles
        List<Articles> articlesList = new ArrayList<>();
        Articles article1 = Articles.builder()
                .title("Melindungi Hak Kepemilikan dan Pemanfaatan")
                .description("Hukum properti adalah cabang hukum yang mengatur hak kepemilikan dan penggunaan properti, termasuk tanah dan bangunan. Tujuan utama dari hukum ini adalah melindungi hak pemilik properti dan mencegah terjadinya konflik antar pihak terkait kepemilikan atau pemanfaatan properti. Di Indonesia, hukum properti diatur melalui KUH Perdata dan UUPA, yang memberikan dasar hukum bagi transaksi properti, warisan, dan penyelesaian sengketa tanah.")
                .content("Hukum properti adalah bagian dari hukum perdata yang mengatur kepemilikan, pemanfaatan, dan distribusi properti, baik bergerak maupun tidak bergerak. Di Indonesia, hukum properti terutama mengacu pada Kitab Undang-Undang Hukum Perdata (KUH Perdata) dan Undang-Undang Pokok Agraria (UUPA). Melalui hukum properti, pemilik memiliki hak yang sah untuk menguasai, menggunakan, dan mengalihkan propertinya kepada pihak lain. Regulasi ini mencakup hal-hal seperti jual beli tanah, warisan, sewa-menyewa, hak pakai, hingga sengketa batas tanah.\n" +
                        "Dengan adanya hukum properti, diharapkan pemilik dapat mempertahankan hak atas properti yang dimilikinya dan terhindar dari konflik dengan pihak lain. Hukum ini juga memberikan landasan bagi pemerintah untuk melakukan pengaturan penggunaan lahan demi kepentingan umum, seperti pembebasan lahan untuk pembangunan infrastruktur. Namun, kompleksitas regulasi dan birokrasi sering kali membuat masyarakat bingung dan rentan terhadap konflik hukum, khususnya terkait hak kepemilikan dan perubahan status lahan. Maka, pemahaman yang baik mengenai hukum properti menjadi penting untuk melindungi hak-hak kepemilikan serta memastikan pemanfaatan properti sesuai peraturan yang berlaku.")
                .thumbnailUrl("https://images.unsplash.com/photo-1528747008803-f9f5cc8f1a64?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .status(Articles.Status.publish)
                .categories(new ArrayList<>())
                .build();
        article1.getCategories().add(properti);

        Articles article2 = Articles.builder()
                .title("Hukum Perbankan di Indonesia: Dasar dan Perkembangannya")
                .description("Hukum perbankan di Indonesia mengatur operasional bank dan lembaga keuangan lainnya, dengan tujuan menjaga stabilitas sistem keuangan dan melindungi hak-hak nasabah. Berdasarkan Undang-Undang Nomor 10 Tahun 1998 tentang Perbankan, regulasi ini mencakup berbagai aspek mulai dari pendirian bank hingga tata kelola dan transparansi transaksi. Dengan perkembangan teknologi finansial, hukum perbankan juga harus menghadapi tantangan baru, seperti perlindungan data nasabah. Upaya pemerintah dan otoritas terkait dalam memperbarui regulasi diharapkan dapat menciptakan iklim perbankan yang sehat, aman, dan mendukung pertumbuhan ekonomi nasional.")
                .content("Hukum perbankan di Indonesia berfungsi sebagai landasan bagi operasional bank dan lembaga keuangan lainnya. Undang-Undang Nomor 10 Tahun 1998 tentang Perbankan merupakan regulasi utama yang mengatur berbagai aspek, mulai dari pendirian bank, tata kelola, hingga perlindungan nasabah. Dalam konteks ini, hukum perbankan tidak hanya bertujuan untuk menjaga stabilitas sistem keuangan, tetapi juga untuk melindungi hak-hak nasabah serta memastikan transparansi dalam transaksi keuangan.\n" +
                        "Perkembangan teknologi informasi dan komunikasi, seperti kehadiran layanan financial technology (fintech), telah membawa tantangan baru bagi hukum perbankan. Misalnya, perlindungan data nasabah dan keadilan dalam akses layanan keuangan menjadi isu penting yang perlu diatur lebih lanjut.\n" +
                        "Pemerintah dan otoritas terkait terus berupaya untuk memperbarui dan menyesuaikan regulasi agar dapat menciptakan iklim perbankan yang sehat dan mendukung pertumbuhan ekonomi nasional. Dengan demikian, hukum perbankan di Indonesia diharapkan mampu beradaptasi dengan perubahan yang cepat dan tetap relevan dalam menghadapi tantangan masa depan.")
                .thumbnailUrl("https://images.unsplash.com/photo-1607863680198-23d4b2565df0?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .status(Articles.Status.publish)
                .categories(new ArrayList<>())
                .build();
        article2.getCategories().add(bank);

        Articles article3 = Articles.builder()
                .title("Perlindungan Hukum atas Hak Properti dalam Transaksi Perbankan")
                .description("Artikel ini membahas pentingnya perlindungan hukum bagi pemilik properti dalam transaksi perbankan, termasuk dalam pembiayaan properti dan hak kepemilikan. Adanya perlindungan ini penting untuk menjamin keamanan bagi semua pihak dalam transaksi, terutama bagi pemilik properti dan pihak bank yang terlibat.")
                .content("Transaksi perbankan yang melibatkan properti, seperti kredit pemilikan rumah (KPR) atau kredit modal usaha dengan jaminan properti, memerlukan perlindungan hukum yang kuat agar hak pemilik properti tetap terjaga. Dalam hal ini, bank bertindak sebagai pihak yang memberikan pembiayaan, sementara properti menjadi jaminan yang dapat diambil alih jika debitur gagal memenuhi kewajibannya.\n" +
                        "Salah satu landasan hukum utama dalam transaksi ini adalah Undang-Undang Nomor 4 Tahun 1996 tentang Hak Tanggungan atas Tanah Beserta Benda-benda yang Berkaitan dengan Tanah di Indonesia. Hak Tanggungan adalah hak jaminan yang diberikan kepada bank atas tanah atau bangunan milik debitur. Proses pendaftaran Hak Tanggungan ini melibatkan notaris dan pejabat pembuat akta tanah (PPAT), yang bertujuan agar hak kepemilikan debitur tercatat secara sah dan aman.\n" +
                        "Pentingnya perlindungan hukum ini adalah untuk memberikan kepastian bagi pemilik properti bahwa aset mereka tidak akan disalahgunakan. Selain itu, hak tanggungan juga memungkinkan bank untuk memiliki hak penjaminan yang kuat tanpa melanggar hak-hak pemilik. Jika terjadi wanprestasi, bank dapat melakukan eksekusi hak tanggungan secara sah, misalnya melalui lelang, yang disaksikan oleh otoritas terkait untuk memastikan proses yang transparan dan sesuai aturan.\n" +
                        "Di sisi lain, pemilik properti juga memiliki perlindungan hak untuk mendapatkan informasi yang jelas terkait prosedur dan risiko yang terkait dengan penggunaan properti sebagai jaminan. Ketika informasi ini disampaikan dengan transparan, diharapkan dapat meminimalkan kesalahpahaman antara bank dan nasabah.\n" +
                        "Dengan adanya perlindungan hukum yang baik, transaksi perbankan yang melibatkan properti dapat memberikan keamanan bagi kedua belah pihak, baik bagi bank sebagai pemberi pinjaman maupun bagi pemilik properti sebagai peminjam. Perlindungan ini penting agar semua proses transaksi tetap adil, transparan, dan dapat meningkatkan kepercayaan publik terhadap sistem hukum dan perbankan di Indonesia.")
                .thumbnailUrl("https://images.unsplash.com/photo-1501167786227-4cba60f6d58f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fGJhbmt8ZW58MHx8MHx8fDA%3D")
                .status(Articles.Status.publish)
                .categories(new ArrayList<>())
                .build();
        article3.getCategories().add(properti);
        article3.getCategories().add(bank);

        Articles article4 = Articles.builder()
                .title("Meningkatkan Akses dan Efisiensi Layanan Hukum untuk Masyarakat")
                .description("Artikel ini membahas pentingnya peran perusahaan hukum dalam memperbaiki akses dan efisiensi layanan hukum bagi masyarakat. Dengan menghadapi tantangan seperti proses hukum yang panjang dan birokrasi yang rumit, perusahaan hukum diharapkan dapat memberikan solusi yang lebih inklusif dan cepat bagi klien mereka.")
                .content("Di era modern ini, kebutuhan akan layanan hukum yang cepat, transparan, dan mudah diakses semakin mendesak. Banyak perusahaan hukum menghadapi tuntutan dari masyarakat yang berharap proses hukum tidak lagi berbelit-belit dan memakan waktu. Hal ini penting karena masyarakat sering kali mengalami kesulitan dalam mengakses layanan hukum akibat hambatan biaya, waktu, dan kerumitan proses yang panjang.\n" +
                        "    \n" +
                        "    Salah satu solusi yang dapat diambil oleh perusahaan hukum adalah dengan memanfaatkan teknologi. Digitalisasi proses hukum, seperti konsultasi online, pembuatan dokumen otomatis, serta sistem manajemen kasus yang terintegrasi, dapat memangkas waktu dan biaya. Perusahaan hukum yang menerapkan sistem ini dapat lebih responsif terhadap kebutuhan klien serta membantu mengurangi beban birokrasi.\n" +
                        "    \n" +
                        "    Selain itu, ada kebutuhan untuk meningkatkan kesadaran hukum di kalangan masyarakat. Perusahaan hukum dapat memberikan edukasi tentang hak-hak hukum dan prosedur yang ada, baik melalui seminar, media sosial, atau konten digital lainnya. Dengan pendekatan ini, masyarakat dapat lebih percaya diri dalam menavigasi sistem hukum dan mengambil keputusan yang tepat.\n" +
                        "    \n" +
                        "    Namun, di balik upaya ini, perusahaan hukum juga harus memastikan bahwa layanan hukum yang diberikan tetap adil dan tidak diskriminatif. Meningkatkan aksesibilitas dan efisiensi dalam layanan hukum tidak hanya akan membantu perusahaan meraih kepercayaan klien, tetapi juga akan berkontribusi dalam menciptakan sistem hukum yang lebih inklusif dan adil bagi semua kalangan.\n" +
                        "    \n" +
                        "    Dengan inovasi yang tepat dan pendekatan yang inklusif, perusahaan hukum dapat berperan besar dalam membangun kepercayaan publik terhadap sistem hukum, serta memastikan bahwa hukum tidak hanya tajam ke bawah, tetapi juga memberikan keadilan bagi seluruh lapisan masyarakat.")
                .thumbnailUrl("https://plus.unsplash.com/premium_photo-1661540409860-fe00bb21a51c?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .status(Articles.Status.publish)
                .categories(new ArrayList<>())
                .build();
        article4.getCategories().add(perusahaan);

        Articles article5 = Articles.builder()
                .title("Pentingnya Pemahaman Hukum dalam Kehidupan Sehari-hari")
                .description("Hukum adalah aturan yang mengatur perilaku masyarakat untuk menciptakan keteraturan dan keadilan. Memahami hukum sangat penting agar kita bisa hidup dengan aman, nyaman, dan adil.")
                .content("Hukum adalah serangkaian aturan yang ditetapkan oleh negara untuk mengatur perilaku masyarakat agar tercipta ketertiban dan keadilan. Setiap individu wajib mematuhi hukum yang berlaku di negara tempat mereka tinggal. Tujuan utama hukum adalah untuk menjaga keadilan, memberikan perlindungan, dan menyelesaikan sengketa.\n" +
                        "\n" +
                        "Di Indonesia, hukum terdiri dari berbagai cabang, seperti hukum pidana, perdata, administrasi negara, dan lainnya. Masing-masing cabang hukum memiliki fungsi dan perannya masing-masing dalam menyelesaikan masalah yang timbul di masyarakat.\n" +
                        "\n" +
                        "Sebagai contoh, hukum pidana berfungsi untuk mengatur tindakan kriminal, seperti pencurian atau pembunuhan, dan memberikan hukuman bagi pelakunya. Sedangkan hukum perdata lebih mengatur hubungan antarindividu, seperti perjanjian kontrak, pembagian warisan, dan masalah hak milik.\n" +
                        "\n" +
                        "Pentingnya pemahaman hukum tidak hanya untuk menghindari pelanggaran, tetapi juga untuk memahami hak dan kewajiban kita sebagai warga negara. Misalnya, hak untuk mendapatkan keadilan dan kewajiban untuk membayar pajak adalah dua hal yang diatur dalam hukum negara.\n" +
                        "\n" +
                        "Dengan memahami hukum, kita bisa menghindari masalah hukum yang tidak perlu dan juga bisa menjalani kehidupan dengan lebih tertib. Pendidikan hukum, meskipun tidak harus dalam bentuk pendidikan formal, sangat penting untuk memberikan pemahaman dasar mengenai hak dan kewajiban dalam kehidupan bermasyarakat.")
                .thumbnailUrl("https://plus.unsplash.com/premium_photo-1679429320956-f6171b6f006b?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .status(Articles.Status.publish)
                .categories(new ArrayList<>())
                .build();
        article5.getCategories().add(sosial);

        articlesList.add(article1);
        articlesList.add(article2);
        articlesList.add(article3);
        articlesList.add(article4);
        articlesList.add(article5);


        articlesRepository.saveAll(articlesList);
    }
}
