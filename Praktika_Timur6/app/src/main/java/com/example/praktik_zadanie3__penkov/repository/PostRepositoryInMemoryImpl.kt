package com.example.praktik_zadanie3__penkov.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktik_zadanie3__penkov.repository.Post

class PostRepositoryInMemoryImpl : PostRepository{
        private var posts = listOf(
            Post(
                id = 2,
                author = "Борисоглебский техникум промышленных и информационных техологий",
                content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\nОбразовательно-производственный центр (кластер) федерального проекта\n\"Профессионалитет\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \"ХШН\" и «Педагогика» на базе ГБПОУ ВО \"ГПК\" .\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ХШН\"; сетевые ОО - ГБПОУ ВО \"БАИК\", ГБПОУ ВО \"ВГПГК\", ГБПОУ ВО \"ВТППП\", ГБПОУ ВО \"ВГПТК\", ГБПОУ ВО \"БТПИТ\".\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ГПК\"; сетевые ОО - ГБПОУ ВО \"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \"БТПИТ\".\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
                likedByMe = false,
                published = "27 апреля в 05:20",
                likes = 380,
                share = 27,
                shareByMe = false
            ),
        Post(
            id = 2,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\nОбразовательно-производственный центр (кластер) федерального проекта\n\"Профессионалитет\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \"ХШН\" и «Педагогика» на базе ГБПОУ ВО \"ГПК\" .\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ХШН\"; сетевые ОО - ГБПОУ ВО \"БАИК\", ГБПОУ ВО \"ВГПГК\", ГБПОУ ВО \"ВТППП\", ГБПОУ ВО \"ВГПТК\", ГБПОУ ВО \"БТПИТ\".\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ГПК\"; сетевые ОО - ГБПОУ ВО \"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \"БТПИТ\".\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
            likedByMe = false,
            published = "21 мая в 18:36",
            likes = 999999,
            share = 990,
            shareByMe = false
        ),
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Int) {
        posts = posts.map {
            if(it.id!= id.toInt()) it else it.copy(likedByMe = !it.likedByMe)
        }
        posts.map{
            if(it.likedByMe && it.id == id.toInt()) it.likes++ else it
        }
        posts.map {
            if(!it.likedByMe && it.id == id.toInt()) it.likes-- else it
        }
        data.value = posts
    }
    override fun shareById(id: Int) {
        posts = posts.map {
            if(it.id!= id.toInt()) it else it.copy(shareByMe = !it.shareByMe)
        }
        posts.map {
            if (it.id != id.toInt()) it else it.share++
        }
        data.value = posts
    }
}
