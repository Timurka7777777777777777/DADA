package com.example.praktik_zadanie3__penkov.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktik_zadanie3__penkov.repository.Post

class PostRepositoryInMemoryImpl : PostRepository{
    private var nextId=1
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Борисоглебский техникум промышленных и информационных техологий>",
            content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\\nОбразовательно-производственный центр (кластер) федерального проекта\\n\\\"Профессионалитет\\\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \\\"ХШН\\\" и «Педагогика» на базе ГБПОУ ВО \\\"ГПК\\\" .\\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \\\"ХШН\\\"; сетевые ОО - ГБПОУ ВО \\\"БАИК\\\", ГБПОУ ВО \\\"ВГПГК\\\", ГБПОУ ВО \\\"ВТППП\\\", ГБПОУ ВО \\\"ВГПТК\\\", ГБПОУ ВО \\\"БТПИТ\\\".\\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \\\"ГПК\\\"; сетевые ОО - ГБПОУ ВО \\\"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \\\"БТПИТ\\\".\\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 699,
            share = 35,
            shareByMe=false
        ),
        Post(
            id = nextId++,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\\nОбразовательно-производственный центр (кластер) федерального проекта\\n\\\"Профессионалитет\\\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \\\"ХШН\\\" и «Педагогика» на базе ГБПОУ ВО \\\"ГПК\\\" .\\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \\\"ХШН\\\"; сетевые ОО - ГБПОУ ВО \\\"БАИК\\\", ГБПОУ ВО \\\"ВГПГК\\\", ГБПОУ ВО \\\"ВТППП\\\", ГБПОУ ВО \\\"ВГПТК\\\", ГБПОУ ВО \\\"БТПИТ\\\".\\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \\\"ГПК\\\"; сетевые ОО - ГБПОУ ВО \\\"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \\\"БТПИТ\\\".\\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
            published = "9 мая в 20:31",
            likedByMe = false,
            likes = 999999,
            share = 999,
            shareByMe=false
        )
    ).reversed()
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun save(post: Post) {
        if(post.id==0){
            posts = listOf(post.copy(
                id = nextId++,
                author = "Я",
                likedByMe = false,
                published = "Сейчас",
                shareByMe = false
            )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map{
            if (it.id != post.id) it else it.copy (content = post.content, likes = post.likes, share = post.share)
        }
        data.value = posts
    }
    override fun likeById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(likedByMe = !it.likedByMe, likes = if (!it.likedByMe) it.likes+1 else it.likes-1)
        }
        data.value = posts
    }
    override fun shareById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(shareByMe = !it.shareByMe, share = it.share+1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id!=id }
        data.value = posts
    }


}

