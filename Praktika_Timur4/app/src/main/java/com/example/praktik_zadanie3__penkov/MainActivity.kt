package com.example.praktik_zadanie3__penkov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.praktik_zadanie3__penkov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\nОбразовательно-производственный центр (кластер) федерального проекта\n\"Профессионалитет\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \"ХШН\" и «Педагогика» на базе ГБПОУ ВО \"ГПК\" .\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ХШН\"; сетевые ОО - ГБПОУ ВО \"БАИК\", ГБПОУ ВО \"ВГПГК\", ГБПОУ ВО \"ВТППП\", ГБПОУ ВО \"ВГПТК\", ГБПОУ ВО \"БТПИТ\".\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ГПК\"; сетевые ОО - ГБПОУ ВО \"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \"БТПИТ\".\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
            published = "21 мая в 18:36",
            likes = 999999,
            share = 990,
            likedByMe = false
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            osnovnoitext.text = post.content
            textlike.text = post.likes.toString()
            textShare.text = post.share.toString()
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.like)
            }
            share?.setOnClickListener {
                post.share++
                textShare.text = post.share.toString()
                when {
                    post.share<1000 ->textShare.text =post.share.toString()
                    post.share in 1000..999999 ->textShare.text ="${post.share/1000}K"
                    else->textShare.text =String.format("%.1fM",post.share.toDouble()/1000000)
                }

            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_like_filled_24dp
                    else R.drawable.ic_like_24dp
                )
                if (post.likedByMe) post.likes++ else post.likes--
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 ->textlike.text ="${post.likes/1000}K"
                    post.likes<1000->textlike.text =post.likes.toString()
                    else->textlike.text =String.format("%.1fM",post.likes.toDouble()/1000000)
                }
            }
        }
    }

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var share: Int,
        var likedByMe: Boolean = false
    )
}

