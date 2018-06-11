package com.msalcedo.dinnews.screen.detail

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.databinding.ActivityDetailBinding
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.screen.detail.di.DaggerDetailComponent
import com.msalcedo.dinnews.screen.detail.di.DetailModule
import com.msalcedo.dinnews.screen.detail.mvvm.DetailViewModel
import com.msalcedo.dinnews.screen.news.events.ArticleEvent
import org.jetbrains.anko.intentFor
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.msalcedo.dinnews.common.ext.empty


class DetailActivity : RxActivity(), ArticleEvent {
    @Inject
    lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.eventHandler = this
        initToolbar()
        initView()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.tvDescription?.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }

    private fun initView() {
        binding.tvTitle!!.text = viewModel.article?.getTitle() ?: ""
        binding.tvDate!!.text = viewModel.article!!.publishedAt
        binding.tvAuthor!!.text = viewModel.article?.author ?: ""
        binding.tvDescription!!.text = viewModel.article?.getDescription() ?: ""
        Application.component.picasso().load(viewModel.article!!.urlToImage)
                .placeholder(R.drawable.placeholder_bg)
                .into(binding.ivNews)
    }

    override fun init() {
        viewModel.start(intent.extras)

    }

    private fun initToolbar() {
        binding.toolbar!!.title = ""
        binding.toolbar!!.setNavigationOnClickListener({
            onBackPressed()
        })
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun onClickReadMore() {
        if (!viewModel.article!!.url.empty()) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(viewModel.article!!.url)
            startActivity(i)
        }
    }

    override fun initializeComponent() {
        DaggerDetailComponent.builder()
                .detailModule(
                        DetailModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {
        fun start(context: Context, article: Article) {
            val intent = context.intentFor<DetailActivity>()
            val bundle = Bundle()
            bundle.putString(Article.KEY, Article.adapter.toJson(article))
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
