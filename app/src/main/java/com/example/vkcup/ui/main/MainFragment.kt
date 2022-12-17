package com.example.vkcup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.vkcup.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    private val checkItems = hashSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        _binding = FragmentMainBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        generateList().forEach {
            binding.chipGroupChoice.addView(generateItems(it))
        }
        updateContinueTitle()
    }

    private fun generateItems(text: String): ItemView {
        val itemView = ItemView(requireContext())
        itemView.text = text
        itemView.setOnClickListener {
            itemView.click { isChecked ->
                if (isChecked) {
                    checkItems.add(itemView.text)
                } else {
                    checkItems.remove(itemView.text)
                }
                updateContinueTitle()
            }
        }
        return itemView
    }

    private fun updateContinueTitle() {
        binding.continueTitle.isVisible = checkItems.isNotEmpty()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray("checked", checkItems.toTypedArray())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val saveChecked = savedInstanceState?.getStringArray("checked")
        saveChecked?.let {
            checkItems.addAll(it)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

private fun generateList(): List<String> {
    return listOf(
        "Юмор",
        "Еда",
        "Кино",
        "Рестораны",
        "Прогулки",
        "Политика",
        "Новости",
        "Автомобили",
        "Сериалы",
        "Рецепты",
        "Работа",
        "Отдых",
        "Спорт",
        "Политика",
        "Новости",
        "Юмор",
        "Еда",
        "Кино",
        "Рестораны",
        "Прогулки",
        "Политика",
        "Новости",
        "Юмор",
        "Еда",
        "Кино"
    )
}