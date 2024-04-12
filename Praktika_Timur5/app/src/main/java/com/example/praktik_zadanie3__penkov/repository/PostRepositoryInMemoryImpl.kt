package com.example.praktik_zadanie3__penkov.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktik_zadanie3__penkov.repository.Post

class PostRepositoryInMemoryImpl : PostRepository{
    private var post = Post(
        id = 1,
        author = "Борисоглебский техникум промышленных и информационных техологий",
        content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\nОбразовательно-производственный центр (кластер) федерального проекта\n\"Профессионалитет\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \"ХШН\" и «Педагогика» на базе ГБПОУ ВО \"ГПК\" .\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ХШН\"; сетевые ОО - ГБПОУ ВО \"БАИК\", ГБПОУ ВО \"ВГПГК\", ГБПОУ ВО \"ВТППП\", ГБПОУ ВО \"ВГПТК\", ГБПОУ ВО \"БТПИТ\".\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ГПК\"; сетевые ОО - ГБПОУ ВО \"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \"БТПИТ\".\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
        published = "21 мая в 18:36",
        likes = 999999,
        share = 990,
        likedByMe = false
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }
    override fun share() {
        post.share++
        data.value = post
    }
}