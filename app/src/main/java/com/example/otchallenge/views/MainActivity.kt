package com.example.otchallenge.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.otchallenge.MyApplication
import com.example.otchallenge.R
import com.example.otchallenge.databinding.ActivityBookListBinding
import com.example.otchallenge.di.DaggerActivityComponent
import com.example.otchallenge.model.data.Book
import com.example.otchallenge.presenter.BookPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewHolderBook {

	private var adapter: BookListAdapter? = null
	private lateinit var binding: ActivityBookListBinding

	@Inject
	lateinit var bookPresenter: BookPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val networkComponent = (application as MyApplication).networkComponent

		DaggerActivityComponent.builder()
			.networkComponent(networkComponent)
			.build()
			.inject(this)

		enableEdgeToEdge()
		binding = ActivityBookListBinding.inflate(layoutInflater)
		setContentView(binding.root)

		ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		initAdapter()
		bookPresenter.fetchBookList(this)
	}

	private fun initAdapter() {
		adapter = BookListAdapter(object : OnClickData<Book?> {
			override fun onClick(data: Book?) {
				Toast.makeText(applicationContext, data?.description, Toast.LENGTH_SHORT).show()
			}
		})
		binding.recyclerView.adapter = adapter
	}

	override fun showLoading(isLoading: Boolean) {
		if (isLoading) {
			binding.progressBar.visibility = View.VISIBLE
		} else {
			binding.progressBar.visibility = View.GONE
		}
	}

	override fun showData(bookList: List<Book>) {
		adapter?.setDataset(bookList)
		binding.error.visibility = View.GONE
		binding.progressBar.visibility = View.GONE
	}

	override fun showError(errorMessage: String?) {
		binding.error.visibility = View.VISIBLE
		binding.progressBar.visibility = View.GONE
		errorMessage?.let {
			Toast.makeText(this, it, Toast.LENGTH_LONG).show()
		} ?: run {
			Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
		}
	}
}
