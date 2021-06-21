package com.example.healtsorsomethingelse.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.notification.*
import com.example.healtsorsomethingelse.databinding.ActivityNotificationBinding
import com.example.healtsorsomethingelse.extensions.ContextExtensions.showLongToast
import com.example.healtsorsomethingelse.extensions.ViewExtensions.gone
import com.example.healtsorsomethingelse.extensions.ViewExtensions.visible
import com.example.healtsorsomethingelse.utils.AnimationHelper
import com.example.healtsorsomethingelse.utils.AppBarStateChangeListener
import com.example.healtsorsomethingelse.utils.notifications.NotificationViewModel
import com.example.healtsorsomethingelse.utils.notifications.OnSwipeCallback
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    @Inject
    lateinit var animationHelper: AnimationHelper
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var adapter: NotificationAdapter
    private val viewModel by viewModels<NotificationViewModel>()
    private val swipeCallback = OnSwipeCallback { pos, id ->
        viewModel.sendAction(RemoveNotification(id))
    }

    /**
     * Start observing on UI state
     */
    private fun observeUiState() {
        launch {
            viewModel.state.collect {
                when (it) {
                    UiState.Idle -> initLoading()
                    UiState.Loading -> handleLoading()
                    is UiState.Error -> {
                        hideContent()
                        showMessage(it.text)
                    }
                    is UiState.Content -> {
                        showContent()
                        fetchContent(it.notifications)
                    }
                }
            }
        }
    }

    private fun setupTouchHelper() {

    }
    /**
     * Remove content, progress, message from the screen
     */
    private fun hideContent() {
        binding.progressBar.gone()
        binding.messageTextView.gone()
        binding.recyclerView.gone()
    }

    /**
     * Draw notifications list on the screen
     */
    private fun fetchContent(list: List<Notifications>) {
        if (list.isEmpty()) {
            showMessage(getString(R.string.no_notification))
        } else {
            fetchList(list)
        }
    }

    private fun fetchList(list: List<Notifications>) {
        if (this::adapter.isInitialized) {
            adapter.updateList(list)
        } else {
            adapter = NotificationAdapter(
                layoutInflater,
                list.toMutableList(),
                swipeCallback
            )
            binding.recyclerView.adapter = adapter
        }
    }

    /**
     * Show list on screen
     */
    private fun showContent() {
        binding.progressBar.gone()
        binding.messageTextView.gone()
        binding.recyclerView.visible()
    }

    /**
     * Show message on screen
     * @param text text of message
     */
    private fun showMessage(text: String) {
        binding.recyclerView.gone()
        binding.progressBar.gone()
        binding.messageTextView.visible()
        binding.messageTextView.text = text
    }

    /**
     * Handle UI state - Loading
     */
    private fun handleLoading() {
        binding.progressBar.visible()
        binding.recyclerView.gone()
        binding.messageTextView.gone()
    }

    private fun initLoading() {
        viewModel.sendAction(Load)
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        animationHelper.setOffSetListener(binding.appBarLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeUiState()
        setupActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}